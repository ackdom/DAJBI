package cz.cvut.fit.dajbi.stack;

import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.parser.Reader;

public class SystemStack extends Stack<Frame> {

	// private static SystemStack instance;
	 
     //Vytvorime soukromy konstruktor
     public SystemStack() {
		super();
	}
 
    /* public static SystemStack getInstance() {
         if (instance == null) {
             instance = new SystemStack();
         }
         return instance;
     }*/
     
     
     
     public Frame newFrame(ClassFile cf, Method m) {
    	 Frame newFrame = newFrame();
    	 newFrame.classFile = cf;
    	 newFrame.reader = new Reader(m.getCodeAttribute().getCode());
    	 return newFrame;
     }
     
     public Frame newFrame() {
    	 push(new Frame());
    	 return top();    	 
     }
     
     
   
}
