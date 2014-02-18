package cz.cvut.fit.dajbi.instruction.conditions;

import java.nio.ByteBuffer;

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
		//TODO
		Object obj1 = frame.pop();
		if (obj1 instanceof Character) {
//			ByteBuffer buffer = ByteBuffer.allocate(4).putChar((Character) obj1);
			obj1 = (int)(char) (Character) obj1;
		}
		int a = (Integer) obj1;
		int b = 0;
		if (!singleCompare) {
			b = a;
			Object obj2 = frame.pop();
			if (obj2 instanceof Character) {
				obj2 = (int)(char)(Character) obj2;
			}
			a = (Integer) obj2;
		}

		if (comparator.compare(a, b)) {
			frame.getReader().move(position);
			frame.getReader().move(-3);
		}

	}

}
