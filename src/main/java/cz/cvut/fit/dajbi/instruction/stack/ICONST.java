package cz.cvut.fit.dajbi.instruction.stack;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class ICONST extends Instruction {
	
	int value ;

	public ICONST(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	public ICONST(Frame f, int i) {
		super(f);
		value = i;
	}

	@Override
	public void execute() {
		frame.push(value);
	}

}
