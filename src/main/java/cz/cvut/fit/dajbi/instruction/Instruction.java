package cz.cvut.fit.dajbi.instruction;

import cz.cvut.fit.dajbi.stack.Frame;

public abstract class Instruction {
	
	protected Frame frame;
	
	public Instruction(Frame f) {
		this.frame = f;
	}
	
	
	public abstract void execute() ;

}
