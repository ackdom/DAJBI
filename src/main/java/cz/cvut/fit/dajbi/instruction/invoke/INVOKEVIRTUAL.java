package cz.cvut.fit.dajbi.instruction.invoke;

import java.util.List;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolClass;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolMethodRef;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;
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
		
		Method method;
		ClassFile classFile = cf;
		do {
			method = classFile.getMethod(item.getNameAndType().getName(), item.getNameAndType().getDescriptor());
			if (method == null) {
				classFile = classFile.getSuperClassCF();
				if (classFile == null) {
					throw new AbstractMethodError(item.getNameAndType().getName() + " " + item.getNameAndType().getDescriptor());
				}
			}
		} while (method == null);
		
		
		frame.getInterpreter().call(classFile, method, pop);
		
	}

}
