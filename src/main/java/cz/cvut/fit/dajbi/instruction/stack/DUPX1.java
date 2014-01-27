package cz.cvut.fit.dajbi.instruction.stack;

import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class DUPX1 extends Instruction {

	public DUPX1(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		Object obj1 = frame.pop();
		Object obj2 = frame.pop();
		frame.push(obj1);
		frame.push(obj2);
		frame.push(obj1);
		
		if (obj1 != null && obj1 instanceof HeapHandle) {
			((HeapHandle) obj1).IncReferences();
		}
	}

}
