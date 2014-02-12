package cz.cvut.fit.dajbi.nativemethods;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.NativeObjectHandle;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.stack.SystemStack;

public class NativeFileReader {
	
	public static void callNative(ClassFile cf, Method method, List<Object> args, SystemStack stack) {
		if (method.getName().equals("open")) {
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
		if (method.getName().equals("close")) {
			BufferedReader reader = (BufferedReader) Heap.getInstance().getNative(((Long) args.get(1)));
			try {
				reader.close();
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		
		//Method not found
		throw new AbstractMethodError(method.getName() + " " + method.getDescription() + "  cl: " + cf.getName());
	}

}
