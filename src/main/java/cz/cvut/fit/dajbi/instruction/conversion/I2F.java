package cz.cvut.fit.dajbi.instruction.conversion;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class I2F extends Instruction {

	public I2F(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {

		int value = (Integer)frame.pop();
		frame.push((float)value);
	}

}
