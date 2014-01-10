package dajbi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.cvut.fit.dajbi.DAJBI;
import cz.cvut.fit.dajbi.internal.VMSettings;
import cz.cvut.fit.dajbi.parser.ClassFileReader;

public class ClassFileReaderTest {
	
	ClassFileReader reader;

	@BeforeClass
	public static void once() {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	@Before
	public void setUp() throws Exception {

	}
	
	@Test
	public void readTest() {
		ClassFileReader reader = new ClassFileReader(VMSettings.TEST_CLASSES+"SampleClass01.class");
		assertTrue(reader.isLoaded());
	}
	
	@Test
	public void parseTest() {
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
	
	@Test
	public void parseClassFile() {
		Logger.getRootLogger().setLevel(Level.ALL);
		ClassFileReader reader = new ClassFileReader(VMSettings.TEST_CLASSES+"SampleClass01.class");
		reader.getClass();
	}


}
