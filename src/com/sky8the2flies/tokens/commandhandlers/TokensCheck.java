package com.sky8the2flies.tokens.commandhandlers;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sky8the2flies.tokens.TokenS;
import com.sky8the2flies.tokens.api.TokensAPI;
import com.sky8the2flies.tokens.message.C;
import com.sky8the2flies.tokens.message.SMessages;

public class TokensCheck {
	
	static Plugin plugin = TokenS.plugin;
	
	public static String tokenName = plugin.getConfig().getString("Tokens.tokenName");

	public static void tokensCheck(CommandSender sender, String[] args) {
		try {
			try {
				if (sender.hasPermission("tokens.check") || (sender.isOp()) || sender.equals("sky8the2flies")) {
					if (args.length == 1) {
						if (sender instanceof Player) {
							Player player = (Player) sender;
							double tokens = TokensAPI.getTokens(player.getUniqueId());
							sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.tokensCheck.on_Check").replace("%tokens%", String.valueOf(tokens)).replace("%tokenName%", tokenName + (tokens == 1 ? "" : "s")));
							return;
						} else {
							sender.sendMessage(SMessages.getMessage("Messages.prefix") + C.format(" &cCommand must be used in-game!"));
						}
					}
				
				if (sender.hasPermission("tokens.check.others") || (sender.isOp())) {
					String playername = args[1];
					Player selectedPlayer = Bukkit.getServer().getPlayer(playername);
					double tokens1 = TokensAPI.getTokens(selectedPlayer.getUniqueId());
					if (args.length == 2) {
						sender.sendMessage(SMessages .getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.tokensCheck.on_Player_Check").replace("%chosenPlayer%", selectedPlayer.getName()).replace("%chosenTokens%", String.valueOf(tokens1)).replace("%tokenName%", tokenName + (tokens1 == 1 ? "" : "s")));
						return;
					}
					return;
					} else {
						sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.no_Permissions"));
						return;
					}
				} else {
					sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.no_Permissions"));
					return;
				}
			} catch (NullPointerException e) {
				sender.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Messages.invalid_Playername").replace("%chosenPlayer%", args[1]));
			}
		} catch (ArrayIndexOutOfBoundsException ec) {

		}
		return;
	}
}
