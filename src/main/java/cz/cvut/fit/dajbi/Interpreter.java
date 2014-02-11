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
			Object arg = args.get(1);
			if (arg instanceof NativeObjectHandle) {
				arg = ((NativeObjectHandle) arg).getInstanceData();
			}
//			String str = (String) Heap.getInstance().getNative((Long) arg);
			System.out.println(arg);
			return;
		}
		if (method.getName().equals("getSecret")) {
			returnValue = 42;
		}
		if (method.getName().equals("print")) {
			System.out.print(args.get(1));
		}
		if (method.getName().equals("open")) {
			if (cf.getName().endsWith("FileReader")) {
				try {
					BufferedReader reader = new BufferedReader(new FileReader((String) args.get(1)));
					long nativeRef = Heap.getInstance().allocNative(reader);
					stack.top().push(nativeRef);
					return;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (method.getName().equals("readLine")) {
			try {
				Object reader = Heap.getInstance().getNative(((Long) args.get(1)));
				String line = ((BufferedReader) reader).readLine();
//				long nativeRef = Heap.getInstance().allocNative(line);
				stack.top().push(new NativeObjectHandle(line));
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
