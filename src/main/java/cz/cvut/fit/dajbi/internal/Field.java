package cz.cvut.fit.dajbi.internal;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.internal.attributes.Attribute;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolUTF8;
import cz.cvut.fit.dajbi.parser.AttributeReader;
import cz.cvut.fit.dajbi.parser.ClassFileReader;

public class Field {
	
	
	
	short accessFlags;
	short nameIndex;
	short descriptorIndex;
	short attributesCount;
	Attribute[] attributes;
	ClassFileReader classFileReader;	
	Object value;

	public Field(ClassFileReader classFileReader) {
		
		this.classFileReader = classFileReader;
		
		accessFlags = classFileReader.getReader().readShort();
		nameIndex = classFileReader.getReader().readShort();
		descriptorIndex = classFileReader.getReader().readShort();
		attributesCount = classFileReader.getReader().readShort();
		
		DAJBI.logger.trace("FIELD "+getName()+" and descritpion: "+getDescription());

		attributes = new Attribute[attributesCount];
		AttributeReader attributeReader = new AttributeReader(classFileReader);
		for (int i = 0; i < attributesCount; i++) {
			attributes[i] = attributeReader.readAttribute();
		}
		
			
	}
	
	public boolean hasFlag(int flag) {
		return (flag & accessFlags) > 0 ? true : false;
	}
	
	
	
	
	public String getName() {
		return classFileReader.getClassFile().getConstantPool().getItem(nameIndex, ConstantPoolUTF8.class).getTitle();
	}
	
	public String getDescription() {
		return classFileReader.getClassFile().getConstantPool().getItem(descriptorIndex, ConstantPoolUTF8.class).getTitle();
	}




	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}




	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}
