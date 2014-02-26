package dajbi;



import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.cvut.fit.dajbi.Interpreter;
import cz.cvut.fit.dajbi.internal.ClassFile;
import cz.cvut.fit.dajbi.internal.VMSettings;
import cz.cvut.fit.dajbi.methodarea.ClassResolver;

public class SynchroTest {

	@BeforeClass
	public static void once() {
		Tests.init();
		Logger.getRootLogger().setLevel(Level.INFO);
	}
	
	@Test
	public void testSynchro() {
		ClassFile mainclass = ClassResolver.resolveClass(VMSettings.TEST_CLASSES+"Synchro.class");		
		Interpreter inter = new Interpreter();		
		inter.run(mainclass);
	}

}
