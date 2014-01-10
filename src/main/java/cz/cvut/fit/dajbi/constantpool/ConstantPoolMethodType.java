package cz.cvut.fit.dajbi.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolMethodType extends ConstantPoolItem {

	private short descriptorIndex;

	public ConstantPoolMethodType(ConstantPool constantPool) {
		super(constantPool);
		
		descriptorIndex = constantPool.reader.readShort();
		
		 DAJBI.logger.trace( constantPool.currentIndex+" MethodType  " +descriptorIndex);

	}
	
	public String getDescriptor() {
		return ((ConstantPoolUTF8)constantPool.items[descriptorIndex]).title;
	}

}
