package dajbi;

import org.junit.Ignore;
import org.junit.Test;

import cz.cvut.fit.dajbi.DAJBI;

public class KnapsackTest {
	
	@Test
	public void test4() {
		String[] args = {"-cp", "target/classes/", "knapsack/Main", "knapsack_inst/knap_4.inst.dat", "4"};
		DAJBI.main(args);
	}

	@Test
	@Ignore
	public void test10() {
		String[] args = {"-cp", "target/classes/", "knapsack/Main", "knapsack_inst/knap_10.inst.dat", "3"};
		DAJBI.main(args);
	}

}
