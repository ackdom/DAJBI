package cz.cvut.fit.dajbi.instruction.store;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.Field;
import cz.cvut.fit.dajbi.stack.Frame;

public class PUTFIELD extends Instruction {

	public PUTFIELD(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		short index = frame.getReader().readShort();
		Field field = frame.getClassFile().getField(index);
		Object value = frame.pop();
		HeapHandle handle = (HeapHandle) frame.pop();
		
//		HeapHandle handle = Heap.getInstance().getObject(ref);
		handle.setFieldData(field, value);
		
		handle.DecReferences();
	}

}
