package cz.cvut.fit.dajbi.methodarea;


import java.io.File;

import cz.cvut.fit.dajbi.internal.VMSettings;

public class ClassLoader {

	
	public static String classLookUp(String cl) {
		File f;
		cl = cl.replace('.', '/');
		//run in loop
		//Look for JAR
		//Look for ClassPath
		//Look for JRE
		
		//Look for Compiled Classes	
		f = new File(VMSettings.COMPILED_CLASSES+cl+".class");
		if(f.isFile() && f.canRead()) {
			return f.getPath();
		}
		
		return null;	
	}
	
}
