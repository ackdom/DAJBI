package cz.cvut.fit.dajbi.constantpool;

import cz.cvut.fit.dajbi.DAJBI;

public class ConstantPoolRef extends ConstantPoolItem {

	protected short classIndex;
	protected short nameAndTypeIndex;

	public ConstantPoolRef(ConstantPool constantPool) {
		super(constantPool);

		classIndex = constantPool.reader.readShort();
		nameAndTypeIndex = constantPool.reader.readShort();

		DAJBI.logger.trace(constantPool.currentIndex + " Created "+ this.getClass().getSimpleName() +" Ref classIndex "
				+ classIndex + " nameAndType " + nameAndTypeIndex);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the classIndex
	 */
	public short getClassIndex() {
		return classIndex;
	}

	/**
	 * @return the nameAndTypeIndex
	 */
	public short getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}

}
