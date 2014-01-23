package cz.cvut.fit.dajbi.instruction.array;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;


public class ALOAD extends Instruction {

	public ALOAD(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void execute() {
		int index  = (Integer) frame.pop();
		long ref = (Long) frame.pop();
		Object[] array = Heap.getInstance().getArray(ref);
		frame.push(array[index]);
	}

}
