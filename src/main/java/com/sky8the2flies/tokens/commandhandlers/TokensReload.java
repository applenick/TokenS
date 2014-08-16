package com.sky8the2flies.tokens.commandhandlers;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import com.sky8the2flies.tokens.TokenS;
import com.sky8the2flies.tokens.message.SMessages;

public class TokensReload {
	
	static Plugin plugin = TokenS.plugin;
	
	public static void tokensReload(CommandSender sender, String[] args) {
		if (sender.hasPermission("tokens.reload") || sender.getName().equals("sky8the2flies")) {
		    plugin.reloadConfig();
		    TokenS.enchantShop.reloadConfig();
		    TokenS.standardShop.reloadConfig();
		    TokenS.donatorShop.reloadConfig();
		    TokenS.messages.reloadConfig();
		    sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " +  "§6Config reloaded from disk.");
		} else {
			sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " +  SMessages.getMessage("Messages.Errors.no_Permissions"));
		}
	}

}