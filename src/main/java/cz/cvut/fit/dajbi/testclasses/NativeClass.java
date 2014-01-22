package cz.cvut.fit.dajbi.testclasses;

public class NativeClass {
	
	
	public static void main(String[] args) {
		int a = 3;
		int b = getSecret();
		int c = a+b;
		System.out.println(c);
	}

	private native static int getSecret() ;
	

}
