package cz.cvut.fit.dajbi.instruction.stack;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.dajbi.heap.Heap;
import cz.cvut.fit.dajbi.heap.HeapHandle;
import cz.cvut.fit.dajbi.instruction.Instruction;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.Method;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolClass;
import cz.cvut.fit.dajbi.internal.constantpool.ConstantPoolItem;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;
import cz.cvut.fit.dajbi.stack.Frame;

public class LDC extends Instruction {

	
	boolean wide;
	
	public LDC(Frame f) {
		super(f);
		// TODO Auto-generated constructor stub
	}

	public LDC(Frame f, boolean b) {
		super(f);
		wide = b;
	}

	@Override
	public void execute() {
		int index = (this.wide) ? frame.getReader().readShort() : frame.getReader().readByteToUInt();
		ConstantPoolItem item = frame.getClassFile().getConstantPool().getItem(index);
		
		Object forLDC = item.valueForLDC();
		
//		//TODO str
		if (forLDC instanceof String) {
			forLDC = Heap.getInstance().allocString((String) forLDC);
		}
//		if (forLDC instanceof String) {
//			String stringForLDC = (String) forLDC;
//			//alloc array
//			long arrRef = Heap.getInstance().allocArray(null, stringForLDC.length());
//			Object[] array = Heap.getInstance().getArray(arrRef);
//			char[] tmp = new char[stringForLDC.length()];
//			stringForLDC.getChars(0, stringForLDC.length(), tmp, 0);
//			for (int i = 0; i < tmp.length; i++) {
//				array[i] = tmp[i];
//			}
////			new String(tmp);
//			//NEW instr.
//			ClassFile stringCF = ClassResolver
//					.resolveWithLookup("java/lang/String");
//			HeapHandle heapRef = Heap.getInstance().allocObject(stringCF);
//
//			//INVOKEVIRT instr.
//			List<Object> pop = new ArrayList<Object>();
//			pop.add(heapRef);
//			pop.add(arrRef);
//			ClassFile cf = ((HeapHandle) pop.get(0)).getClassFile();
//
//			Method method;
//			ClassFile classFile = cf;
//			method = classFile.getMethod("<init>", "([C)V");
//			if (method == null) {
//				throw new AbstractMethodError("<init>" + " " + "([C)V");
//			}
//			frame.getInterpreter().call(cf, method, pop);
//			forLDC = frame.pop();
//		}
		
		frame.push(forLDC);
	}

}
