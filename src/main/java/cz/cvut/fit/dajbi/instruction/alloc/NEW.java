package cz.cvut.fit.dajbi.instruction.alloc;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolClass;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;
import cz.cvut.fit.dajbi.stack.Frame;

public class NEW extends Instruction {

	public NEW(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		ConstantPoolClass classRef = (frame.getClassFile().getConstantPool().getItem(frame.getReader().readShort(), ConstantPoolClass.class));
		ClassFile cf = ClassResolver.resolveWithLookup(classRef.getName());
		long heapRef = Heap.getInstance().allocObject(cf);
		frame.push(heapRef);
	}

}
