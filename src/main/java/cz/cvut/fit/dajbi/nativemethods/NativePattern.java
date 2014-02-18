package cz.cvut.fit.dajbi.nativemethods;

import java.util.List;
import java.util.regex.Pattern;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.stack.SystemStack;

public class NativePattern {

	public static void callNative(ClassFile cf, Method method, List<Object> args, SystemStack stack) {
		if (method.getName().equals("compile2")) {
			String regex = Heap.getInstance().getString((HeapHandle) args.get(0));
			Pattern pattern = Pattern.compile(regex);
			long nativeRef = Heap.getInstance().allocNative(pattern);
			stack.top().push(nativeRef);
			return;
		}
		if (method.getName().equals("split2")) {
			Pattern pattern = (Pattern) Heap.getInstance().getNative((Long) args.get(3));
			String str = Heap.getInstance().getString((HeapHandle) args.get(1));
			String[] strings = pattern.split(str, (Integer) args.get(2));
			long arrRef = Heap.getInstance().allocArray(null, strings.length);
			Object[] array = Heap.getInstance().getArray(arrRef);
			for (int i = 0; i < array.length; i++) {
				HeapHandle strRef = Heap.getInstance().allocString(strings[i]);
				array[i] = strRef;
			}
			stack.top().push(arrRef);
			return;
		}
		
		//Method not found
		throw new AbstractMethodError(method.getName() + " " + method.getDescription() + "  cl: " + cf.getName());
	}

}
