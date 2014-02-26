package cz.cvut.fit.dajbi.testclasses;

public class Synchro {

	public static void main(String[] args) {
		System.out.println("Start");
		// (new Synchro()).run();
		doMath();
		for (int i = 0; i < 1000; i++) {
			System.out.println(1);
		}
	}

	private synchronized static void doMath() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(2);
		}
	}

}
