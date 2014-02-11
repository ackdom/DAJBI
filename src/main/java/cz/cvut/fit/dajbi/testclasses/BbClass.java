package cz.cvut.fit.dajbi.testclasses;

public class BbClass extends AaClass {
	
	int ba;
	int bb = 42;
	int bc;
	
	

	public BbClass(int aa, int ac, int ba, int bc) {
		super(aa, ac);
		this.ba = ba;
		this.bc = bc;
	}

	


	@Override
	public String toString() {
		return "BbClass []";
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BbClass cl = new BbClass(25, 26, 32, 33);
		String a = cl.toString();
		System.out.println(a);
		System.out.println(cl.aa);
		System.out.println(cl.ab);
		System.out.println(cl.ac);
		System.out.println(cl.ba);
		System.out.println(cl.bb);
		System.out.println(cl.bc);
		System.out.println("");
		
		AaClass cla = cl;
		cl = null;
		System.out.println(cla.toString());
		System.out.println("");
		cla = null;
		cl = new BbClass(4, 5, 6, 7);
		
		cla = new AaClass(2147483647, -300);
		new AaClass(70, 71);
		new AaClass(70, 71);
		new BbClass(4, 555, 6, 7);
		System.out.println(cla.toString());
		System.out.println(cla.aa);
		System.out.println(cla.ab);
		System.out.println(cla.ac);
//		int i = 250;
	}

}
