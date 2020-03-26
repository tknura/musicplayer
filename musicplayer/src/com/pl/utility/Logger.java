package com.pl.utility;

import com.pl.utility.configuration.Config;

public class Logger {

	public Logger() {
		
	}
	
	public static void debug(String string) {
		if(Config.getVerbose() == true) {
			System.out.println("[LOGGER] " + string);
		}
	}

}
