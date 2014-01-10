package cz.cvut.fit.dajbi.internal.attributes;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.parser.AttributeReader;

public class CodeAttribute extends Attribute {

	
	//DUMMY NA PRESKOCENI EXCEPTION
	static final int exceptionTable = 8;
	
	
	short maxStack;
	short maxLocals;
	int codeLength;
	byte[] code;
	short exceptionTableLength;
	short attributesCount;
	Attribute[] attributes;
	
	
	
	public CodeAttribute(AttributeReader attributeReader) {
		super(attributeReader);
		
		maxStack = attributeReader.getReader().readShort();
		maxLocals = attributeReader.getReader().readShort();
		codeLength = attributeReader.getReader().readInt();
		code = attributeReader.getReader().readBytes(codeLength);
		exceptionTableLength = attributeReader.getReader().readShort();
		//hack abysme nemuseli implementovat excpetiony zatim
		attributeReader.getReader().readBytes(exceptionTableLength*exceptionTable);
		
		attributesCount = attributeReader.getReader().readShort();
		
		DAJBI.logger.trace(" === Code ATTRIBUTE START ===");
		attributes =  new Attribute[attributesCount];
		for(int i = 0; i < attributesCount; i++) {
			attributes[i] = attributeReader.readAttribute();
		}
		DAJBI.logger.trace(" === Code ATTRIBUTE END ===");
		
				

	}

}
