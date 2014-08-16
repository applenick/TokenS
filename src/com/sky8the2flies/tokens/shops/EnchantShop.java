package com.sky8the2flies.tokens.shops;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.sky8the2flies.tokens.TokenS;
import com.sky8the2flies.tokens.message.C;
import com.sky8the2flies.tokens.message.SMessages;


public class EnchantShop {
	
	public static void openShop(Player p) {
		try {
	    Inventory inv = Bukkit.createInventory(null, 9, "Enchantment Shop");
		ConfigurationSection cs = TokenS.enchantShop.getConfig().getConfigurationSection("shop");
		    for (String s : cs.getKeys(false)) {
		    ConfigurationSection itemSection = cs.getConfigurationSection(s);
		    
		    int slot = itemSection.getInt("slot");		    
		    int itemId = itemSection.getInt("id");

		    int meta = itemSection.getInt("meta");
		    int amount = itemSection.getInt("amount");
		    String name = itemSection.getString("name");		    
		    List<String> enchants = itemSection.getStringList("enchants");
		    
		    ItemStack output = new ItemStack(Material.getMaterial(itemId), amount, (short) meta);
		    
			ItemMeta itemMeta = output.getItemMeta();
			
			if (name != null) {
				itemMeta.setDisplayName(C.format(name));
			}
			
		    if (enchants != null) {
		      for (String enchant : enchants) {
		        String[] breakdown = enchant.split(":");
		        Enchantment ench = TokenS.enchant.getEnchantment(breakdown[0]);
		        
					try {
						int level = Integer.parseInt(breakdown[1]);
						try {
							if (level > ench.getMaxLevel()) {
								level = ench.getMaxLevel();
							}

							output.addUnsafeEnchantment(ench, level);
						} catch (NullPointerException npe) {
							System.out.println("############ CONFIG ERROR ############");
							System.out.println(" ");
							System.out.println(itemSection + ":");
							System.out.println("  enchants:");
							System.out.println("  - >" + enchant + "<");
							System.out.println(" ");
							System.out.println("######### NOT A REAL ENCHANT #########");
						}
					} catch (ArrayIndexOutOfBoundsException aioobe) {
						System.out.println("############ CONFIG ERROR ############");
						System.out.println(" ");
						System.out.println(itemSection + ":");
						System.out.println("  enchants:");
						System.out.println("  - >" + enchant + "<");
						System.out.println(" ");
						System.out.println("######### NOT A REAL ENCHANT #########");
					}
				}
		    }
				output.setItemMeta(itemMeta);

				inv.setItem(slot - 1, output);

				p.openInventory(inv);
		    }
		} catch (ArrayIndexOutOfBoundsException ee) {
			TokenS.console.sendMessage(C.format("[TokenS] &cConfig is set up wrong..."));
			p.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Errors.cfg_Error"));
		}
	}	
}
