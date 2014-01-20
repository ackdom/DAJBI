package cz.cvut.fit.dajbi.testclasses;

public class SampleClass01 {

	static int myValue = 42;
	boolean myBool = false;
	double myDouble = 3.1456789;
	short myShort = 3;
	float myFloat = (float) 2e-3;
	long myLong = 2023123;
	//static MyObject mujmuj;
	
	private int result(int i) {
		int j = 10;
		return i+j;
	}
	
	public static void main(String[] args) {
		
		MyObject muj = new MyObject(); 
		muj.ahoj = 33;
		//int[] arr = new int[5];
		int j = 25;
		int i = 10;
		int c = sum(j);
		return;
		
		
		//SampleClass01 sample = new SampleClass01();
		//sample.result(25);
	}
	
	public synchronized static int sum(int j) {
		return j+10;
	}

}
