package cz.cvut.fit.dajbi.instruction.conditions;

import cz.cvut.fit.dajbi.heap.NullableHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class IFNULL extends Instruction {

	public IFNULL(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		NullableHandle handle = (NullableHandle) frame.pop();
		
		int jump = frame.getReader().readShort();
		if(handle.isNull()) {
			frame.getReader().move(jump);
			frame.getReader().move(-3);
		}
		
		handle.DecReferences();
	}

}
