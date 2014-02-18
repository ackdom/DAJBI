package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;

public class ConstantPoolClass extends ConstantPoolItem {

	short nameIndex;

	public ConstantPoolClass(ConstantPool constantPool) {
		super(constantPool);
		nameIndex = constantPool.reader.readShort();
		DAJBI.logger.trace(constantPool.currentIndex + " Loaded class with name " + nameIndex);
	}

	public String getName() {
		return constantPool.getItem(nameIndex, ConstantPoolUTF8.class).getTitle();
	}

	@Override
	public Object valueForLDC() {
		return Heap.getInstance().allocNative(ClassResolver.resolveWithLookup(getName()));
	}
	
	
}
