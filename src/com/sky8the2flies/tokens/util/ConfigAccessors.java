package com.sky8the2flies.tokens.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigAccessors {
  private final String fileName;
  private final JavaPlugin plugin;
  private YamlConfiguration defConfig;
  private File configFile;
  private FileConfiguration fileConfiguration;

  public ConfigAccessors(JavaPlugin plugin, String fileName)
  {
    if (plugin == null)
      throw new IllegalArgumentException("plugin cannot be null");
    if (!plugin.isInitialized())
      throw new IllegalArgumentException("plugin must be initiaized");
    this.plugin = plugin;
    this.fileName = fileName;
  }

  public void reloadConfig() {
    if (this.configFile == null) {
      File dataFolder = this.plugin.getDataFolder();
      if (dataFolder == null)
        throw new IllegalStateException();
      this.configFile = new File(dataFolder + File.separator, this.fileName);
    }

    this.fileConfiguration = YamlConfiguration.loadConfiguration(this.configFile);

    InputStream defConfigStream = this.plugin.getResource(this.fileName);

    if ((defConfigStream != null) && (this.configFile.length() < 1L)) {
      this.plugin.getLogger().info("Generating config for " + this.fileName);
      this.defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
      this.fileConfiguration.setDefaults(this.defConfig);
      this.fileConfiguration = this.defConfig;
      
      this.plugin.getLogger().info("Saving " + this.fileName + "...");
      saveConfig();
    }
  }

	public FileConfiguration getConfig() {
		if (this.fileConfiguration == null) {
			reloadConfig();
		}
		return this.fileConfiguration;
	}

	public void saveConfig() {
		if ((this.fileConfiguration == null) || (this.configFile == null))
			return;
		try {
			getConfig().save(this.configFile);
		} catch (IOException ex) {
			this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, ex);
		}
	}

	public void saveDefaultConfig() {
		if (!this.configFile.exists())
			this.plugin.saveResource(this.fileName, false);
	}
}
