package knapsack;

public class KnapsackBrute extends Knapsack {
	
	public byte[] akt;

	@Override
	public Result solve(Instance instance) {
		Result res = null;
		int k = 1;
//		if (instance.n == 4) k = 100000;
//		if (instance.n == 10) k = 10000;
//		if (instance.n == 15) k = 100;
//		long startTime = System.nanoTime();
		for (int i = 0; i < k; i++) {
			res = new Result(instance);
			akt = new byte[instance.n];
			for (int j = 0; j < akt.length; j++) {
				akt[j] = 0;
			}
			try {
			boolean end = false;
			while(!end) {
				if (aktV(instance) <= instance.M) {
					int c = aktC(instance);
					if (c >= res.c) {
						System.arraycopy(akt, 0, res.x, 0, akt.length);
//						res.x = akt.clone();
						res.c = c;
					}
				}
				end = incX();
			}
			} catch (Exception e) {
			}
		}
//		long stopTime = System.nanoTime();
//		res.avgTime = (stopTime - startTime)/k;
		
		return res;
	}
	
	public boolean incX() throws Exception {
		return incX(0);
	}
	
	public boolean incX(int k) throws Exception {
		if (k == akt.length) {
//			throw new Exception();
			return true;
		}
		if (akt[k] == 0) {
			akt[k] = 1;
		} else {
			akt[k] = 0;
			return incX(k+1);
		}
		return false;
	}
	
	public int aktC(Instance instance) {
		int res = 0;
		for (int i = 0; i < akt.length; i++) {
			if (akt[i] == 1) {
				res += instance.C[i];
			}
		}
		return res;
	}

	public int aktV(Instance instance) {
		int res = 0;
		for (int i = 0; i < akt.length; i++) {
			if (akt[i] == 1) {
				res += instance.V[i];
			}
		}
		return res;
	}

}
