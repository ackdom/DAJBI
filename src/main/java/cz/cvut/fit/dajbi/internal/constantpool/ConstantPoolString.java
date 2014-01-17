package cz.cvut.fit.dajbi.internal.constantpool;

public class ConstantPoolString extends ConstantPoolItem {

	
	int stringIndex;
	
	public ConstantPoolString(ConstantPool constantPool) {
		super(constantPool);
		
		this.stringIndex = constantPool.reader.readShort();
	}
	
	public String getString() {
		return constantPool.getItem(stringIndex, ConstantPoolUTF8.class).getTitle();
	}
	
	@Override
	public Object valueForLDC() {
		return getString();
	}

}
