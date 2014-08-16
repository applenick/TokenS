package com.sky8the2flies.tokens.util.player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.sky8the2flies.tokens.TokenS;

public class PlayerFile {

	private UUID uuid = null;

	private File file;
	private FileConfiguration conf;

	public PlayerFile(UUID uuid) {
		this.uuid = uuid;
	}

	public void load() {
		if (file == null) {
			file = new File(TokenS.getInstance().getDataFolder() + File.separator + "playerInfo", uuid + ".yml");
		}

		conf = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration getConfig() {
		if (conf == null) {
			load();
		}

		return conf;
	}

	public void save() {
		if (file == null || conf == null) {
			return;
		}

		try {
			getConfig().save(file);
		} catch (IOException ex) {
			TokenS.getInstance().getLogger().severe("Unable to save " + uuid + ".yml.");
			ex.printStackTrace();
		}
	}
}