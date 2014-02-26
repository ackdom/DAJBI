package cz.cvut.fit.dajbi.instruction;

import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.attributes.ExceptionHandler;
import cz.cvut.fit.dajbi.stack.Frame;

public class ATHROW extends Instruction {

	public ATHROW(Frame f) {
		super(f);
	}

	@Override
	public void execute() {
		HeapHandle handle = (HeapHandle) frame.pop();
		ExceptionHandler exceptionHandler = null;
		while (exceptionHandler == null) {
			exceptionHandler = findHandler(handle);
			while (!frame.isEmpty()) {
				frame.pop();
			}
			if(exceptionHandler == null) {
				frame.getInterpreter().getStack().pop();
				if(!frame.getInterpreter().getStack().isEmpty()) {
					frame = frame.getInterpreter().getStack().top();
				} else {
					throw new UnsupportedOperationException("Unhandled exception");
				}
			}
		}
		
		frame.getReader().moveAt(exceptionHandler.getHandlerPc());
		frame.push(handle);
	}

	private ExceptionHandler findHandler(HeapHandle handle) {
		ExceptionHandler[] exceptionTable = frame.getMethod().getCodeAttribute().getExceptionTable();
		int index = frame.getReader().getIndex();
		for (ExceptionHandler eH : exceptionTable) {
			if(eH.getStartPc() <= index && eH.getEndPc() >= index) {
				ClassFile cf = handle.getClassFile();
				do {
					if (eH.getCatchType().getName()
							.equals(cf.getName())) {
						return eH;
					}
				} while ((cf = cf.getSuperClassCF()) != null);
			}
		}
		return null;
	}

}
