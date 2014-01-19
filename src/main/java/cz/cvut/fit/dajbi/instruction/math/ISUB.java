package cz.cvut.fit.dajbi.instruction.math;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class ISUB extends Instruction {

	public ISUB(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		int a = (Integer) frame.pop();
		int b = (Integer) frame.pop();
		int result = a - b ;
		frame.push(result);
	}

}
