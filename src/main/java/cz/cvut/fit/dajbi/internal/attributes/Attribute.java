package cz.cvut.fit.dajbi.internal.attributes;
import cz.cvut.fit.dajbi.parser.AttributeReader;
import cz.cvut.fit.dajbi.parser.ClassFileReader;

public class Attribute {
	
	public static enum Type {
		Code
	}

	
	AttributeReader attributeReader;
	
	short attributeNameIndex;
	int attributeLength;
	
	
	public Attribute(AttributeReader attributeReader) {
		
		this.attributeReader = attributeReader;		
		attributeNameIndex = attributeReader.getReader().readShort();
		attributeLength = attributeReader.getReader().readInt();	
	
	}
	
	

	

}
