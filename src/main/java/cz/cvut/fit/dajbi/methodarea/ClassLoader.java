package cz.cvut.fit.dajbi.methodarea;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cz.cvut.fit.dajbi.internal.VMSettings;

public class ClassLoader {

	private static List<String> classPaths = new ArrayList<String>(Arrays.asList(VMSettings.COMPILED_CLASSES, VMSettings.RUNTIME_CLASSES, VMSettings.RUNTIME_CLASSES2));
	
	
	public static String classLookUp(String cl) throws ClassNotFoundException {
		File f;
		cl = cl.replace('.', '/');
		
		
		
		//run in loop
		//Look for JAR
		//Look for ClassPath
		//Look for JRE
		
		//Look for Compiled Classes	
		for(String path : classPaths) {
			f = new File(path+cl+".class");
			if(f.isFile() && f.canRead()) {
				return f.getPath();
			}
		}		
		
		throw new ClassNotFoundException(cl);	
	}
	
	public static void addClassPath(String classPath) {
		classPaths.add(classPath);
	}
}
