package cz.cvut.fit.dajbi.instruction.conditions.strategy;

public class EQStrategy implements CompareStrategy {

	public boolean compare(int a, int b) {
		return (a == b);
	}

}
