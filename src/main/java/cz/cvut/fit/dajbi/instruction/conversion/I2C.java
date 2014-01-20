package cz.cvut.fit.dajbi.instruction.conversion;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class I2C extends Instruction {

	public I2C(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		int value = (Integer)frame.pop();
		value = (int)(char)value;
		frame.push(value);
	}

}
