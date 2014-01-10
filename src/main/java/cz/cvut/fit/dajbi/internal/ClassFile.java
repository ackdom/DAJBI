package cz.cvut.fit.dajbi.internal;

public class ClassFile {
	
	short minorVersion;
	short majorVersion;
	short constantPoolCount;
	/**
	 * @return the minorVersion
	 */
	public short getMinorVersion() {
		return minorVersion;
	}
	/**
	 * @param minorVersion the minorVersion to set
	 */
	public void setMinorVersion(short minorVersion) {
		this.minorVersion = minorVersion;
	}
	/**
	 * @return the majorVersion
	 */
	public short getMajorVersion() {
		return majorVersion;
	}
	/**
	 * @param majorVersion the majorVersion to set
	 */
	public void setMajorVersion(short majorVersion) {
		this.majorVersion = majorVersion;
	}
	/**
	 * @return the constantPoolCount
	 */
	public short getConstantPoolCount() {
		return constantPoolCount;
	}
	/**
	 * @param constantPoolCount the constantPoolCount to set
	 */
	public void setConstantPoolCount(short constantPoolCount) {
		this.constantPoolCount = constantPoolCount;
	}
	
	
	
	

}
