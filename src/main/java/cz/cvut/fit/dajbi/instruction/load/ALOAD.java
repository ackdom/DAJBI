package cz.cvut.fit.dajbi.instruction.load;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class ALOAD extends Instruction {

	public ALOAD(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void execute() {		
		Object local = frame.getLocal(frame.getReader().readByteToUInt());
		frame.push(local);
	}

}
