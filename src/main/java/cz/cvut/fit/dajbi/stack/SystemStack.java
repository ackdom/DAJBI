package cz.cvut.fit.dajbi.stack;

import cz.cvut.fit.dajbi.heap.HeapHandle;
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
    	 newFrame.setMethod(m);
    	 
    	 return newFrame;
     }
     
     public Frame newFrame() {
    	 Frame frame = new Frame();
    	 frame.setInvoker(top());
    	 push(frame);
    	 return top();    	 
     }

	@Override
	public Frame pop() {
		Frame frame = super.pop();
		for (Object obj : frame.localVariables.values()) {
			if(obj != null && obj instanceof HeapHandle) {
				((HeapHandle) obj).DecReferences();
			}
		}
		return frame;
	}
     
     
   
}
