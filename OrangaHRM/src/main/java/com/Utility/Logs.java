package com.Utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Logs {
	public static Logger log=Logger.getLogger("Logs");
	public static void Info(String message) {
		PropertyConfigurator.configure("Log4j.properties");
		log.info(message);
	}

}
