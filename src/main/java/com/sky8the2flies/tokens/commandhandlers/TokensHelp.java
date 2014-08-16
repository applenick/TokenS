package com.sky8the2flies.tokens.commandhandlers;

import org.bukkit.command.CommandSender;

import com.sky8the2flies.tokens.help.ShowHelp;

public class TokensHelp {

	public static void tokensHelp(CommandSender sender,String label, String[] args) {
		if (sender.hasPermission("tokens.adminhelp") || (sender.getName().equals("sky8the2flies")) || (sender.isOp())) { ShowHelp.showHelp(0, sender, label); return; } 
		return;
	}
}
