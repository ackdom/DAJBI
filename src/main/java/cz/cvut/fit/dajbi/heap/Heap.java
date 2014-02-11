package cz.cvut.fit.dajbi.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.internal.AccessFlag;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Field;

public class Heap {
	
	private static final Heap instance = new Heap();
	private long arrayInstCounter;
	private long nativeInstCounter;
	public static final Long NULL =  -1L;
	
	private Set<HeapHandle> rootSet; //objectPool
	private Map<Long, ArrayHandle> arrays;
	private Map<Long, Object> nativeInstances;
	
	private int heapSize = 64;//*1024*1024;
	//pozor heapSize je ted int (vs. long)
	byte[] heapData = new byte[heapSize];
	byte[] heapNext = new byte[heapSize];
	
	private long freeSpace = 0;
	
	private Heap() {
//		rootSet = new HashMap<Long, HeapHandle>();
		rootSet = new HashSet<HeapHandle>();
		arrays = new HashMap<Long, ArrayHandle>();
		nativeInstances = new HashMap<Long, Object>();
		arrayInstCounter = 1;
		nativeInstCounter = 1;
	}
	
	public static Heap getInstance() {
		return instance;
	}
	
	/** 
	 * Returns tagged reference to instance data on given offset in heap.
	 * @param offset start of instance data
	 * @return tagged reference to instance data on heap
	 */
	HeapHandle getObjectReference(long offset) {
		HeapHandle handle = new HeapHandle(offset);
		handle.IncReferences();
		
		rootSet.add(handle);
		return handle;
//		return incCounter();
	}


	public Object[] getArray(long id) {
		//TODO array
		return arrays.get(id).getInstanceData();
//		throw new UnsupportedOperationException("arrays");
	}
	
	public long allocArray(ClassFile type, int size) {
		//TODO array
//		throw new UnsupportedOperationException("arrays");
		ArrayHandle handle = new ArrayHandle(new Object[size]);
		arrays.put(arrayInstCounter, handle);
		
		return arrayInstCounter++;
	}
	
	public HeapHandle allocObject(ClassFile classFile) {
		HeapHandle handle = new HeapHandle(classFile, allocateSpace(classFile.getDataSize()));
		handle.IncReferences();
		//alloc superclasses' fields as well
		ClassFile cf = classFile;
		while (cf != null) {
			for (Field f : cf.getFields()) {
				if (!f.hasFlag(AccessFlag.ACC_STATIC)) {
					handle.setFieldData(f);
				}
			}
			cf = cf.getSuperClassCF();
		}
		rootSet.add(handle);
		return handle;
	}

	private long allocateSpace(long objectSize) {
		//pri zaplneni spusti GC
		while (freeSpace + objectSize + HeapHandle.INTERNALDATASIZE >= heapSize && heapSize < Integer.MAX_VALUE) {
			collectGarbage();
		}
		
		long origFreeSpace = freeSpace;
		//allocating space for instance data + INTERNALDATASIZE for internal instance data
		freeSpace += objectSize + HeapHandle.INTERNALDATASIZE;
		
		
		
		if (freeSpace >= heapSize) {
			if(heapSize == Integer.MAX_VALUE) {
				throw new OutOfMemoryError("MaxInt size of heap is limiting.");
			} else {
				throw new OutOfMemoryError();
			}
		}
//		DAJBI.logger.fatal(freeSpace + " / " + heapSize);
		return origFreeSpace;
	}
	
	private void collectGarbage() {
		GarbageCollector collector = new GarbageCollector(rootSet, heapData, heapNext);
		freeSpace = collector.collect();
		
		//prohozeni
		byte[] tmp = heapData;
		heapData = heapNext;
		heapNext = tmp;
		
		heapSize = heapData.length;
		if (freeSpace > heapSize/2) { //pokud je po GC zaplneno vic nez z poloviny, pro priste se zdvojnasobi velikost
			if (heapSize > Integer.MAX_VALUE/2) {
				heapNext = new byte[Integer.MAX_VALUE];
			} else {
				heapNext = new byte[2*heapSize];
			}
		} else if (heapNext.length < heapSize) { //zalozni musi byt aspon stejne velka
			heapNext = new byte[heapSize];
		}
	}

	void setByte(long index, byte value) {
		//TODO indexace z longu !
		heapData[(int) index] = value;
	}
	
	byte getByte(long index) {
		//TODO indexace z longu !
		return heapData[(int) index];
	}
	
	void removeFromRootSet(HeapHandle handle) {
		rootSet.remove(handle);
	}
	
	public long allocNative(Object obj) {
		nativeInstances.put(nativeInstCounter, obj);
		return nativeInstCounter++;
	}
	
	public Object getNative(long ref) {
		return nativeInstances.get(ref);
	}
	
	public void setNative(long ref, Object obj) {
		nativeInstances.put(ref, obj);
	}

}
