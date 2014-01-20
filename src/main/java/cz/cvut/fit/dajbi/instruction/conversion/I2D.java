package cz.cvut.fit.dajbi.instruction.conversion;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class I2D extends Instruction {

	public I2D(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		int value = (Integer)frame.pop();
		frame.push((double)value);

	}

}
