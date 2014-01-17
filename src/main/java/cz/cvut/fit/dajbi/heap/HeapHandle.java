package cz.cvut.fit.dajbi.heap;

import java.util.HashMap;
import java.util.Map;

import cz.cvut.fit.dajbi.internal.ClassFile;

public class HeapHandle {

	ClassFile classFile;
	Map<String,Object> instanceData;
	
	public HeapHandle(ClassFile cf) {
		super();
		classFile = cf;
		instanceData = new HashMap<String, Object>();
	}

	/**
	 * @return the instanceData
	 */
	public Map<String, Object> getInstanceData() {
		return instanceData;
	}

	/**
	 * @param instanceData the instanceData to set
	 */
	public void setInstanceData(Map<String, Object> instanceData) {
		this.instanceData = instanceData;
	}
	
	
	
	
	
	
	
}
