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

public class TokensRemove {
	
	static Plugin plugin = TokenS.plugin;
	
	public static String tokenName = plugin.getConfig().getString("Tokens.tokenName");

	public static void tokensRemove(CommandSender sender, String label, String[] args) {
		if (sender.hasPermission("tokens.remove") || (sender.getName().equals("sky8the2flies")) || (sender.isOp())) {
			if (args.length == 3) {
				String playername = args[2];
				double amount = 0;
				try {
					amount = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.Errors.invalid_Amount"));
				}
				try {
					Player giftedPlayer = Bukkit.getServer().getPlayer(playername);
					TokensAPI tok = new TokensAPI();
					tok.removeTokens(giftedPlayer.getUniqueId(), amount);
					PlayerFile file = new PlayerFile(giftedPlayer.getUniqueId());
					file.save();
					
					if (sender.equals(giftedPlayer)) {
						
					} else {
					sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.tokensRemove.remove_Tokens").replace("%amount%", args[1]).replace("%tokenName%", tokenName).replace("%chosenPlayer%", args[2]));
					}
					
					giftedPlayer.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.tokensRemove.removed_Tokens").replace("%amount%", args[1]).replace("%tokenName%", tokenName + (amount == 1 ? "" : "s")));
					
								
				} catch (NullPointerException e) {
					sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.Errors.invalid_Playername").replace("%chosenPlayer%", args[2]));
				}
				return;
			} else {
				if (args.length <= 2) {
					ShowHelp.showHelp(2, sender, label);
				}
				return;
			}
		} else {
			sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.Errors.no_Permissions"));
		}
		return;
	}
}