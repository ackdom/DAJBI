package cz.cvut.fit.dajbi.instruction.math;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class IINC extends Instruction {

	public IINC(Frame f) {
		super(f);
	}

	@Override
	public void execute() {
		Integer local = (Integer) frame.getLocal(frame.getReader().peekByteAsUInt()); 
		frame.setLocal(frame.getReader().readByteToUInt(), local+frame.getReader().readByte());
	}

}
