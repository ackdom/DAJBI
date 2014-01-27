package cz.cvut.fit.dajbi.instruction.stack;

import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class POP extends Instruction {

	public POP(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		Object obj = frame.pop();
		
		if (obj != null && obj instanceof HeapHandle) {
			((HeapHandle) obj).DecReferences();
		}
	}

}
