package knapsack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static Instance[] parse(String file, int lines) {
		Instance[] res = new Instance[lines];
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
				String line = br.readLine();
				int lineNum = 0;

				while(line != null) {
					String it[] = line.split(" ");
//					if (it.length < 3) break;
					Instance instance = new Instance(Integer.parseInt(it[0]), Integer.parseInt(it[1]), Integer.parseInt(it[2]));
					for (int i = 0; i < instance.n; i++) {
						instance.V[i] = Integer.parseInt(it[3+i*2]);
						instance.C[i] = Integer.parseInt(it[3+i*2+1]);
					}
					res[lineNum] = instance;
					line = br.readLine();
					lineNum++;
					if(lineNum >= lines) break;
				}
			} finally {
				br.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String file = args[0];
		Instance[] instances = parse(file, Integer.parseInt(args[1]));
		KnapsackBrute kb = new KnapsackBrute();
		Result resultB;
		for (Instance instance : instances) {
			resultB = kb.solve(instance);
			
//			System.out.print("B ");
			resultB.p();
			System.out.println("");
		}
	}

}
