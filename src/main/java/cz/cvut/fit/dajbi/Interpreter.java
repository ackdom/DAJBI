package cz.cvut.fit.dajbi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.heap.NativeObjectHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.instruction.InstructionFactory;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.nativemethods.NativeFileReader;
import cz.cvut.fit.dajbi.nativemethods.NativeObject;
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
				+ method.getDescription() + " class: " + cf.getName());
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
		
		if (method.getName().equals("println")) {
			Object arg = args.get(1);
			if (arg instanceof NativeObjectHandle) {
				arg = ((NativeObjectHandle) arg).getInstanceData();
			}
//			String str = (String) Heap.getInstance().getNative((Long) arg);
			System.out.println(arg);
			return;
		}
		if (method.getName().equals("print")) {
			Object arg = args.get(1);
			if (arg instanceof NativeObjectHandle) {
				arg = ((NativeObjectHandle) arg).getInstanceData();
			}
			System.out.print(arg);
			return;
		}
		if (cf.getName().equals("java/lang/Object")) {
			NativeObject.callNative(cf, method, args, stack);
			return;
		}
		if (cf.getName().equals("java/io/FileReader")) {
			NativeFileReader.callNative(cf, method, args, stack);
			return;
		}
		if (method.getName().equals("getSecret")) {
			stack.top().push(42);
			return;
		}
		
		
		//Method not found
		throw new AbstractMethodError(method.getName() + " " + method.getDescription() + "  cl: " + cf.getName());
		
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
			DAJBI.logger.debug("Instrukce " + byCode.getClass().getSimpleName());
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
