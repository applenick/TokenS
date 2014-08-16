package com.sky8the2flies.tokens.shops;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.sky8the2flies.tokens.TokenS;
import com.sky8the2flies.tokens.api.TokensAPI;
import com.sky8the2flies.tokens.message.C;
import com.sky8the2flies.tokens.message.SMessages;
import com.sky8the2flies.tokens.util.player.PlayerFile;

public class DonatorShop {
	
	static Plugin plugin = TokenS.plugin;

	public static String tokenName = plugin.getConfig().getString("Tokens.tokenName");
		
	public static Boolean hasTokens(UUID uuid, int t) {
		if (getPlayerBalance(uuid) >= t) {
			return Boolean.valueOf(true);
		}
		return Boolean.valueOf(false);
	}

	public static void playerGUI(Player player) {
		guiShop(player);
	}

	public static void takeTokens(UUID uuid, int t) {
		TokensAPI tok = new TokensAPI();
		tok.removeTokens(uuid, t);
	}

	public static int getPlayerBalance(UUID uuid) {
		return TokensAPI.getTokens(uuid);
	}

	public static boolean hasAccount(UUID uuid) {
		PlayerFile file = new PlayerFile(uuid);
		return file.getConfig().get(uuid.toString()) != null;
	}
	
	public static ConfigurationSection iSection() {
		ConfigurationSection itemSection = null;
		ConfigurationSection cs = TokenS.donatorShop.getConfig().getConfigurationSection("token_Shop");
		for (String s : cs.getKeys(false))  {
			itemSection = cs.getConfigurationSection(s);
		}

		return itemSection;
	}

	public static void guiShop(Player p) {
		try {
			
			ConfigurationSection itemSection = null;
			ConfigurationSection cs = TokenS.donatorShop.getConfig().getConfigurationSection("token_Shop");
			Inventory inv = Bukkit.createInventory(null, TokenS.donatorShop.getConfig().getInt("shop.row_Amount") * 9, TokenS.donatorShop.getConfig().getString("shop.shop_Name"));
			for (String s : cs.getKeys(false))  {
				itemSection = cs.getConfigurationSection(s);
				
				int slot = itemSection.getInt("slot");
				int itemId = itemSection.getInt("id");
				int meta = itemSection.getInt("meta");
				
				int amount = itemSection.getInt("amount");
				int price = itemSection.getInt("price");
				String name = itemSection.getString("name");
				
				List<String> enchants = itemSection.getStringList("enchants");

				ItemStack output = new ItemStack(Material.getMaterial(itemId), amount, (short) meta);

				ItemMeta itemMeta = output.getItemMeta();
				
				List<String> l = new ArrayList<String>();
				l.add(C.format("&aPrice: " + price));
				itemMeta.setLore(l);

				if (name != null) {
					itemMeta.setDisplayName(C.format(name));
				}

				output.setItemMeta(itemMeta);
				
				if (enchants != null) {
					for (String enchant : enchants) {
						String[] breakdown = enchant.split(":");
				
						try {
							Enchantment ench = TokenS.enchant.getEnchantment(breakdown[0]);						
							try {
								int level = Integer.valueOf(breakdown[1]);

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
				inv.setItem(slot - 1, output);
				}
				
			}
				p.openInventory(inv);
		} catch (ArrayIndexOutOfBoundsException ee) {
			TokenS.console.sendMessage(C.format("[TokenS] &cConfig is set up wrong..."));
			p.sendMessage(SMessages.getMessage("Messages.prefix") + " " + SMessages.getMessage("Errors.cfg_Error"));
		}
	}

	public static void slotClick(Player player, ItemStack givenItem) {
			if (!(givenItem.getTypeId() == 0)) {
				int price = iSection().getInt("price");
				if (hasTokens(player.getUniqueId(), price).booleanValue()) {

					takeTokens(player.getUniqueId(), price);

					player.getInventory().addItem(givenItem);

					player.sendMessage(C.format(TokenS.prefix + " &aPurchased » &7&b" + givenItem.getType() 	+ "&7&6 for &a" + price + "&6 " + tokenName + (price == 1 ? "" : "s") + ".").replace("_", " "));
					return;
				} else {
					player.sendMessage(C.format(TokenS.prefix + " &cYou haven't got enough " + tokenName + "s."));
					return;
				}
			}
	}
}
