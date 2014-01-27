package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolLong extends ConstantPoolItem {

	private long value;

	public ConstantPoolLong(ConstantPool constantPool) {
		super(constantPool);

		value = constantPool.reader.readLong();

		DAJBI.logger.trace(constantPool.currentIndex + " Long with = " + value);
		constantPool.currentIndex++;

	}
	
	@Override
	public Object valueForLDC() {
		// TODO Auto-generated method stub
		return value;
	}

}
