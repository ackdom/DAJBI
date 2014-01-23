package cz.cvut.fit.dajbi.instruction.array;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class ASTORE extends Instruction {

	
	Object value;
	
	public ASTORE(Frame f, Object value) {
		super(f);
		this.value = value;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		int index  = (Integer) frame.pop();
		long ref = (Long) frame.pop();
		Object[] array = Heap.getInstance().getArray(ref);
		array[index] = value;
	}

}
