package cz.cvut.fit.dajbi.internal.attributes;
import cz.cvut.fit.dajbi.parser.ClassFileReader;

public class Attribute {

	
	short attributeNameIndex;
	int attributeLength;
	
	
	public Attribute(ClassFileReader classFileReader) {
		
		attributeNameIndex = classFileReader.getReader().readShort();
		attributeLength = classFileReader.getReader().readShort();	
	
	}
	
	

	

}
