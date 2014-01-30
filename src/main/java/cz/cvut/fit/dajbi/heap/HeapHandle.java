package cz.cvut.fit.dajbi.heap;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Field;

public class HeapHandle {

	ClassFile classFile;
	Map<String,Object> instanceData;
	
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
		
		//sets size of block at the beginning
		setBytes(-4, ByteBuffer.allocate(4).putInt(cf.getDataSize()).array());
	}
	
	public HeapHandle(ClassFile classFile) {
		super();
		this.classFile = classFile;
		dataOffset = 0;
		instanceData = new HashMap<String, Object>();
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
		return Heap.getInstance().getByte(this.dataOffset + offset + 4);
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
			ClassFile fieldsClassFile = field.getFieldsClassFile();
			return Heap.getInstance().getObjectReference(buffer.getLong(), fieldsClassFile);
			
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
			bytes = ByteBuffer.allocate(1).put((Byte) value).array();
			break;
		//boolean
		case 'Z':
			boolean b = (Boolean) value;
			bytes = ByteBuffer.allocate(1).put((byte) (b?1:0)).array();
			break;
			
		//char
		case 'C':
			bytes = ByteBuffer.allocate(2).putChar((Character) value).array();
			break;
		//short
		case 'S':
			bytes = ByteBuffer.allocate(2).putShort((Short) value).array();
			break;
			
		//float
		case 'F':
			bytes = ByteBuffer.allocate(4).putFloat((Float) value).array();
			break;
		//int
		case 'I':
			bytes = ByteBuffer.allocate(4).putInt((Integer) value).array();
			break;
			
		//double
		case 'D':
			bytes = ByteBuffer.allocate(8).putDouble((Double) value).array();
			break;
		//long
		case 'J':
			bytes = ByteBuffer.allocate(8).putLong((Long) value).array();
			break;
			
		//reference (class)
		case 'L':
			long val;
			HeapHandle handle = (HeapHandle) value;
			if (value != null) {
//				HeapHandle handle = Heap.getInstance().getObject((Long) value);
				val = handle.getDataOffset();
			} else {
				val = Heap.NULL;
			}
			handle.DecReferences();
			bytes = ByteBuffer.allocate(8).putLong(val).array();
			break;
			
		//reference (array)
		case '[':
			//TODO array
			bytes = ByteBuffer.allocate(8).putLong((Long) value).array();
	
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
		Heap.getInstance().setByte(this.dataOffset + offset + 4, value);
	}

	Map<String, Object> getInstanceData() {
		return instanceData;
	}

	
}
