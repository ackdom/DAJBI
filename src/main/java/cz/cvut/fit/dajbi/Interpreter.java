package cz.cvut.fit.dajbi;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.instruction.InstructionFactory;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.stack.Frame;
import cz.cvut.fit.dajbi.stack.SystemStack;

public class Interpreter {

	SystemStack stack;

	public Interpreter() {
		stack = new SystemStack();
	}

	public void run(ClassFile startClass) {
		Method main = startClass.getMain();
		run(startClass, main);
	}

	public void run(ClassFile startClass, Method startMethod) {
		call(startClass, startMethod);
		runloop();
	}

	public void call(ClassFile cf, Method method) {
		call(cf, method, new ArrayList<Object>());
	}

	public void call(ClassFile cf, Method method, List<Object> args) {
		DAJBI.logger.error("Called method " + method.getName() + " "
				+ method.getDescription());
		if (method.getCodeAttribute() == null) {

			callNative(cf, method, args);
			return;

		}
		Frame newFrame = stack.newFrame(cf, method);
		newFrame.setInterpreter(this);
		for (int i = 0; i < args.size(); i++) {
			newFrame.setLocal(i, args.get(i));
		}

	}

	private void callNative(ClassFile cf, Method method, List<Object> args) {

		Object returnValue = null;
		if (method.getName().equals("println")) {
			System.out.println(args.get(1));
		}
		if (method.getName().equals("getSecret")) {
			returnValue = 42;
		}
		
		
		if(returnValue != null) {
			stack.top().push(returnValue);
		}
		
	}

	private void runloop() {

		DAJBI.logger.debug("Runloop started");
		while (!stack.isEmpty()) {

			Frame top = stack.top();

			// tohle asi neni potreba kdyz bude return :)
			if (!top.getReader().hasNext()) {
				stack.pop();
				continue;
			}

			Instruction byCode = InstructionFactory.byCode(top.getReader()
					.readByteToUInt(), top);
			System.out
					.println("Instrukce " + byCode.getClass().getSimpleName());
			byCode.execute();

		}
		DAJBI.logger.debug("Runloop ended");

	}

	/**
	 * @return the stack
	 */
	public SystemStack getStack() {
		return stack;
	}

	/**
	 * @param stack
	 *            the stack to set
	 */
	public void setStack(SystemStack stack) {
		this.stack = stack;
	}

}
