package cz.cvut.fit.dajbi;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class DAJBI {

	static final Logger logger = Logger.getLogger(DAJBI.class);

	public static void main(String[] args) {

		// Configure logger
		BasicConfigurator.configure();
		logger.debug("Hello World!");
	}

}
