package cz.cvut.fit.dajbi.instruction.stack;

import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class DUP extends Instruction {

	public DUP(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		Object obj = frame.top();
		
		if (obj != null && obj instanceof HeapHandle) {
			((HeapHandle) obj).IncReferences();
		}
		
		frame.push(obj);
	}

}
