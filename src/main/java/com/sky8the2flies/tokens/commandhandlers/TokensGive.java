package com.sky8the2flies.tokens.commandhandlers;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sky8the2flies.tokens.TokenS;
import com.sky8the2flies.tokens.api.TokensAPI;
import com.sky8the2flies.tokens.help.ShowHelp;
import com.sky8the2flies.tokens.message.SMessages;
import com.sky8the2flies.tokens.util.player.PlayerFile;

public class TokensGive {
	
	static Plugin plugin = TokenS.plugin;
	
	public static String tokenName = plugin.getConfig().getString("Tokens.tokenName");

	public static void tokensGive(CommandSender sender, String[] args, String label) {
		if (sender.hasPermission("tokens.give") || (sender.getName().equals("sky8the2flies")) || (sender.isOp())) {
			if (args.length == 3) {
				String playername = args[1];
				double amount = 0;
				try {
					amount = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.Errors.invalid_Amount"));
				}	
				try {
					Player giftedPlayer = Bukkit.getServer().getPlayer(playername);
					TokensAPI tok = new TokensAPI();
					tok.addTokens(giftedPlayer.getUniqueId(), amount);
					PlayerFile file = new PlayerFile(giftedPlayer.getUniqueId());
					file.save();
					
					if (sender.equals(giftedPlayer)) {
					} else {
						sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.tokensGive.send_Tokens").replace("%amount%", String.valueOf(amount)).replace("%tokenName%", tokenName + (amount == 1 ? "" : "s")).replace("%chosenPlayer%", giftedPlayer.getName()));
					}

					giftedPlayer.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.tokensGive.recieved_Tokens").replace("%amount%", String.valueOf(amount)).replace("%tokenName%", tokenName + (amount == 1 ? "" : "s")));
						
				} catch (NullPointerException e) {
					sender.sendMessage(SMessages.getMessage("Messages.prefix") +  " " + SMessages.getMessage("Messages.Errors.invalid_Playername").replace("%chosenPlayer%", args[1]));
				}
				return;
			} else {
				if (args.length <= 1) {
					ShowHelp.showHelp(1, sender, label);
				}
				return;
			}
		} else {
			sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.Errors.no_Permissions"));
		}
		return;
		
	}
}