package cz.cvut.fit.dajbi.instruction.conditions;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class GOTOW extends Instruction {

	public GOTOW(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		frame.getReader().move(frame.getReader().readInt());
		frame.getReader().move(-5);

	}

}
