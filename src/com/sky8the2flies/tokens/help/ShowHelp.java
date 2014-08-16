package com.sky8the2flies.tokens.help;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.sky8the2flies.tokens.message.C;

public class ShowHelp {
	
	public static void showHelp(int menu, CommandSender sender, String label) {
		switch (menu) {
		case 0:
			sender.sendMessage(C.format(" "));
			sender.sendMessage(C.format("&f&l      TokenS Commands:"));
			sender.sendMessage(C.format("&6&l /" + label + " give &b[player] [amount] &3- &bGive players tokens"));
			sender.sendMessage(C.format("&6&l /" + label + " remove &b[player] [amount] &3- &bRemove players tokens"));
			sender.sendMessage(C.format("&6&l /" + label + " check &b(player) &3- &bCheck tokens"));
			sender.sendMessage(C.format("&6&l /" + label + " shop &3- &bOpen the TokenShop"));
			break;
		case 1:
			sender.sendMessage(C.format(" "));
			sender.sendMessage(C.format("&c&lError > &6&lUsage: &b/"  + label + " give <name> <amount>"));
			break;
		case 2:
			sender.sendMessage(C.format(" "));
			sender.sendMessage(C.format("&c&lError > §6&lUsage: &b/" + label + " remove <amount> <name>"));
			break;
		case 3:
			sender.sendMessage(C.format(" "));
			sender.sendMessage(C.format("&c&lError > &6&lUsage: &b/" + label + " check [name]"));
			break;
		case 4:
			sender.sendMessage(C.format(" "));
			sender.sendMessage(C.format("&f&l      TokenS:"));
			sender.sendMessage(C.format("&5&lAuthor: &b&lsky8the2flies"));
			sender.sendMessage(C.format("&5&lVersion: &bv" + Bukkit.getPluginManager().getPlugin("TokenS").getDescription().getVersion()));
			sender.sendMessage(C.format("&6&l - &b&l/" + label + " help"));
			break;
		}
	}
}
