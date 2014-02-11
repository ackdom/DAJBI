package cz.cvut.fit.dajbi.heap;

import cz.cvut.fit.dajbi.DAJBI;

public class NativeObjectHandle implements NullableHandle {
	
	private boolean isNull = true;

	public boolean isNull() {
		return isNull;
	}

	public void setNull() {
		this.isNull = true;
		this.instanceData = null;
	}
	
	private Object instanceData;

	public Object getInstanceData() {
		return instanceData;
	}

	public void setInstanceData(Object instanceData) {
		this.instanceData = instanceData;
		this.isNull = false;
		if(instanceData == null) {
			setNull();
		}
	}

	public NativeObjectHandle(Object instanceData) {
		super();
		setInstanceData(instanceData);
	}

	public void IncReferences() {
		DAJBI.logger.info("IncReference not used");
	}

	public void DecReferences() {
		DAJBI.logger.info("DecReference not used");
	}

}
