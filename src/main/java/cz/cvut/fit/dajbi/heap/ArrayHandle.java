package cz.cvut.fit.dajbi.heap;

public class ArrayHandle {

	/** @see #getInstanceData() */
	private Object[] instanceData;
	

	ArrayHandle(Object[] instanceData) {
		super();
		this.instanceData = instanceData;
	}


	/**
	 * instance data of array
	 * @return
	 */
	Object[] getInstanceData() {
		return instanceData;
	}

}
