package cz.cvut.fit.dajbi.instruction.store;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class ISTORE extends Instruction {
	
	int index;

	public ISTORE(Frame f) {
		super(f);
		index = (int) frame.getReader().readByte();
	}

	public ISTORE(Frame f, int i) {
		super(f);
		index = i;
	}

	@Override
	public void execute() {
		frame.setLocal(index, frame.pop());
	}

}
