package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolClass extends ConstantPoolItem {

	short nameIndex;

	public ConstantPoolClass(ConstantPool constantPool) {
		super(constantPool);
		nameIndex = constantPool.reader.readShort();
		DAJBI.logger.trace(constantPool.currentIndex + " Loaded class with name " + nameIndex);
	}

	public String getName() {
		return constantPool.getItem(nameIndex, ConstantPoolUTF8.class).getTitle();
	}
}
