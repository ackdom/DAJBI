package cz.cvut.fit.dajbi.instruction.alloc;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolClass;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;
import cz.cvut.fit.dajbi.stack.Frame;

public class NEWARRAY extends Instruction {

	boolean typed;

	public NEWARRAY(Frame f, boolean typed) {
		super(f);
		this.typed = typed;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {

		ClassFile type = null;
		int size = (Integer) frame.pop();

		if (typed) {
			String name = frame
					.getClassFile()
					.getConstantPool()
					.getItem(frame.getReader().readShort(),
							ConstantPoolClass.class).getName();
			
			type = ClassResolver.resolveWithLookup(name);			
			frame.push(Heap.getInstance().allocArray(type, size));
			
		} else {
			// reads aType as type of array which doesnt matter for us
			byte t = frame.getReader().readByte();
			frame.push(Heap.getInstance().allocArrayPrim(t, size));
		}
		
		

	}
}
