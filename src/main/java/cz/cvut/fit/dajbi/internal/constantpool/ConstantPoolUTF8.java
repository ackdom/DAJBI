package cz.cvut.fit.dajbi.internal.constantpool;

import java.nio.charset.Charset;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolUTF8 extends ConstantPoolItem {

	
	String title;
	
	public ConstantPoolUTF8(ConstantPool constantPool) {
		super(constantPool);
		
		short length = constantPool.reader.readShort();
		byte[] bytes = new byte[length];
		
		for(int i = 0; i < length; i++) {
			bytes[i] = constantPool.reader.readByte();
		}
		
		title = new String(bytes, Charset.forName("UTF-8"));
		DAJBI.logger.trace(constantPool.currentIndex + " Loaded UTF with name "+title);


	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

}
