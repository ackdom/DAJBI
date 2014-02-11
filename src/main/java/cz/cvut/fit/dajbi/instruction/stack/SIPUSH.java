package cz.cvut.fit.dajbi.instruction.stack;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class SIPUSH extends Instruction {

	public SIPUSH(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		frame.push((int) frame.getReader().readShort());		
	}

}
