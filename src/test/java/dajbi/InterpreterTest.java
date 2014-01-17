package dajbi;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.cvut.fit.dajbi.Interpreter;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.VMSettings;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;
import cz.cvut.fit.dajbi.parser.ClassFileReader;

public class InterpreterTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@BeforeClass
	public static void once() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}

	@Test
	public void test() {
		
		ClassFile mainclass = ClassResolver.resolveClass(VMSettings.TEST_CLASSES+"SampleClass01.class");		
		Interpreter inter = new Interpreter();		
		inter.run(mainclass);	
	}

}
