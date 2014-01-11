package cz.cvut.fit.dajbi.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Field;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.internal.attributes.Attribute;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPool;

public class ClassFileReader {

	/**
	 * Path to ClassFile
	 */
	String fileName;
	Reader reader;
	ClassFile classFile;

	/**
	 * Current index in classfile
	 */
	int index;

	final int magicKeyWord = 0xCAFEBABE;

	public ClassFileReader(String fileName) {
		this.fileName = fileName;
		this.index = 0;

		try {
			this.reader = new Reader(IOUtils.toByteArray(new FileInputStream(
					fileName)));
		} catch (FileNotFoundException e) {
			DAJBI.logger.error("Could not find class file", e);
		} catch (IOException e) {
			DAJBI.logger.error("Could read find class file", e);
		}
	}

	/**
	 * Checks Whether ClassFile is loaded or not
	 * 
	 * @return true if bytes array isnt null
	 */
	public boolean isLoaded() {
		return (reader == null || reader.bytes == null || reader.bytes.length == 0) ? false
				: true;
	}

	/**
	 * Checks whether its Valid classFile has to be loaded and needs to have
	 * 0xCAFEBABE
	 * 
	 * @return true when valid false otherwise
	 */
	public boolean isValid() {
		int cafe = 0;
		for (index = 0; index < 4; index++) {
			int shift = (4 - 1 - index) * 8;
			cafe += (reader.readByte() & 0x000000FF) << shift;
		}

		if (cafe == magicKeyWord)
			return true;

		return false;
	}

	public ClassFile read() {
		if (!isLoaded() || !isValid())
			return null ;

		ClassFile classFile = new ClassFile();
		this.classFile = classFile;
		
		classFile.setMinorVersion(reader.readShort());
		classFile.setMajorVersion(reader.readShort());
		classFile.setConstantPoolCount(((short)reader.readShort()-1));
		ConstantPool pool = new ConstantPool(reader,
				classFile.getConstantPoolCount());
		classFile.setConstantPool(pool);
		classFile.setAccessFlags(reader.readShort());
		classFile.setThisClass(reader.readShort());
		classFile.setSuperClass(reader.readShort());
		classFile.setInterfacesCount(reader.readShort());

		int[] interfaces = new int[classFile.getInterfacesCount()];
		for (int i = 0; i < classFile.getInterfacesCount(); i++) {
			interfaces[i] = reader.readShort();
		}
		
		classFile.setFieldsCount(reader.readShort());
		Field[] fields = new Field[classFile.getFieldsCount()];
		for(int i = 0; i < classFile.getFieldsCount();i++) {
			fields[i] = new Field(this);
		}
		classFile.setFields(fields);
		
		classFile.setMethodsCount(reader.readShort());
		Method[] methods = new Method[classFile.getMethodsCount()];
		for(int i = 0; i < classFile.getMethodsCount();i++) {
			methods[i] = new Method(this);
		}
		classFile.setMethods(methods);
		
		classFile.setAttributesCount(reader.readShort());
		Attribute[] attributes = new Attribute[classFile.getAttributesCount()];
		AttributeReader attributereader = new AttributeReader(this);
		for(int i = 0; i < classFile.getAttributesCount();i++) {
			attributes[i] = attributereader.readAttribute();
		}
		classFile.setAttributes(attributes);
		
		

		return classFile;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the reader
	 */
	public Reader getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Reader reader) {
		this.reader = reader;
	}

	/**
	 * @return the classFile
	 */
	public ClassFile getClassFile() {
		return classFile;
	}

	/**
	 * @param classFile the classFile to set
	 */
	public void setClassFile(ClassFile classFile) {
		this.classFile = classFile;
	}
	
	

}
