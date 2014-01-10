package cz.cvut.fit.dajbi.parser;

import cz.cvut.fit.dajbi.internal.attributes.Attribute;
import cz.cvut.fit.dajbi.internal.attributes.CodeAttribute;
import cz.cvut.fit.dajbi.internal.attributes.Attribute.Type;
import cz.cvut.fit.dajbi.internal.attributes.NotImplementedAttribute;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPool;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolUTF8;

public class AttributeReader {
	
	
	
	Reader reader;
	ClassFileReader classReader;
	ConstantPool constantPool;

	/**
	 * @param reader
	 */
	public AttributeReader(ClassFileReader classFileReader) {
		super();
		this.classReader = classFileReader;
		this.reader = classFileReader.getReader();
		this.constantPool = classFileReader.getClassFile().getConstantPool();
	}
	
	public Attribute readAttribute() {
		
		short index = reader.peekShort();
		String type = ((ConstantPoolUTF8)constantPool.getItems()[index]).getTitle();
		Type valueOf = Attribute.Type.valueOf(type);
		
		switch (valueOf) {
		case Code:			
			return new CodeAttribute(this);

		default:
			return new NotImplementedAttribute(this);
			
			
		}			
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
	 * @return the classReader
	 */
	public ClassFileReader getClassReader() {
		return classReader;
	}

	/**
	 * @param classReader the classReader to set
	 */
	public void setClassReader(ClassFileReader classReader) {
		this.classReader = classReader;
	}

	/**
	 * @return the constantPool
	 */
	public ConstantPool getConstantPool() {
		return constantPool;
	}

	/**
	 * @param constantPool the constantPool to set
	 */
	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}
	
	

}
