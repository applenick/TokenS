package com.sky8the2flies.tokens.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.sky8the2flies.tokens.util.player.PlayerFile;

public class PlayerLoginListener implements Listener {
	
	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		PlayerFile file = new PlayerFile(player.getUniqueId());
		file.load();
	}
}
