package com.sky8the2flies.tokens.commandhandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sky8the2flies.tokens.TokenS;
import com.sky8the2flies.tokens.help.ShowHelp;

public class Commands implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tokens")) {
			if (args.length <= 0) {
				ShowHelp.showHelp(4, sender, label);		
			}
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("help")) {
					TokensHelp.tokensHelp(sender, label, args);
					return true;
				} else if (args[0].equalsIgnoreCase("shop")) {
					TokensShop.tokensShop(sender, args);
					return true;
				} else if (args[0].equalsIgnoreCase("give")) {
					TokensGive.tokensGive(sender, args, label);
					return true;
				} else if (args[0].equalsIgnoreCase("remove")) {
					TokensRemove.tokensRemove(sender, label, args);
					return true;
				} else if (args[0].equalsIgnoreCase("check")) {
					TokensCheck.tokensCheck(sender, args);
					return true;
				} else if (args[0].equalsIgnoreCase("reload")) {
						TokensReload.tokensReload(sender, args);
				}  else {
				    sender.sendMessage(TokenS.prefix + " §6Unknown command arguments.");
				}
			}
		}
		return false;
	}
}
