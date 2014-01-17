package dajbi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cz.cvut.fit.dajbi.methodarea.ClassLoader;
import cz.cvut.fit.dajbi.testclasses.SampleClass01;

public class ClassLoaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void lookupsuccess() {
		String name = SampleClass01.class.getName();
		assertEquals(ClassLoader.classLookUp(name), "target/classes/cz/cvut/fit/dajbi/testclasses/SampleClass01.class");

	}
	
	@Test
	public void lookopfail() {
		String name = "cz.fit.cvut.dajbi.notexisting.MyClass";
		assertEquals(ClassLoader.classLookUp(name), null);
	}

}
