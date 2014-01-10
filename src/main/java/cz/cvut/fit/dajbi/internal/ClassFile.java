package cz.cvut.fit.dajbi.internal;

import cz.cvut.fit.dajbi.constantpool.ConstantPool;

public class ClassFile {

	short minorVersion;
	short majorVersion;
	short constantPoolCount;
	ConstantPool constantPool;
	short accessFlags;
	short thisClass;
	short superClass;
	short interfacesCount;
	short[] interfaces;
	short fieldsCount;
	Field[] fields;
	short methodsCount;
	Method[] methods;
	short attributesCount;
	Attribute[] attributes;
	
	
	
	/**
	 * @return the minorVersion
	 */
	public short getMinorVersion() {
		return minorVersion;
	}

	/**
	 * @param minorVersion
	 *            the minorVersion to set
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
	 * @param majorVersion
	 *            the majorVersion to set
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
	 * @param constantPoolCount
	 *            the constantPoolCount to set
	 */
	public void setConstantPoolCount(short constantPoolCount) {
		this.constantPoolCount = constantPoolCount;
	}

	/**
	 * @return the constantPool
	 */
	public ConstantPool getConstantPool() {
		return constantPool;
	}

	/**
	 * @param constantPool the constantPool to set
	 */
	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}

	/**
	 * @return the accessFlags
	 */
	public short getAccessFlags() {
		return accessFlags;
	}

	/**
	 * @param accessFlags the accessFlags to set
	 */
	public void setAccessFlags(short accessFlags) {
		this.accessFlags = accessFlags;
	}

	/**
	 * @return the thisClass
	 */
	public short getThisClass() {
		return thisClass;
	}

	/**
	 * @param thisClass the thisClass to set
	 */
	public void setThisClass(short thisClass) {
		this.thisClass = thisClass;
	}

	/**
	 * @return the superClass
	 */
	public short getSuperClass() {
		return superClass;
	}

	/**
	 * @param superClass the superClass to set
	 */
	public void setSuperClass(short superClass) {
		this.superClass = superClass;
	}

	/**
	 * @return the interfacesCount
	 */
	public short getInterfacesCount() {
		return interfacesCount;
	}

	/**
	 * @param interfacesCount the interfacesCount to set
	 */
	public void setInterfacesCount(short interfacesCount) {
		this.interfacesCount = interfacesCount;
	}

	/**
	 * @return the interfaces
	 */
	public short[] getInterfaces() {
		return interfaces;
	}

	/**
	 * @param interfaces the interfaces to set
	 */
	public void setInterfaces(short[] interfaces) {
		this.interfaces = interfaces;
	}

	/**
	 * @return the fieldsCount
	 */
	public short getFieldsCount() {
		return fieldsCount;
	}

	/**
	 * @param fieldsCount the fieldsCount to set
	 */
	public void setFieldsCount(short fieldsCount) {
		this.fieldsCount = fieldsCount;
	}

	/**
	 * @return the fields
	 */
	public Field[] getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	/**
	 * @return the methodsCount
	 */
	public short getMethodsCount() {
		return methodsCount;
	}

	/**
	 * @param methodsCount the methodsCount to set
	 */
	public void setMethodsCount(short methodsCount) {
		this.methodsCount = methodsCount;
	}

	/**
	 * @return the methods
	 */
	public Method[] getMethods() {
		return methods;
	}

	/**
	 * @param methods the methods to set
	 */
	public void setMethods(Method[] methods) {
		this.methods = methods;
	}

	/**
	 * @return the attributesCount
	 */
	public short getAttributesCount() {
		return attributesCount;
	}

	/**
	 * @param attributesCount the attributesCount to set
	 */
	public void setAttributesCount(short attributesCount) {
		this.attributesCount = attributesCount;
	}

	/**
	 * @return the attributes
	 */
	public Attribute[] getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Attribute[] attributes) {
		this.attributes = attributes;
	}

}
