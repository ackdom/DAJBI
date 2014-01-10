package cz.cvut.fit.dajbi.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolMethodHandle extends ConstantPoolItem {
	
	byte typeIndex;
	short referenceIndex;

	public ConstantPoolMethodHandle(ConstantPool constantPool) {
		super(constantPool);
		
		typeIndex = constantPool.reader.readByte();
		referenceIndex = constantPool.reader.readShort();
		
		DAJBI.logger.trace(+constantPool.currentIndex+" Method Handle of type "+typeIndex+" and ref "+referenceIndex);
		
		// TODO Auto-generated constructor stub
	}
	
	public ConstantPoolMethodRef getMethodRef() {
		return (ConstantPoolMethodRef) constantPool.items[referenceIndex];
	}

}
