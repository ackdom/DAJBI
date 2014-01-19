package cz.cvut.fit.dajbi.instruction.math;

import cz.cvut.fit.dajbi.stack.Frame;

public class SUB<T extends Number> extends ArithemticInstruction<T> {
	
	

	
	public SUB(Frame f) {
		super(f);
	}

	@Override
	public void execute() {
		arithmetic.add(a);
		arithmetic.sub(b);
		frame.push(arithmetic.value());
	}

}
