package cz.cvut.fit.dajbi.heap;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Field;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;

public class GarbageCollector {
	
	private static final int FWPOINTEROFFSET = 4;

	private Collection<HeapHandle> rootSet;
	private ByteBuffer heapSrc;
	private ByteBuffer heapDst;
	
	private long dstFreeSpace = 0;

	public GarbageCollector(Set<HeapHandle> rootSet, byte[] heapSrc, byte[] heapDst) {
		this.rootSet = rootSet;
		this.heapSrc = ByteBuffer.wrap(heapSrc);
		this.heapDst = ByteBuffer.wrap(heapDst);
	}
	
	/**
	 * Copies data reachable from rootSet.
	 * @return offset pointing to free space in heapdst
	 */
	public long collect() {
		Iterator<HeapHandle> rootSetIterator = rootSet.iterator();
		while(rootSetIterator.hasNext()) {
			HeapHandle handle = rootSetIterator.next();
			long srcOffset = handle.getDataOffset();
			if (srcOffset == Heap.NULL) { continue; }
			ClassFile classFile = handle.getClassFile();
			
			int size = heapSrc.getInt((int) srcOffset);
			if (size == -2) { //data already copied, use forwarding pointer
				handle.setDataOffset(heapSrc.getLong((int) (srcOffset + FWPOINTEROFFSET)));
			} else {
				long dstOffset = dstFreeSpace;
				//set ref. to new copy in dstHeap
				handle.setDataOffset(dstOffset);
				
				copyData(srcOffset, size);
				//increment the freeSpace pointer
				dstFreeSpace += size;
				
				//copy referenced object's data
				collect(classFile, dstOffset);
			}
		}
		return dstFreeSpace;
	}

	/**
	 * @param classFile for class which object data are here
	 * @param instanceDstOffset pointing to object data in dstHeap
	 */
	private void collect(ClassFile classFile, long instanceDstOffset) {
		//TODO rozsirit i o pole
		List<Field> referenceFields = getReferenceFields(classFile);
		for (Field field : referenceFields) {
			//address of field reference in dstHeap
			long fieldRefAdr = instanceDstOffset + HeapHandle.INTERNALDATASIZE + field.getFieldDataOffset();
			long srcOffset = heapDst.getLong((int) fieldRefAdr);
			if(srcOffset == Heap.NULL) { continue; }
			ClassFile fieldsClassFile = getInstancesClassFile(heapSrc, srcOffset);
			
			int size = heapSrc.getInt((int) srcOffset);
			if (size == -2) {
				long ref = heapSrc.getLong((int) (srcOffset + FWPOINTEROFFSET));
				heapDst.putLong((int) fieldRefAdr, ref);
			} else {
				long dstOffset = dstFreeSpace;
				heapDst.putLong((int) fieldRefAdr, dstOffset);
				
				copyData(srcOffset, size);
				dstFreeSpace += size;
				
				collect(fieldsClassFile, dstOffset);
			}
		}
		
	}

	/**
	 * loads instance's ClassFile from heap
	 * @param heap {@link ByteBuffer} where is instance stored
	 * @param instanceOffset offset of instance data at heap
	 * @return
	 */
	private ClassFile getInstancesClassFile(ByteBuffer heap, long instanceOffset) {
		long ref = heap.getLong((int) (instanceOffset + 4));
		String name = (String) Heap.getInstance().getNative(ref);
		ClassFile classFile = ClassResolver.resolveWithLookup(name);
		return classFile;
	}

	private void copyData(long srcDataOffset, int size) {
		for (int i = 0; i < size; i++) {
			heapDst.array()[(int) (dstFreeSpace + i)] = heapSrc.array()[(int) (srcDataOffset + i)];
		}
		breakSrc(srcDataOffset);
	}

	private void breakSrc(long srcDataOffset) {
		// -2 pro identifikaci "broken heart"
		heapSrc.putInt((int) srcDataOffset, -2);
		// leave in src forwarding pointer to heapDst
		heapSrc.putLong((int) (srcDataOffset + FWPOINTEROFFSET), dstFreeSpace);
	}

	private List<Field> getReferenceFields(ClassFile classFile) {
		ArrayList<Field> list = new ArrayList<Field>();
		for (Field field : classFile.getFields()) {
			if(field.getDescription().charAt(0) == 'L') {
				list.add(field);
			}
		}
		return list;
	}
}
