package com.sky8the2flies.tokens.message;

import org.bukkit.ChatColor;

public class C {
	public static String format(String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		return msg;
	}
}
