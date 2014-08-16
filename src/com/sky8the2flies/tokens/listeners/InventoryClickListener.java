package com.sky8the2flies.tokens.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.sky8the2flies.tokens.TokenS;
import com.sky8the2flies.tokens.commandhandlers.TokensShop;
import com.sky8the2flies.tokens.shops.DonatorShop;
import com.sky8the2flies.tokens.shops.StandardShop;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public static void onInventoryClick(InventoryClickEvent e) {
		try {
			Player player = (Player) e.getWhoClicked();	
			
			if (e.getInventory().getName().contains("Choose shop!")) {
				if (e.isLeftClick()) {
					e.setCancelled(true);
					if (e.getCurrentItem().getType().equals(Material.DIAMOND_AXE)) {
						if (player.hasPermission("tokens.tokenshop.item")) StandardShop.playerGUI(player); else e.setCancelled(true);
					} else if (e.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
						if (player.hasPermission("tokens.tokenshop.donator")) DonatorShop.playerGUI(player); else e.setCancelled(true);
					} else if (e.getCurrentItem().getType().equals(Material.ENCHANTED_BOOK)) {
						e.setCancelled(true);
						//if (player.hasPermission("tokens.tokenshop.enchant")) EnchantShop.openShop(player); else e.setCancelled(true);
					}
				} else {
					e.setCancelled(true);
				}
			}
			
			if (e.getInventory().getName().contains(TokenS.donatorShop.getConfig().getString("shop.shop_Name"))) {
				if (e.isLeftClick()) {
				e.setCancelled(true);
				
				ItemStack clickedItem = new ItemStack(e.getCurrentItem().getType());
				clickedItem.addUnsafeEnchantments(e.getCurrentItem().getEnchantments());
				clickedItem.setDurability(e.getCurrentItem().getDurability());
				clickedItem.setAmount(e.getCurrentItem().getAmount());
				
				DonatorShop.slotClick(player, clickedItem);
				if (!e.getCurrentItem().getType().equals(Material.AIR)) {
					e.getWhoClicked().closeInventory();
					DonatorShop.guiShop(player);
					player.closeInventory();
					} else {
						e.setCancelled(true);
					}
			} else {
					e.setCancelled(true);
					if (!e.getCurrentItem().getType().equals(Material.AIR)) {
						e.setCancelled(true);
					}
				}
			}
			
			
				if (e.getInventory().getName().contains(TokenS.standardShop.getConfig().getString("shop.shop_Name"))) {
					if (e.isLeftClick()) {
					e.setCancelled(true);
					
					ItemStack clickedItem = new ItemStack(e.getCurrentItem().getType());
					clickedItem.addUnsafeEnchantments(e.getCurrentItem().getEnchantments());
					clickedItem.setDurability(e.getCurrentItem().getDurability());
					clickedItem.setAmount(e.getCurrentItem().getAmount());
					
					StandardShop.slotClick(player, clickedItem);
					if (!e.getCurrentItem().getType().equals(Material.AIR)) {
						e.getWhoClicked().closeInventory();
						StandardShop.guiShop(player);
						player.closeInventory();
						} else {
							e.setCancelled(true);
						}
				} else {
					e.setCancelled(true);
					if (!e.getCurrentItem().getType().equals(Material.AIR)) {
						e.setCancelled(true);
					} 
				}
			}
		} catch (NullPointerException npe) {
			Player p = (Player) e.getWhoClicked();
			p.closeInventory();
			if (!(e.getInventory().getName().equals("Choose shop!"))) p.openInventory(TokensShop.originalShop(p));
		}
	}

}
