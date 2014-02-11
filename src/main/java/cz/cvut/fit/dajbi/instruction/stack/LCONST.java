package cz.cvut.fit.dajbi.instruction.stack;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class LCONST extends Instruction {

	private long value;

	public LCONST(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}
	
	public LCONST(Frame f, long i) {
		super(f);
		value = i;
	}

	@Override
	public void execute() {
		frame.push(value);
	}

}
