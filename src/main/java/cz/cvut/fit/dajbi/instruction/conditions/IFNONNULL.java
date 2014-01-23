package cz.cvut.fit.dajbi.instruction.conditions;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class IFNONNULL extends Instruction {

	public IFNONNULL(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		int jump = frame.getReader().readShort();
		if(frame.pop() != Heap.NULL) {
			frame.getReader().move(jump);
			frame.getReader().move(-3);
		}

	}

}
