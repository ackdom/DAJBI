package cz.cvut.fit.dajbi.instruction.math;

import cz.cvut.fit.dajbi.stack.Frame;

public class ADD<T extends Number> extends ArithemticInstruction<T> {

	public ADD(Frame f) {
		super(f);
	}

	@Override
	public void execute() {
		arithmetic.add(a);
		arithmetic.add(b);
		frame.push(arithmetic.value());	
	}

}
