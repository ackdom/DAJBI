package cz.cvut.fit.dajbi;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.methodarea.ClassLoader;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;


public class DAJBI {

	public static final Logger logger = Logger.getLogger(DAJBI.class);

	public static void main(String[] args) {

		// Configure logger
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
		logger.debug("Hello World!");
		
		if (args.length < 1) {
			System.out.println("Usage:");
			System.out.println("[-cp <classPath>] <mainClass>");
			System.out.println("OR");
			System.out.println("-jar <jarArchive>");
			System.exit(1);
		}
		
		if (args[0].equals("-cp")) {
			run(args[1], args[2], getArgs(args, 3));
		} else if (args[0].equals("-jar")) {
			runJar(args[1], getArgs(args, 2));
		} else {
			run(args[0], getArgs(args, 1));
		}

		logger.debug("Program ended");
	}

	private static void run(String mainClass, List<Object> args) {
		ClassFile classFile = ClassResolver.resolveClass(mainClass);
		Interpreter interpreter = new Interpreter();
		interpreter.run(classFile, args);
	}

	private static void runJar(String jarArchive, List<Object> args) {
		// TODO Auto-generated method stub
		
	}

	private static void run(String classPath, String mainClass, List<Object> args) {
		ClassLoader.addClassPath(classPath);
		ClassFile classFile = ClassResolver.resolveWithLookup(mainClass);
		Interpreter interpreter = new Interpreter();
		interpreter.run(classFile, args);
	}
	
	private static List<Object> getArgs(String[] args, int firstArg) {
		int size = args.length - firstArg;
		long arrRef = Heap.getInstance().allocArray(null, size);
		Object[] array = Heap.getInstance().getArray(arrRef);
		for (int i = firstArg; i < args.length; i++) {
			HeapHandle argRef = Heap.getInstance().allocString(args[i]);
			array[i-firstArg] = argRef;
		}
		List<Object> res = new ArrayList<Object>();
		res.add(arrRef);
		return res;
	}

}
