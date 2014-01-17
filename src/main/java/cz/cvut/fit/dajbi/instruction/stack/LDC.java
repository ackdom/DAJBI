package cz.cvut.fit.dajbi.instruction.stack;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolItem;
import cz.cvut.fit.dajbi.stack.Frame;

public class LDC extends Instruction {

	
	boolean wide;
	
	public LDC(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	public LDC(Frame f, boolean b) {
		super(f);
		wide = b;
	}

	@Override
	public void execute() {
		int index = (this.wide) ? frame.getReader().readShort() : frame.getReader().readByteToUInt();
		ConstantPoolItem item = frame.getClassFile().getConstantPool().getItem(index);
		frame.push(item.valueForLDC());
	}

}
