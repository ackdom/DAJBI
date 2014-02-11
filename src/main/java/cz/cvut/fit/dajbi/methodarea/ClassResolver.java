package cz.cvut.fit.dajbi.methodarea;

import java.util.HashMap;

import cz.cvut.fit.dajbi.Interpreter;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.parser.ClassFileReader;

public class ClassResolver {

	static HashMap<String, ClassFile> classFiles = new HashMap<String, ClassFile>();

	public static ClassFile resolveClass(String str) {
		if (classFiles.containsKey(str)) {
			return classFiles.get(str);
		}

		ClassFileReader cfr = new ClassFileReader(str);
		ClassFile read = cfr.read();
		Method clinit = read.getClinit();
		classFiles.put(str, read);
		if (clinit != null) {
			Interpreter inter = new Interpreter();
			inter.run(read, clinit);
		}

		return read;

	}
	
	public static ClassFile resolveWithLookup(String str) {
		if (classFiles.containsKey(str)) {
			return classFiles.get(str);
		}
		
		try {
			return resolveClass(ClassLoader.classLookUp(str));
		} catch (ClassNotFoundException e) {
			NoClassDefFoundError error = new NoClassDefFoundError();
			error.initCause(e);
			throw error;
		}
	}

}
