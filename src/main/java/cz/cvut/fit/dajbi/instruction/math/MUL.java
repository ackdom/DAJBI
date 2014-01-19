package cz.cvut.fit.dajbi.instruction.math;

import cz.cvut.fit.dajbi.stack.Frame;

public class MUL<T extends Number> extends ArithemticInstruction<T> {

	public MUL(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		arithmetic.add(a);
		arithmetic.mul(b);
		frame.push(arithmetic.value());	
	}

}
