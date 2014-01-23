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
	
	public Object[] getArray(long id) {
		return (Object[]) getObject(id).getInstanceData().get("[]");
	}
	
	
	public long allocArray(ClassFile type, int size) {
		Object[] obj = new Object[size];
		HeapHandle handle = new HeapHandle(type);
		Map<String, Object> instanceData = handle.getInstanceData();
		instanceData.put("[]",obj );
		objectPool.put(counter, handle);
		return counter++;		}
	
	
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
