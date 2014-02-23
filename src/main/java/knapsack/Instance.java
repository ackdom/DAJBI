package knapsack;

public class Instance {

	public int id;
	public int n;
	public int M;
	public int[] V;
	public int[] C;
	
	public Instance(int id, int n, int m) {
		super();
		this.id = id;
		this.n = n;
		M = m;
		V = new int[n];
		C = new int[n];
	}
}
