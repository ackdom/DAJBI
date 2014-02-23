package cz.cvut.fit.dajbi.instruction.math;

import cz.cvut.fit.dajbi.stack.Frame;

public class DIV<T extends Number> extends ArithemticInstruction<T> {

	public DIV(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		arithmetic.add(a);
		arithmetic.div(b);
		frame.push(arithmetic.value());	
	}

}
