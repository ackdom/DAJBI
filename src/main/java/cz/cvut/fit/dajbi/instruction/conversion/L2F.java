package cz.cvut.fit.dajbi.instruction.conversion;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class L2F extends Instruction {

	public L2F(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		long value = (Long)frame.pop();
		frame.push((float)value);
	}

}
