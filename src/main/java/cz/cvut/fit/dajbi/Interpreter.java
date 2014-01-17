package cz.cvut.fit.dajbi;

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
		call(startClass, main);
		runloop();
	}

	public void run(ClassFile read, Method clinit) {
		call(read, clinit);
		runloop();
	}

	public void call(ClassFile cf, Method method) {
		DAJBI.logger.error("Called method "+method.getName()+" "+method.getDescription());
		Frame newFrame = stack.newFrame(cf, method);
		newFrame.setInterpreter(this);
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
			byCode.execute();
			System.out.println("Instrukce "+byCode.getClass().getSimpleName());

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
