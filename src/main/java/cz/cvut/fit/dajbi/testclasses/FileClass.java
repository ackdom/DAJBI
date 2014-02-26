package cz.cvut.fit.dajbi.testclasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileClass {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		System.out.println("ahoj");
//		System.out.println("C:/temp/dajbi/aa.txt");
//		File file = new File("C:/temp/dajbi/aa.txt");
//		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedReader reader = new BufferedReader(new FileReader("README.md"));
		String line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}
		reader.close();
	}

}
