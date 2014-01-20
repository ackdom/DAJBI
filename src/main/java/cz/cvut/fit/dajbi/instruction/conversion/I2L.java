package cz.cvut.fit.dajbi.instruction.conversion;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class I2L extends Instruction {

	public I2L(Frame f) {
		super(f);
	}

	@Override
	public void execute() {
		int value = (Integer)frame.pop();
		frame.push((long)value);

	}

}
