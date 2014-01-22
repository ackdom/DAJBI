package cz.cvut.fit.dajbi.instruction.load;

import java.util.Map;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.Field;
import cz.cvut.fit.dajbi.stack.Frame;

public class GETFIELD extends Instruction {

	public GETFIELD(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		short index = frame.getReader().readShort();
		Field field = frame.getClassFile().getField(index);
		Long ref = (Long) frame.pop();
		
		HeapHandle object = Heap.getInstance().getObject(ref);
		Map<String, Object> instanceData = object.getInstanceData();
		frame.push(instanceData.get(field.getName()));
	}

}
