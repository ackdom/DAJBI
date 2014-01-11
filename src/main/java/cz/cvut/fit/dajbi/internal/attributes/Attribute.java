package cz.cvut.fit.dajbi.internal.attributes;
import cz.cvut.fit.dajbi.parser.AttributeReader;

public class Attribute {
	
	public static enum Type {
		Code,NotImplemented
	}
	
	public static Type getType(String string) {
		
		Type t = null;
		try {
			t = Type.valueOf(string);
		} catch (Exception e) {
			t = Type.NotImplemented;
			
		}
		return t;		
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
