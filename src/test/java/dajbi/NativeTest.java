package dajbi;

import static org.junit.Assert.*;

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

public class NativeTest {

	@Before
	public void setUp() throws Exception {
	}

	@BeforeClass
	public static void once() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}

	@Test
	public void canRunNativeWithReturn() {		
		ClassFile mainclass = ClassResolver.resolveClass(VMSettings.TEST_CLASSES+"NativeClass.class");		
		Interpreter inter = new Interpreter();		
		inter.run(mainclass);	
	}

}
