package cz.cvut.fit.dajbi.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.sun.org.apache.bcel.internal.generic.IINC;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.internal.ClassFile;

public class ClassFileReader {

	/**
	 * Path to ClassFile
	 */
	String fileName;

	byte[] bytes;

	/**
	 * Current index in classfile
	 */
	int index;

	final int magicKeyWord = 0xCAFEBABE;

	public ClassFileReader(String fileName) {
		this.fileName = fileName;
		this.index = 0;
				
		try {
			this.bytes = IOUtils.toByteArray(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			DAJBI.logger.error("Could not find class file", e);
		} catch (IOException e) {
			DAJBI.logger.error("Could read find class file", e);
		}
	}
	
	
	/**
	 * Checks Whether ClassFile is loaded or not
	 * @return true if bytes array isnt null
	 */
	public boolean isLoaded() {
		return (bytes == null || bytes.length == 0) ? false : true;
	}
	
	/**
	 * Checks whether its Valid classFile has to be loaded and needs to have 0xCAFEBABE
	 * @return true when valid false otherwise
	 */
	public boolean isValid() {
		int cafe = 0;
		for (index = 0; index < 4; index++) {
	        int shift = (4 - 1 - index) * 8;
	        cafe += (bytes[index] & 0x000000FF) << shift;
	    }
		
		if(cafe == magicKeyWord)
			return true;
		
		
		return false;
	}

	public ClassFile read() {
		if (!isValid())
			return null;
		
		

		return null;
	}

}
