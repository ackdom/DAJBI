package cz.cvut.fit.dajbi.instruction.load;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.Field;
import cz.cvut.fit.dajbi.stack.Frame;

public class GETSTATIC extends Instruction {

	public GETSTATIC(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		short index = frame.getReader().readShort();
		
		Field field = frame.getClassFile().getField(index);
		
		frame.push(field.getValue());

	}

}
