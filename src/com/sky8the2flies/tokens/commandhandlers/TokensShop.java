package com.sky8the2flies.tokens.commandhandlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
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

public class TokensShop {
  static Plugin plugin = TokenS.plugin;

  public static String tokenName = plugin.getConfig().getString("Tokens.tokenName");

  public static void tokensShop(CommandSender sender, String[] args) {
    if ((sender instanceof Player)) {
      Player player = (Player)sender;
      if ((player.hasPermission("tokens.tokenshop")) || (sender.getName().equals("sky8the2flies")) || (sender.isOp())) {
        sender.sendMessage(C.format(TokenS.prefix + " &6Loading shop information..."));
        player.openInventory(originalShop(player));
      } else {
        sender.sendMessage(SMessages.getMessage("Messages.prefix") + "" + SMessages.getMessage("Messages.Errors.no_Permissions"));
      }
      return;
    }
    sender.sendMessage("Must be in-game to use this command!");
  }
  
  public static Inventory originalShop(Player player) {
	  Inventory i = Bukkit.createInventory(null, 9, "Choose shop!"); 
	  
		ItemStack s1 = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta ms1 = s1.getItemMeta();
		Object ls1 = new ArrayList<Object>();
		if (player.hasPermission("tokens.tokenshop.item")) { ((ArrayList<Object>) ls1).add(C.format("&7&lEnter the &a&litem &7&lshop!")); }
		else { ((ArrayList<Object>) ls1).add(C.format("&7&lEnter the &4&litem &7&lshop!")); ((ArrayList<Object>) ls1).add(C.format("&4&lNo access!")); }
		ms1.setLore((List<String>) ls1);
		ms1.setDisplayName(C.format("&6&lITEM SHOP"));
		ms1.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		s1.setItemMeta(ms1);
		
		ItemStack s2 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta ms2 = s2.getItemMeta();
		Object ls2 = new ArrayList<Object>();
		if (player.hasPermission("tokens.tokenshop.donator")) {((ArrayList<Object>) ls2).add(C.format("&7&lEnter the &a&ldonator &7&litem shop!"));}
		else { ((ArrayList<Object>) ls2).add(C.format("&7&lEnter the &4&ldonator &7&litem shop!"));  ((ArrayList<Object>) ls2).add(C.format("&4&lNo access!"));}
		
		ms2.setLore((List<String>) ls2);
		ms2.setDisplayName(C.format("&e&lDONATOR &6&lITEM SHOP"));
		ms2.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		s2.setItemMeta(ms2);
		
		ItemStack s3 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta ms3 = s3.getItemMeta();
		Object ls3 = new ArrayList<Object>();
		if (player.hasPermission("tokens.tokenshop.enchant")) { ((ArrayList<Object>) ls3).add(C.format("&7&lEnter the &a&lenchantment &7shop!")); }
		else { ((ArrayList<Object>) ls3).add(C.format("&7&lEnter the &4&lenchantment &7shop!")); ((ArrayList<Object>) ls3).add(C.format("&4&lNo access!")); }
		ms3.setLore((List<String>) ls3);
		ms3.setDisplayName(C.format("&b&lENCHANTMENT SHOP"));
		s3.setItemMeta(ms3);
		
		ItemStack s4 = new ItemStack(Material.EMERALD);
		ItemMeta ms4 = s4.getItemMeta();
		Object ls4 = new ArrayList<Object>();
		((ArrayList<Object>) ls4).add(C.format("&aYour tokens: " + TokensAPI.getTokens(player.getUniqueId())));
		((ArrayList<Object>) ls4).add(C.format("&7LeftClick on a item to buy it!"));
		((ArrayList<Object>) ls4).add(C.format("&7Click out of the inventory to return back to this menu."));
		((ArrayList<Object>) ls4).add(C.format("&7Vote/Donate for more &atokens!"));
		ms4.setLore((List<String>) ls4);
		ms4.setDisplayName(C.format("&6&lTokenShop Help:"));
		s4.setItemMeta(ms4);
		
		
		i.setItem(3, s1);
		i.setItem(4, s2);
		i.setItem(5, s3);
		i.setItem(8, s4);
		
	  return i;  
  }
}