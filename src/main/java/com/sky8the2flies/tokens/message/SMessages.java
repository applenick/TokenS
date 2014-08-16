package com.sky8the2flies.tokens.message;

import com.sky8the2flies.tokens.TokenS;

public class SMessages {
	
	public static String getMessage(String cfg) {
		return C.format(TokenS.messages.getConfig().getString(cfg)); 
		}

}
