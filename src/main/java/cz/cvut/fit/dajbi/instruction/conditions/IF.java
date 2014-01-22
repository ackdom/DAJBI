package cz.cvut.fit.dajbi.instruction.conditions;

import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.instruction.conditions.strategy.CompareStrategy;
import cz.cvut.fit.dajbi.stack.Frame;

public class IF extends Instruction {

	CompareStrategy comparator;
	boolean singleCompare;

	public IF(Frame f, CompareStrategy comp, boolean single) {
		super(f);
		comparator = comp;
		singleCompare = single;
	}

	@Override
	public void execute() {
		short position = frame.getReader().readShort();
		int a = (Integer) frame.pop();
		int b = 0;
		if (!singleCompare) {
			b = (Integer) frame.pop();
		}

		if (comparator.compare(b,a)) {
			frame.getReader().move(position);
			frame.getReader().move(-3);
		}

	}

}
