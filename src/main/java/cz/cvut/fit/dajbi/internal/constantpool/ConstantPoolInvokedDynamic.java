package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolInvokedDynamic extends ConstantPoolItem {

	private short boostrapMethodAttributeIndex ;
	private short NameTypeIndex;

	public ConstantPoolInvokedDynamic(ConstantPool constantPool) {
		super(constantPool);
		
		boostrapMethodAttributeIndex = constantPool.reader.readShort();
		 NameTypeIndex = constantPool.reader.readShort();
		// TODO Auto-generated constructor stub
		 
		 DAJBI.logger.trace( constantPool.currentIndex+" InvokeDynamic " +boostrapMethodAttributeIndex+ " and type "+NameTypeIndex);
	}
	
	public ConstantPoolNameAndType getNameType() {
		return (ConstantPoolNameAndType) constantPool.items[NameTypeIndex];
	}

}
