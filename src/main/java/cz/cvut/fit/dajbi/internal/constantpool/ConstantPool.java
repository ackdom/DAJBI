package cz.cvut.fit.dajbi.internal.constantpool;

import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolItem.Tag;
import cz.cvut.fit.dajbi.parser.Reader;

/**
 * Constant Pool class which stores per class per interface constant pool
 * 
 * @author dominik
 * 
 */
public class ConstantPool {

	Reader reader;
	ConstantPoolItem items[];
	int currentIndex;

	public ConstantPool(Reader byteReader, int size) {
		this.items = new ConstantPoolItem[size];
		this.reader = byteReader;
		currentIndex = 0;
		while(currentIndex < items.length) {
			items[currentIndex] = parseItem();
			currentIndex++;
		}

	}

	/**
	 * Parse Constant pool item
	 * 
	 * @return parsed item
	 */
	private ConstantPoolItem parseItem() {

		byte peekByte = reader.peekByte();
		
		Tag tag = ConstantPoolItem.Tag.getTag(peekByte);
		switch (tag) {
		
		case CLASS:
			return new ConstantPoolClass(this);
		case UTF8:
			return new ConstantPoolUTF8(this);
		case METHODREF:
			return new ConstantPoolMethodRef(this);			
		case FIELDREF:
			return new ConstantPoolFieldRef(this);						
		case INTERFACEMETHODREF:	
			return new ConstantPoolInterfaceMethodRef(this);			
		case NAMEANDTYPE:
			return new ConstantPoolNameAndType(this);
		case DOUBLE:
			return new ConstantPoolDouble(this);
		case INTEGER:
			return new ConstantPoolInteger(this);
		case FLOAT:
			return new ConstantPoolFloat(this);
		case LONG:
			return new ConstantPoolLong(this);
		case METHODHANDLE:
			return new ConstantPoolMethodHandle(this);
		case INVOKEDYNAMIC:
			return new ConstantPoolInvokedDynamic(this);
		case METHODTYPE: 
			return new ConstantPoolMethodType(this);			
		default:
			System.out.println("Neznam "+tag);
			break;
		}

		return null;
	}

	/**
	 * @return the items
	 */
	public ConstantPoolItem[] getItems() {
		return items;
	}
	
	
	
	public <T extends ConstantPoolItem> T getItem(int i,Class<T> type) {		
		return type.cast(getItem(i));		
	}
	
	public ConstantPoolItem getItem(int i) {
		return items[i-1];
	}
	
	
}
