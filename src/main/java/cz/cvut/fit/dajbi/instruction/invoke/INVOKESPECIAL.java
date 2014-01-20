package cz.cvut.fit.dajbi.instruction.invoke;

import java.util.List;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolMethodRef;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;
import cz.cvut.fit.dajbi.stack.Frame;

public class INVOKESPECIAL extends Instruction {

	public INVOKESPECIAL(Frame f) {
		super(f);

	}

	@Override
	public void execute() {
		ConstantPoolMethodRef item = frame
				.getClassFile()
				.getConstantPool()
				.getItem(frame.getReader().readShort(),
						ConstantPoolMethodRef.class);
		
		ClassFile cf = ClassResolver.resolveWithLookup(item.getClassRef()
				.getName());
		Method method = cf.getMethod(item.getNameAndType().getName(), item
				.getNameAndType().getDescriptor());
		int attributesCount = method.getAttributesCount();
		List<Object> pop = frame.popArgList(attributesCount + 1);
		frame.getInterpreter().call(cf, method, pop);
	}

}
