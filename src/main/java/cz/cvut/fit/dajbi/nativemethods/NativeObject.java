package cz.cvut.fit.dajbi.nativemethods;

import java.util.List;

import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.stack.SystemStack;

public class NativeObject {

	public static void callNative(ClassFile cf, Method method, List<Object> args, SystemStack stack) {
		if (method.getName().equals("registerNatives")) {
			return;
		}
		
		//Method not found
		throw new AbstractMethodError(method.getName() + " " + method.getDescription() + "  cl: " + cf.getName());
	}

}
