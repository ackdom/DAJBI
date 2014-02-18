package cz.cvut.fit.dajbi.nativemethods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.heap.NativeObjectHandle;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.stack.SystemStack;

public class NativeFileWriter {
	
	public static void callNative(ClassFile cf, Method method, List<Object> args, SystemStack stack) {
		if (method.getName().equals("open")) {
			try {
				String file = Heap.getInstance().getString((HeapHandle) args.get(1));
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				long nativeRef = Heap.getInstance().allocNative(writer);
				stack.top().push(nativeRef);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if (method.getName().equals("close")) {
			BufferedWriter writer = (BufferedWriter) Heap.getInstance().getNative(((Long) args.get(1)));
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		if (method.getName().equals("write")) {
			try {
				Object writer = Heap.getInstance().getNative(((Long) args.get(1)));
				String line = Heap.getInstance().getString((HeapHandle) args.get(2));
				((BufferedWriter) writer).write(line);
//				long nativeRef = Heap.getInstance().allocNative(line);
//				stack.top().push(new NativeObjectHandle(line));
//				stack.top().push(Heap.getInstance().allocString(line));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		//Method not found
		throw new AbstractMethodError(method.getName() + " " + method.getDescription() + "  cl: " + cf.getName());
	}

}
