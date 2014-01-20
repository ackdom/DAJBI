package cz.cvut.fit.dajbi.instruction;

import cz.cvut.fit.dajbi.stack.Frame;

public class L2I extends Instruction {

	public L2I(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		long value = (Long)frame.pop();
		frame.push((int)value);
	}

}
