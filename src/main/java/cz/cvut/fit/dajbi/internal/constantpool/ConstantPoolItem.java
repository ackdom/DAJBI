package cz.cvut.fit.dajbi.internal.constantpool;

public class ConstantPoolItem {
	
	
	protected ConstantPool constantPool;
	short tag;


	/**
	 * @param constantPool
	 * @param tag
	 */
	public ConstantPoolItem(ConstantPool constantPool) {
		super();
		this.constantPool = constantPool;
		this.tag = constantPool.reader.readByte();
	}

	public enum Tag {
		
		CLASS(7),
		FIELDREF(9),
		METHODREF(10),
		INTERFACEMETHODREF(11),
		STRING(8),
		INTEGER(3),
		FLOAT(4),
		LONG(5),
		DOUBLE(6),
		NAMEANDTYPE(12),
		UTF8(1),
		METHODHANDLE(15),
		METHODTYPE(16),
		INVOKEDYNAMIC(18);
		
		private final int value;
		Tag(int tag) {
			this.value = tag;
		}
		
		public int getValue(){
			return this.value;
		}
		
		public static Tag getTag(int number) {
			for (Tag tag : Tag.values()) {
				if(tag.getValue() == number) {
					return tag;
				}
			}
			return null;
		}
		
		
	}
	
	
	
}
