package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolNameAndType extends ConstantPoolItem {

	private short nameIndex;
	private short descriptorInex;

	public ConstantPoolNameAndType(ConstantPool constantPool) {
		super(constantPool);
		nameIndex = constantPool.reader.readShort();
		descriptorInex = constantPool.reader.readShort();
		
		DAJBI.logger.trace(constantPool.currentIndex + " created name "+nameIndex+" and type "+descriptorInex);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return ((ConstantPoolUTF8) constantPool.items[nameIndex]).getTitle();
	}
	
	public String getDescriptorIndex() {
		return ((ConstantPoolUTF8) constantPool.items[descriptorInex]).getTitle();
	}
}
