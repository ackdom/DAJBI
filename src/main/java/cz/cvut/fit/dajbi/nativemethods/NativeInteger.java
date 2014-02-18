package cz.cvut.fit.dajbi.nativemethods;

import java.util.List;
import java.util.regex.Pattern;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.stack.SystemStack;

public class NativeInteger {

	public static void callNative(ClassFile cf, Method method, List<Object> args, SystemStack stack) {
		if (method.getName().equals("parseInt")) {
			String str = Heap.getInstance().getString((HeapHandle) args.get(0));
			int i = Integer.parseInt(str);
			stack.top().push(i);
			return;
		}
		if (method.getName().equals("toString")) {
			HeapHandle str = Heap.getInstance().allocString(String.valueOf(args.get(0)));
			stack.top().push(str);
			return;
		}
		
		//Method not found
		throw new AbstractMethodError(method.getName() + " " + method.getDescription() + "  cl: " + cf.getName());
	}

}
