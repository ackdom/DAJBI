package cz.cvut.fit.dajbi.instruction.stack;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class BIPUSH extends Instruction {

	public BIPUSH(Frame f) {
		super(f);
	}

	@Override
	public void execute() {
		frame.push((int)frame.getReader().readByte());		
	}

}
