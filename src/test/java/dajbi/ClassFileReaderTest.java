package dajbi;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;

import cz.cvut.fit.dajbi.internal.VMSettings;
import cz.cvut.fit.dajbi.parser.ClassFileReader;

public class ClassFileReaderTest {
	
	ClassFileReader reader;

	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
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
	
	@Test
	public void ReadmeReadTest() {
		ClassFileReader reader = new ClassFileReader("README.md");
	
		assertTrue(reader.isLoaded());
	}
	
	@Test
	public void ReadmeValidTest() {
		ClassFileReader reader = new ClassFileReader("README.md");
		assertFalse(reader.isValid());
	}
	
	@Test
	public void nonExistingRead() {
		ClassFileReader reader = new ClassFileReader("not.class");
		assertFalse(reader.isLoaded());		
	}


}
