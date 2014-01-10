package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolClass extends ConstantPoolItem {

	short name;

	public ConstantPoolClass(ConstantPool constantPool) {
		super(constantPool);
		name = constantPool.reader.readShort();
		DAJBI.logger.trace(constantPool.currentIndex + " Loaded class with name " + name);
	}

	public String getName() {
		return ((ConstantPoolUTF8) constantPool.items[name]).getTitle();
	}
}
