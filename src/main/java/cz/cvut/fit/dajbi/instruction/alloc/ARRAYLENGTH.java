package cz.cvut.fit.dajbi.instruction.alloc;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class ARRAYLENGTH extends Instruction {

	public ARRAYLENGTH(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		//TODO repair getArray()
		int length = Heap.getInstance().getArray((Long)frame.pop()).length;
		frame.push(length);

	}

}
