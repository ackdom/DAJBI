package cz.cvut.fit.dajbi.instruction.math;

import com.stefanmuenchow.arithmetic.Arithmetic;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class ArithemticInstruction<T extends Number> extends Instruction {

	Arithmetic<T> arithmetic;
	T a;
	T b;

	@SuppressWarnings("unchecked")
	public ArithemticInstruction(Frame f) {
		super(f);
		a = (T) frame.pop();
		b = (T) frame.pop();
		arithmetic = new Arithmetic<T>(a.getClass());
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
