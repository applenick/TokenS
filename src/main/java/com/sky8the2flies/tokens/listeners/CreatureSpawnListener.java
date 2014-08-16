package com.sky8the2flies.tokens.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.sky8the2flies.tokens.TokenS;

public class CreatureSpawnListener implements Listener {
	
	@EventHandler
	public void creatureSpawn(CreatureSpawnEvent event) {
		if (event.getSpawnReason().equals(SpawnReason.SPAWNER)) {
			FileConfiguration config = TokenS.instance.getConfig();
			if (!(config.getBoolean("tokens_On_Kill.give_Tokens_From_Spawner"))) {
				ArrayList<UUID> mobs = TokenS.mobsFromSpawner;
				mobs.add(event.getEntity().getUniqueId());
			}
		}
	}

}
