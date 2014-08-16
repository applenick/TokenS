package com.sky8the2flies.tokens;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sky8the2flies.tokens.commandhandlers.Commands;
import com.sky8the2flies.tokens.enchants.Enchantments;
import com.sky8the2flies.tokens.listeners.CreatureSpawnListener;
import com.sky8the2flies.tokens.listeners.EntityDeathListener;
import com.sky8the2flies.tokens.listeners.InventoryClickListener;
import com.sky8the2flies.tokens.listeners.PlayerLoginListener;
import com.sky8the2flies.tokens.message.C;
import com.sky8the2flies.tokens.util.ConfigAccessor;
import com.sky8the2flies.tokens.util.ConfigAccessors;

public class TokenS extends JavaPlugin {

	public static Plugin plugin = null;
	public static String prefix = C.format("&b&l[&6&lTokenS&b&l]");
	
	public static TokenS instance;
	
	public static ArrayList<UUID> mobsFromSpawner = new ArrayList<UUID>();
	
	public static ConfigAccessor standardShop;
	public static ConfigAccessor donatorShop;
	public static ConfigAccessor enchantShop;
	public static ConfigAccessors messages;

	public static Enchantments enchant = Enchantments.getInstance();
	
	public static ConsoleCommandSender console;
	
	public void onEnable() {
		console = Bukkit.getConsoleSender();
		if (!createDataDirectory()) {
			console.sendMessage(C.format("&c&lERRORR: &cCouldn't create data folder. shutting down..."));
			setEnabled(false);
		}
		
		makeConfigs();
		
		instance = this;
		plugin = this;
		getCommand("tokens").setExecutor(new Commands());

		final FileConfiguration config = this.getConfig();
		
		
		
		try {
			String strDirectory = plugin.getDataFolder() + File.separator + "shops";

			boolean success = (new File(strDirectory)).mkdir();
			if (success) {
				TokenS.console.sendMessage(C.format(prefix + " &aLoaded shops.file."));
			}
			

		} catch (Exception e) {
			TokenS.console.sendMessage(C.format(prefix + " &4Error: &c" + e.getMessage()));
		}
		
		try {
			String strDirectory = plugin.getDataFolder() + File.separator + "playerInfo";

			boolean success = (new File(strDirectory)).mkdir();
			if (success) {
				TokenS.console.sendMessage(C.format(prefix + " &aLoaded shops.file."));
			}
			

		} catch (Exception e) {
			TokenS.console.sendMessage(C.format(prefix + " &4Error: &c" + e.getMessage()));
		}
		
		
		registerEvents();
		
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	public static TokenS getInstance() {
		return instance;
	}

	public void registerEvents() {
		getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
		getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);
	}
	
	private boolean createDataDirectory() {
		File file = getDataFolder();
		if ((!file.isDirectory()) && (!file.mkdirs())) {
			return false;
		}
		return true;
	}

	public void makeConfigs() {
		standardShop = new ConfigAccessor(this, "standardShop.yml");
		donatorShop = new ConfigAccessor(this, "donatorShop.yml");
		enchantShop = new ConfigAccessor(this, "enchantShop.yml");
		messages = new ConfigAccessors(this, "messages.yml");
		standardShop.getConfig().options().copyDefaults(true);
		donatorShop.getConfig().options().copyDefaults(true);
		enchantShop.getConfig().options().copyDefaults(true);
		messages.getConfig().options().copyDefaults(true);
		standardShop.reloadConfig();
		donatorShop.reloadConfig();
		enchantShop.reloadConfig();
		messages.reloadConfig();
	}
}