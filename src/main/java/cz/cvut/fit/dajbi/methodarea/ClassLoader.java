package cz.cvut.fit.dajbi.methodarea;


import java.io.File;

import cz.cvut.fit.dajbi.internal.VMSettings;

public class ClassLoader {

	
	public static String classLookUp(String cl) {
		File f;
		cl = cl.replace('.', '/');
		
		String[] classPaths = {VMSettings.COMPILED_CLASSES, VMSettings.RUNTIME_CLASSES};
		
		
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
		
		return null;	
	}
	
}
