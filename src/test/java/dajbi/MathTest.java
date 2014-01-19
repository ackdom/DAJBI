package dajbi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cz.cvut.fit.dajbi.instruction.math.ADD;
import cz.cvut.fit.dajbi.instruction.math.MUL;
import cz.cvut.fit.dajbi.instruction.math.SUB;
import cz.cvut.fit.dajbi.stack.Frame;

public class MathTest {

	Frame frame;

	@Before
	public void setUp() throws Exception {

		frame = new Frame();

	}

	@Test
	public void testIntegerSub() {
		frame.push(0x05);
		frame.push(0x0A);

		SUB<Integer> sub = new SUB<Integer>(frame);
		sub.execute();
		assertEquals(Integer.valueOf(5), frame.pop());

	}

	@Test
	public void testIntegerAdd() {
		frame.push(0x05);
		frame.push(0x0A);

		ADD<Integer> sub = new ADD<Integer>(frame);
		sub.execute();
		assertEquals(Integer.valueOf(15), frame.pop());

	}

	@Test
	public void testIntegerMul() {
		frame.push(0x05);
		frame.push(0x0A);

		MUL<Integer> sub = new MUL<Integer>(frame);
		sub.execute();
		assertEquals(Integer.valueOf(50), frame.pop());

	}

	@Test
	public void testDoubleAdd() {
		Double a = 5.2;
		Double b = 4.6;
		frame.push(a);
		frame.push(b);

		ADD<Double> sub = new ADD<Double>(frame);
		sub.execute();
		assertEquals(Double.valueOf(9.8), (Double) frame.pop(),0.1);

	}
	
	@Test
	public void testDoubleSub() {
		Double a = 5.1;
		Double b = 4.0;
		frame.push(b);
		frame.push(a);
		
		SUB<Double> sub = new SUB<Double>(frame);
		sub.execute();
		
		assertEquals(new Double(1.1), (Double) frame.pop(),0.1);
		
	}
	
	@Test
	public void testDoubleMul() {
		Double a = 0.2;
		Double b = 0.6;
		frame.push(b);
		frame.push(a);
		
		MUL<Double> sub = new MUL<Double>(frame);
		sub.execute();
		assertEquals(new Double(0.06), (Double) frame.pop(),0.1 );
		
	}
	
	@Test
	public void testFloatAdd() {
		Float a = (float) 2.0e2;
		Float b = (float) 3.0e2;
		frame.push(b);
		frame.push(a);
		
		ADD<Float> sub = new ADD<Float>(frame);
		sub.execute();
		assertEquals(Float.valueOf((float) 500.0), frame.pop());
		
	}
	@Test
	public void testFloatSub() {
		Float a = (float) 2.0e2;
		Float b = (float) 3.0e2;
		frame.push(a);
		frame.push(b);
		
		SUB<Float> sub = new SUB<Float>(frame);
		sub.execute();
		assertEquals(Float.valueOf((float) 100.0), frame.pop());
		
	}
	@Test
	public void testFloatMul() {
		Float a = (float) 2.0e2;
		Float b = (float) 3.0e2;
		frame.push(a);
		frame.push(b);
		
		MUL<Float> sub = new MUL<Float>(frame);
		sub.execute();
		assertEquals(Float.valueOf((float) 60000), frame.pop());
		
	}

}
