package cz.cvut.fit.dajbi;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.methodarea.ClassLoader;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;


public class DAJBI {

	public static final Logger logger = Logger.getLogger(DAJBI.class);

	public static void main(String[] args) {

		// Configure logger
		BasicConfigurator.configure();
		logger.debug("Hello World!");
		
		if (args.length < 1) {
			System.out.println("Usage:");
			System.out.println("[-cp <classPath>] <mainClass>");
			System.out.println("OR");
			System.out.println("-jar <jarArchive>");
			System.exit(1);
		}
		
		if (args[0].equals("-cp")) {
			run(args[1], args[2]);
		} else if (args[0].equals("-jar")) {
			runJar(args[1]);
		} else {
			run(args[0]);
		}
//		ClassFile paa = ClassResolver.resolveClass("C:/Users/Jakub/workspace/fit/paa/du1/bin/paa/Main.class");
//		Interpreter interpreter = new Interpreter();
//		interpreter.run(paa);
	}

	private static void run(String mainClass) {
		ClassFile classFile = ClassResolver.resolveClass(mainClass);
		Interpreter interpreter = new Interpreter();
		interpreter.run(classFile);
	}

	private static void runJar(String jarArchive) {
		// TODO Auto-generated method stub
		
	}

	private static void run(String classPath, String mainClass) {
		ClassLoader.addClassPath(classPath);
		ClassFile classFile = ClassResolver.resolveWithLookup(mainClass);
		Interpreter interpreter = new Interpreter();
		interpreter.run(classFile);
	}

}
