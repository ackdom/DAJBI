package cz.cvut.fit.dajbi.internal.attributes;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolUTF8;
import cz.cvut.fit.dajbi.parser.AttributeReader;

public class NotImplementedAttribute extends Attribute {

	public NotImplementedAttribute(AttributeReader attributeReader) {
		super(attributeReader);
		
		attributeReader.getReader().readBytes(attributeLength);
		
		String name = attributeReader.getConstantPool().getItem(attributeNameIndex,ConstantPoolUTF8.class).getTitle();
		DAJBI.logger.trace("UNIMPLEMENTED ATTRIBUTED ( "+name+" )");		
	}

}
