package cz.cvut.fit.dajbi.internal.attributes;

import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolClass;
import cz.cvut.fit.dajbi.parser.AttributeReader;

public class ExceptionHandler {
	
	private short startPc;
	private short endPc;
	private short handlerPc;
	private short catchType;
	private AttributeReader attributeReader;
	
	public short getStartPc() {
		return startPc;
	}
	public short getEndPc() {
		return endPc;
	}
	public short getHandlerPc() {
		return handlerPc;
	}
	public ConstantPoolClass getCatchType() {
		return attributeReader.getConstantPool().getItem(catchType, ConstantPoolClass.class);
	}
	
	public ExceptionHandler(short startPc, short endPc, short handlerPc,
			short catchType, AttributeReader attributeReader) {
		super();
		this.startPc = startPc;
		this.endPc = endPc;
		this.handlerPc = handlerPc;
		this.catchType = catchType;
		this.attributeReader = attributeReader;
	}
	
	

}
