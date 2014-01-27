package cz.cvut.fit.dajbi.internal;



import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.internal.attributes.Attribute;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolUTF8;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;
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
	
	private final int fieldDataOffset; 

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
		
		fieldDataOffset = classFileReader.getFieldDataOffset();
		classFileReader.setFieldDataOffset(fieldDataOffset + getSize());
			
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

	public String getFieldsClassName() {
		String s = getDescription();
		return s.substring(1, s.length()-1); //oreze "L" na zacatku a ";" na konci 
	}
	
	public ClassFile getFieldsClassFile() {
		return ClassResolver.resolveWithLookup(getFieldsClassName());
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
	
	/**
	 * Returns size of data contained in this field.
	 * @return size in bytes
	 */
	public int getSize() {
		switch (getDescription().charAt(0)) {
		//byte
		case 'B':
		//boolean
		case 'Z':
			return 1;
			
		//char
		case 'C':
		//short
		case 'S':
			return 2;
			
		//float
		case 'F':
		//int
		case 'I':
			return 4;
			
		//double
		case 'D':
		//long
		case 'J':
			return 8;
			
		//reference (class)
		case 'L':
			return 8;
			
		//reference (array)
		case '[':
			throw new UnsupportedOperationException();

		default:
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * index offset of field data on heap 
	 * @return
	 */
	public int getFieldDataOffset() {
		return fieldDataOffset;
	}

}
