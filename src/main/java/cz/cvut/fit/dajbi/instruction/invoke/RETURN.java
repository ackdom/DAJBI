package cz.cvut.fit.dajbi.instruction.invoke;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class RETURN extends Instruction {

	boolean retValue = false;
	
	public RETURN(Frame f, boolean ret) {
		super(f);
		retValue = ret;
	}

	public RETURN(Frame f) {
		super(f);
	}

	@Override
	public void execute() {
		if(retValue && frame.top() != null) {
			frame.getInvoker().push(frame.pop());
		}
		frame.getInterpreter().getStack().pop();
	}

}
