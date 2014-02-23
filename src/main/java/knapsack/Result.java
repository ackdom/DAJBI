package knapsack;

public class Result {
	
	public byte[] x;
	public int c;
	public Instance instance;
	public long avgTime;
	
	public Result(Instance instance) {
		x = new byte[instance.n];
		this.instance = instance;
	}
	
	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append(instance.id + " ");
//		builder.append(instance.n + " ");
//		builder.append(c + " ");
//		for (int i = 0; i < x.length; i++) {
//			builder.append(" " + x[i]);
//		}
//		builder.append(" " + avgTime);
//		return builder.toString();
		return "aaaa";
	}
	
	public void p() {
		System.out.print(instance.id);
		System.out.print(" ");
		System.out.print(instance.n);
		System.out.print(" ");
		System.out.print(c);
		System.out.print(" ");
		for (int i = 0; i < x.length; i++) {
			System.out.print(" ");
			System.out.print(x[i]);
		}
//		System.out.print(" ");
//		System.out.print(avgTime);
	}
	

}
