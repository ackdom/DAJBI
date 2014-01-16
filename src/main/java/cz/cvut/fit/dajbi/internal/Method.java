package cz.cvut.fit.dajbi.internal;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.internal.attributes.Attribute;
import cz.cvut.fit.dajbi.internal.attributes.CodeAttribute;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolUTF8;
import cz.cvut.fit.dajbi.parser.AttributeReader;
import cz.cvut.fit.dajbi.parser.ClassFileReader;

public class Method {

	short accessFlags;
	short nameIndex;
	short descriptorIndex;
	short attributesCount;
	Attribute[] attributes;
	ClassFileReader classFileReader;
	CodeAttribute codeAttribute;
	
	public Method(ClassFileReader classFileReader) {
		
		this.classFileReader = classFileReader;
		
		accessFlags = classFileReader.getReader().readShort();
		nameIndex = classFileReader.getReader().readShort();
		descriptorIndex = classFileReader.getReader().readShort();
		attributesCount = classFileReader.getReader().readShort();
		
		DAJBI.logger.trace("METHOD "+getName()+" and descritpion: "+getDescription());
		
		
		attributes = new Attribute[attributesCount];
		AttributeReader attributeReader = new AttributeReader(classFileReader);
		for (int i = 0; i < attributesCount; i++) {
			
			attributes[i] = attributeReader.readAttribute();
			if(attributes[i] instanceof CodeAttribute) {
				codeAttribute = (CodeAttribute) attributes[i];
			}
		}		
			
	}
	
	
	
	
	public String getName() {
		return classFileReader.getClassFile().getConstantPool().getItem(nameIndex, ConstantPoolUTF8.class).getTitle();

	}
	public String getDescription() {
		return classFileReader.getClassFile().getConstantPool().getItem(descriptorIndex, ConstantPoolUTF8.class).getTitle();
	}

	/**
	 * @return the codeAttribute
	 */
	public CodeAttribute getCodeAttribute() {
		return codeAttribute;
	}

}
