package cz.cvut.fit.dajbi.instruction.invoke;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPool;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolMethodRef;
import cz.cvut.fit.dajbi.stack.Frame;

public class INVOKESTATIC extends Instruction {

	

	public INVOKESTATIC(Frame f) {
		super(f);
		
		
	}

	@Override
	public void execute() {
		ConstantPoolMethodRef item = frame.getClassFile().getConstantPool().getItem(frame.getReader().readShort(), ConstantPoolMethodRef.class);
	}

}
