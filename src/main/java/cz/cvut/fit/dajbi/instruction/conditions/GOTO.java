package cz.cvut.fit.dajbi.instruction.conditions;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class GOTO extends Instruction {

	public GOTO(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		short index = frame.getReader().readShort();
		frame.getReader().move(index);
		frame.getReader().move(-3);
	}

}
