package cz.cvut.fit.dajbi.parser;

import java.nio.ByteBuffer;

public class Reader {

	byte[] bytes;
	int index;

	public Reader(byte[] byteArray) {
		index = 0;
		this.bytes = byteArray;
	}

	public boolean hasNext() {
		return index < bytes.length;
	}

	public byte peekByte() {
		return peekByte(0);
	}

	private byte peekByte(int i) {
		return bytes[index + i];
	}

	public byte readByte() {
		return bytes[index++];
	}

	public int readByteToUInt() {
		return 0xff & readByte();
	}

	public short readShort() {
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.put(readBytes(2));
		buffer.flip();
		return buffer.getShort();
	}

	public short peekShort() {
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.put(peekBytes(2));
		buffer.flip();
		return buffer.getShort();
	}

	public int readInt() {
		int tmp = 0;
		for (int i = 0; i < 4; i++) {
			tmp = tmp << 8;
			tmp += readByte();
		}
		return tmp;
	}

	public long readLong() {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.put(readBytes(8));
		buffer.flip();// need flip
		return buffer.getLong();
	}

	public double readDouble() {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.put(readBytes(8));
		buffer.flip();// need flip
		return buffer.getDouble();
	}

	public byte[] readBytes(int i) {
		byte[] arr = new byte[i];
		for (int j = 0; j < i; j++) {
			arr[j] = readByte();
		}
		return arr;
	}

	public byte[] peekBytes(int i) {
		byte[] arr = new byte[i];
		for (int j = 0; j < i; j++) {
			arr[j] = peekByte(j);
		}
		return arr;
	}

	public int peekByteAsUInt() {
		return 0xff & peekByte();

	}

	public void move(int i) {
		index += i;		
	}

}
