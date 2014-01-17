package cz.cvut.fit.dajbi.instruction.load;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class ILOAD extends Instruction {

	int index;

	public ILOAD(Frame f) {
		super(f);
		index = (int) frame.getReader().readByte();
		// TODO Auto-generated constructor stub
	}

	public ILOAD(Frame f, int i) {
		super(f);
		index = i;
	}

	@Override
	public void execute() {
		frame.push(frame.getLocal(index));
	}

}
