package cz.cvut.fit.dajbi.instruction.invoke;

import java.util.List;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolMethodRef;
import cz.cvut.fit.dajbi.stack.Frame;

public class INVOKEVIRTUAL extends Instruction {

	public INVOKEVIRTUAL(Frame f) {
		super(f);
	}

	@Override
	public void execute() {
		ConstantPoolMethodRef item = frame.getClassFile().getConstantPool().getItem(frame.getReader().readShort(), ConstantPoolMethodRef.class);
		int attributesCount = Method.getMethodArgumentCount(item.getNameAndType().getDescriptor())+1;
		List<Object> pop = frame.popArgList(attributesCount);
		ClassFile cf = ((HeapHandle)pop.get(0)).getClassFile();
		Method method = cf.getMethod(item.getNameAndType().getName(), item.getNameAndType().getDescriptor());
		
		
		frame.getInterpreter().call(cf, method, pop);
		
	}

}
