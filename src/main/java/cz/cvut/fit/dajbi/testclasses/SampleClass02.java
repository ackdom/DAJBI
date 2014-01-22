package cz.cvut.fit.dajbi.testclasses;

public class SampleClass02 {

	public static void main(String[] args) {
		//int j = 25;
		//int i = 15;
		//int c = sum(j,i);
		
		System.out.println("ahoj");
		
		/*for(int i = 0; i < 10; i++) {
			j = sum(i,j);
		}
		
		
		MyObject muj = new MyObject();
		muj.ahoj = 10;
		int c = muj.calc(j);
		*/
		return;
	}
	
	public synchronized static int sum(int j, int i) {
		return j+i;
	}

}
