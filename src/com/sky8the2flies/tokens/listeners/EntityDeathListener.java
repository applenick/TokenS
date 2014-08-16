package com.sky8the2flies.tokens.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.sky8the2flies.tokens.TokenS;
import com.sky8the2flies.tokens.api.TokensAPI;
import com.sky8the2flies.tokens.message.C;

public class EntityDeathListener implements Listener {
	
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		try {
			FileConfiguration config = TokenS.instance.getConfig();
			if (config.getBoolean("tokens_On_Kill.enabled")) {
				ArrayList<UUID> mobs = TokenS.mobsFromSpawner;
				Entity entity = event.getEntity();
				String type = event.getEntityType().toString().toLowerCase();
				if (!(mobs.contains(entity.getUniqueId()))) {
					TokensAPI tok = new TokensAPI();
					Player killer = event.getEntity().getKiller();
					double amount = config.getInt("tokens_On_Kill." + type);
					tok.addTokens(killer.getUniqueId(), amount);
					killer.sendMessage(C.format(TokenS.prefix + " &6You recieved &b" + amount + " " + config.getString("Tokens.tokenName") + (amount == 1 ? "" : "s") + " &6for killing a &b" + type + "&6."));
				} else {
					mobs.remove(entity.getUniqueId());
				}
			}
		} catch (NullPointerException npe) {

		}
	}

}