package cz.cvut.fit.dajbi.instruction.stack;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.stack.Frame;

public class ACONSTNULL extends Instruction {

	public ACONSTNULL(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		HeapHandle handle = new HeapHandle(null, Heap.NULL);
		frame.push(handle);
	}

}
