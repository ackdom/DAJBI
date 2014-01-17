package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolInteger extends ConstantPoolItem {
	
	int value;

	public ConstantPoolInteger(ConstantPool constantPool) {
		super(constantPool);
		
		value = constantPool.reader.readInt();
		DAJBI.logger.trace(constantPool.currentIndex+" Integer with = "+value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object valueForLDC() {
		// TODO Auto-generated method stub
		return value;
	}

}
