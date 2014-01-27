package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolDouble extends ConstantPoolItem {

	private double value;

	public ConstantPoolDouble(ConstantPool constantPool) {
		super(constantPool);

		//byte[] bytes = new byte[8];
		//ByteBuffer.wrap(bytes).putLong(constantPool.reader.readLong());
		value = constantPool.reader.readDouble();
		DAJBI.logger.trace(constantPool.currentIndex+" Double with = "+value);
		constantPool.currentIndex++;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object valueForLDC() {
		return value;
	}

}
