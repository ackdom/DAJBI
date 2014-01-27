package cz.cvut.fit.dajbi.heap;

import java.util.HashMap;
import java.util.Map;

import cz.cvut.fit.dajbi.internal.AccessFlag;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Field;

public class Heap {
	
	private static final Heap instance = new Heap();
	private long counter;
	public static final Long NULL =  -1L;
	
	private Map<Long,HeapHandle> rootSet; //objectPool
	
	private int heapSize = 64*1024*1024;
	//pozor heapSize je ted int (vs. long)
	byte[] heapData = new byte[heapSize];
	byte[] heapNext = new byte[heapSize];
	
	private long freeSpace = 0;
	
	private Heap() {
		rootSet = new HashMap<Long, HeapHandle>();
		counter = 1;
	}
	
	public static Heap getInstance() {
		return instance;
	}
	
	/**
	 * Returns tagged reference for given index.
	 * @param id
	 * @return
	 */
	public HeapHandle getObject(long id) {
		return rootSet.get(id);
	}
	
	/** 
	 * Returns index of tagged reference to instance data on given offset in heap.
	 * @param offset start of instance data
	 * @param classFile {@link ClassFile} of referenced instance data
	 * @return index of tagged reference to instance data on heap
	 */
	HeapHandle getObjectReference(long offset, ClassFile classFile) {
		HeapHandle handle = new HeapHandle(classFile, offset, counter);
		handle.IncReferences();
		
		rootSet.put(counter, handle);
		incCounter();
		return handle;
//		return incCounter();
	}


	public Object[] getArray(long id) {
		//TODO array
		throw new UnsupportedOperationException("arrays");
//		return (Object[]) getObject(id).getInstanceData().get("[]");
	}
	
	public HeapHandle allocArray(ClassFile type, int size) {
		HeapHandle handle = new HeapHandle(type, allocateSpace(type.getDataSize()), counter);
		//TODO array
		throw new UnsupportedOperationException("arrays");
//		Object[] obj = new Object[size];
//		HeapHandle handle = new HeapHandle(type);
//		Map<String, Object> instanceData = handle.getInstanceData();
//		instanceData.put("[]",obj );
//		objectPool.put(counter, handle);
//		return counter++;
	}
	
	
	public HeapHandle allocArrayPrim(byte type, int size) {
		//TODO array
		throw new UnsupportedOperationException("arrays");
	}

	public HeapHandle allocObject(ClassFile classFile) {
		HeapHandle handle = new HeapHandle(classFile, allocateSpace(classFile.getDataSize()), counter);
		handle.IncReferences();

		for(Field f : classFile.getFields()) {
			if(!f.hasFlag(AccessFlag.ACC_STATIC)) {
				handle.setFieldData(f);
			}
		}		
		rootSet.put(counter, handle);
		incCounter();
		return handle;
//		return incCounter();
//		throw new UnsupportedOperationException("alloc Object");
	}

	private long incCounter() {
		long orig = counter;
		
		while(rootSet.containsKey(counter)) {
			if (counter == Long.MAX_VALUE - 1) {
				counter = Long.MIN_VALUE;
			} else {
				counter++;
			}
		}
		
		return orig;
	}
	
	private long allocateSpace(long objectSize) {
		//pri zaplneni spusti GC
		if (freeSpace + objectSize + 4 >= heapSize) {
			collectGarbage();
		}
		
		long origFreeSpace = freeSpace;
		//allocating space for instance data + 1 integer for size of this data
		freeSpace += objectSize + 4;
		
		
		
		if (freeSpace >= heapSize) {
			if(heapSize == Integer.MAX_VALUE) {
				throw new OutOfMemoryError("MaxInt size of heap is limiting.");
			} else {
				throw new OutOfMemoryError();
			}
		}
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
	
	void removeFromRootSet(long rootIndex) {
		rootSet.remove(rootIndex);
	}

}
