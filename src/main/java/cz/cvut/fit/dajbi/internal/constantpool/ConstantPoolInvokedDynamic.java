package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolInvokedDynamic extends ConstantPoolItem {

	private short boostrapMethodAttributeIndex ;
	private short nameTypeIndex;

	public ConstantPoolInvokedDynamic(ConstantPool constantPool) {
		super(constantPool);
		
		boostrapMethodAttributeIndex = constantPool.reader.readShort();
		 nameTypeIndex = constantPool.reader.readShort();
		// TODO Auto-generated constructor stub
		 
		 DAJBI.logger.trace( constantPool.currentIndex+" InvokeDynamic " +boostrapMethodAttributeIndex+ " and type "+nameTypeIndex);
	}
	
	public ConstantPoolNameAndType getNameType() {
		return  constantPool.getItem(nameTypeIndex,ConstantPoolNameAndType.class);
	}

}
