package cz.cvut.fit.dajbi.heap;

import java.util.HashMap;
import java.util.Map;

import cz.cvut.fit.dajbi.internal.AccessFlag;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Field;

public class Heap {
	
	private static final Heap instance = new Heap();
	private long counter;
	
	private Map<Long,HeapHandle> objectPool;
	
	
	
	
	private Heap() {
		objectPool = new HashMap<Long, HeapHandle>();
		counter = 1;
	}
	
	public static Heap getInstance() {
		return instance;
	}
	
	public HeapHandle getObject(long id) {
		return objectPool.get(id);
	}
	
	
	public long allocObject(ClassFile classFile) {
		HeapHandle handle = new HeapHandle(classFile);
		Map<String, Object> instanceData = handle.getInstanceData();
		for(Field f : classFile.getFields()) {
			if(!f.hasFlag(AccessFlag.ACC_STATIC)) {
				instanceData.put(f.getName(), f.getValue());
			}
		}		
		objectPool.put(counter, handle);
		return counter++;		
	}
	
	

}
