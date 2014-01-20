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




	/**
	 * @return the accessFlags
	 */
	public short getAccessFlags() {
		return accessFlags;
	}




	/**
	 * @return the attributesCount
	 */
	public int getAttributesCount() {
		return getMethodArgumentCount(getDescription());
	}
	
	public static int getMethodArgumentCount(String desc) {
		 int beginIndex = desc.indexOf('(') + 1;
		 int endIndex = desc.lastIndexOf(')');
		 String x0 = desc.substring(beginIndex, endIndex);//Extract the part related to the arguments
		 String x1 = x0.replace("[", "");//remove the [ symbols for arrays to avoid confusion.
		 String x2 = x1.replace(";", "; ");//add an extra space after each semicolon.
		 String x3 = x2.replaceAll("L\\S+;", "L");//replace all the substrings starting with L, ending with ; and containing non whitespace characters inbetween with L.
		 String x4 = x3.replace(" ", "");//remove the previously inserted spaces.
		 return x4.length();//count the number of elements left.
		}

}
