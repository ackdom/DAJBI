package cz.cvut.fit.dajbi.instruction.math;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class LCMP extends Instruction {

	public LCMP(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		Long val2 = (Long) frame.pop();
		Long val1 = (Long) frame.pop();
		if(val1 > val2) {
			frame.push(new Integer(1));
		} else if (val1 == val2) {
			frame.push(new Integer(0));
		} else if (val1 < val2) {
			frame.push(new Integer(-1));
		}
	}

}
