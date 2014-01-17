package cz.cvut.fit.dajbi.instruction.store;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.Field;
import cz.cvut.fit.dajbi.stack.Frame;

public class PUTSTATIC extends Instruction {

	public PUTSTATIC(Frame f) {
		super(f);
		
	}

	@Override
	public void execute() {
		short index = frame.getReader().readShort();
		Field field = frame.getClassFile().getField(index);
		field.setValue(frame.pop());
		

	}

}
