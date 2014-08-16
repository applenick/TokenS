package com.sky8the2flies.tokens.api;

import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;

import com.sky8the2flies.tokens.util.player.PlayerFile;

public class TokensAPI {
	
	public static int getTokens(UUID uuid) {
		PlayerFile file = new PlayerFile(uuid);
		return file.getConfig().getInt("Tokens.amount");	
	}
	
	public void addTokens(UUID uuid, double amount) {
		PlayerFile file = new PlayerFile(uuid);
		ConfigurationSection cf = file.getConfig();
		int getTokens = cf.getInt("Tokens.amount");
		cf.set("Tokens.amount", getTokens + amount);
		file.save();
	}
	
	public void removeTokens(UUID uuid, double amount) {
		PlayerFile file = new PlayerFile(uuid);
		ConfigurationSection cf = file.getConfig();
		int getTokens = cf.getInt("Tokens.amount");
		cf.set("Tokens.amount", getTokens - amount);
		file.save();
	}
}
