package cz.cvut.fit.dajbi.heap;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Field;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;

public class HeapHandle implements NullableHandle {
	public static final int INTERNALDATASIZE = 12; 

	private ClassFile classFile;
	
	private int references = 0;
	
	/**
	 * offset pointing on start of instance data
	 */
	private long dataOffset;

	/**
	 * 
	 * @param cf {@link ClassFile} represented by this handle
	 * @param DataOffset offset pointing on start of instance data
	 */
	public HeapHandle(ClassFile cf, long DataOffset) {
		super();
		classFile = cf;
		dataOffset = DataOffset;
		
		if (dataOffset != Heap.NULL) {
			//sets size of instance's data
			storeInstanceDataSize();
			storeClassFile();
		}
	}

	/**
	 * Sets {@code int} representing size of instance's data. Including internal data of {@code HeapHandle}.
	 * Size is stored on the beginning of this data representation on heap.
	 */
	private void storeInstanceDataSize() {
		setBytes(-INTERNALDATASIZE + 0, ByteBuffer.allocate(4).putInt(classFile.getDataSize() + INTERNALDATASIZE)
				.array());
	}
	
	/**
	 * classFile name is stored after size at offset of 4 bytes (int) and takes 8 bytes (long)
	 */
	private void storeClassFile() {
		String name = classFile.getName();
		long ref = Heap.getInstance().allocNative(name);
		setBytes(-INTERNALDATASIZE + 4, ByteBuffer.allocate(8).putLong(ref).array());
	}

	/**
	 * Creates new {@link HeapHandle} for data at given offset.
	 * ClassFile is loaded from data at the offset position.
	 * @param offset
	 */
	HeapHandle(long offset) {
		super();
		dataOffset = offset;
		classFile = loadClassFile();
	}

	/**
	 * Gets {@code ClassFile} of referenced instance.
	 * @return
	 */
	private ClassFile loadClassFile() {
		byte[] bytes = getBytes(-INTERNALDATASIZE + 4, 8);
		long ref = ByteBuffer.wrap(bytes).getLong();
		String name = (String) Heap.getInstance().getNative(ref);
		ClassFile classFile = ClassResolver.resolveWithLookup(name);
		return classFile;
	}

	/**
	 * @return the classFile
	 */
	public ClassFile getClassFile() {
		return classFile;
	}


	/**
	 * @param classFile the classFile to set
	 */
	public void setClassFile(ClassFile classFile) {
		this.classFile = classFile;
	}
	
	/**
	 * Increment number of references pointing to this {@link HeapHandle}.
	 */
	public void IncReferences() {
		++references;
	}
	
	/**
	 * Decrement number of references pointing to this {@link HeapHandle}.
	 */
	public void DecReferences() {
		--references;
		if(references == 0) {
			Heap.getInstance().removeFromRootSet(this);
		}
	}
	
	/**
	 * @deprecated
	 * NOT IMPLEMENTED
	 * @param fieldDataOffset
	 * @param type
	 * @return
	 */
	public <T extends Number> T getNumberData(int fieldDataOffset, Class<T> type) {
		throw new UnsupportedOperationException();
	}
	
	private byte[] getBytes(int offset, int size) {
		byte[] bytes = new byte[size];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = getByte(offset + i);
		}
		return bytes;
//		throw new UnsupportedOperationException();
	}
	
	private byte getByte(int offset) {
		return Heap.getInstance().getByte(this.dataOffset + offset + INTERNALDATASIZE);
	}


	public Object getFieldData(Field field) {
		byte[] bytes = getBytes(field.getFieldDataOffset(), field.getSize());
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		
		switch (field.getDescription().charAt(0)) {
		//byte
		case 'B':
			return buffer.get();
		//boolean
		case 'Z':
			return buffer.get() == 0 ? false : true;
			
		//char
		case 'C':
			return buffer.getChar();
		//short
		case 'S':
			return buffer.getShort();
			
		//float
		case 'F':
			return buffer.getFloat();
		//int
		case 'I':
			return buffer.getInt();
			
		//double
		case 'D':
			return buffer.getDouble();
		//long
		case 'J':
			return buffer.getLong();
			
		//reference (class)
		case 'L':
			return Heap.getInstance().getObjectReference(buffer.getLong());
			
		//reference (array)
		case '[':
			//TODO array
			return buffer.getLong();
	
		default:
			throw new UnsupportedOperationException();
		}
		
//		throw new UnsupportedOperationException();
	}


	void setFieldData(Field field) {
		setFieldData(field, field.getValue());
	}


	/**
	 * @param field
	 * @param value
	 */
	public void setFieldData(Field field, Object value) {
		byte[] bytes;
		
		switch (field.getDescription().charAt(0)) {
		//byte
		case 'B':
			if (value == null) { value = new Byte((byte) 0); }
			bytes = ByteBuffer.allocate(1).put((Byte) value).array();
			break;
		//boolean
		case 'Z':
			if (value == null) { value = new Boolean(false); }
			boolean b = (Boolean) value;
			bytes = ByteBuffer.allocate(1).put((byte) (b?1:0)).array();
			break;
			
		//char
		case 'C':
			if (value == null) { value = new Character('\u0000'); }
			bytes = ByteBuffer.allocate(2).putChar((Character) value).array();
			break;
		//short
		case 'S':
			if (value == null) { value = new Short((short) 0); }
			bytes = ByteBuffer.allocate(2).putShort((Short) value).array();
			break;
			
		//float
		case 'F':
			if (value == null) { value = new Float(0); }
			bytes = ByteBuffer.allocate(4).putFloat((Float) value).array();
			break;
		//int
		case 'I':
			if(value == null) { value = new Integer(0); } 
			bytes = ByteBuffer.allocate(4).putInt(((Integer) value)).array();
//			bytes = ByteBuffer.allocate(4).putInt(((Number) value).intValue()).array();
			break;
			
		//double
		case 'D':
			if (value == null) { value = new Double(0); }
			bytes = ByteBuffer.allocate(8).putDouble((Double) value).array();
			break;
		//long
		case 'J':
			if (value == null) { value = new Long(0); }
			bytes = ByteBuffer.allocate(8).putLong((Long) value).array();
			break;
			
		//reference (class)
		case 'L':
			long val;
			HeapHandle handle = (HeapHandle) value;
			if (value != null) {
//				HeapHandle handle = Heap.getInstance().getObject((Long) value);
				val = handle.getDataOffset();
				handle.DecReferences();
			} else {
				val = Heap.NULL;
			}
			bytes = ByteBuffer.allocate(8).putLong(val).array();
			break;
			
		//reference (array)
		case '[':
			//TODO array
			if (value == null) { value = Heap.NULL; }
			bytes = ByteBuffer.allocate(8).putLong((Long) value).array();
			break;
	
		default:
			throw new UnsupportedOperationException();
		}
		
		setBytes(field.getFieldDataOffset(), bytes);
	}


	/**
	 * @return offset pointing on start of instance data
	 */
	public long getDataOffset() {
		return dataOffset;
	}
	
	/**
	 * @param dataOffset offset pointing on start of instance data
	 */
	void setDataOffset(long dataOffset) {
		this.dataOffset = dataOffset;
	}


	private void setBytes(int offset, byte[] values) {
		for (int i = 0; i < values.length; i++) {
			setByte(offset + i, values[i]);
		}
	}
	
	private void setByte(int offset, byte value) {
		Heap.getInstance().setByte(this.dataOffset + offset + INTERNALDATASIZE, value);
	}

	public boolean isNull() {
		return dataOffset == Heap.NULL;
	}

	public void setNull() {
		dataOffset = Heap.NULL;
	}
	
}
