package cz.cvut.fit.dajbi.internal.constantpool;

import java.nio.ByteBuffer;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolFloat extends ConstantPoolItem {

	private float value;

	public ConstantPoolFloat(ConstantPool constantPool) {
		super(constantPool);
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putInt(constantPool.reader.readInt());
		value = ByteBuffer.wrap(bytes).getFloat();
		DAJBI.logger.trace(constantPool.currentIndex+" Float with = "+value);	}

}
