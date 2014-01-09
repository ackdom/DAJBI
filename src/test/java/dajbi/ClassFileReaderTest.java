package dajbi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cz.cvut.fit.dajbi.internal.VMSettings;
import cz.cvut.fit.dajbi.parser.ClassFileReader;

public class ClassFileReaderTest {
	
	ClassFileReader reader;

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void readTest() {
		ClassFileReader reader = new ClassFileReader(VMSettings.TEST_CLASSES+"SampleClass01.class");
		assertTrue(reader.isLoaded());
	}
	
	@Test
	public void validTest() {
		ClassFileReader reader = new ClassFileReader(VMSettings.TEST_CLASSES+"SampleClass01.class");
		assertTrue(reader.isValid());
	}


}
