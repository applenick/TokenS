package com.sky8the2flies.tokens.enchants;

import org.bukkit.enchantments.Enchantment;


public class Enchantments {

	static Enchantments instance = new Enchantments();

	public static Enchantments getInstance() {
		return instance;
	}

	public Enchantment getEnchantment(String enchantment) {
		if (enchantment.equalsIgnoreCase("sharpness"))
			return Enchantment.DAMAGE_ALL;
		else if (enchantment.equalsIgnoreCase("protection"))
			return Enchantment.PROTECTION_ENVIRONMENTAL;
		else if (enchantment.equalsIgnoreCase("fire_protection"))
			return Enchantment.PROTECTION_FIRE;
		else if (enchantment.equalsIgnoreCase("blast_protection"))
			return Enchantment.PROTECTION_EXPLOSIONS;
		else if (enchantment.equalsIgnoreCase("feather_falling"))
			return Enchantment.PROTECTION_FALL;
		else if (enchantment.equalsIgnoreCase("fire_protection"))
			return Enchantment.PROTECTION_FIRE;
		else if (enchantment.equalsIgnoreCase("projectile_protection"))
			return Enchantment.PROTECTION_PROJECTILE;
		else if (enchantment.equalsIgnoreCase("power"))
			return Enchantment.ARROW_DAMAGE;
		else if (enchantment.equalsIgnoreCase("flame"))
			return Enchantment.ARROW_FIRE;
		else if (enchantment.equalsIgnoreCase("infinity"))
			return Enchantment.ARROW_INFINITE;
		else if (enchantment.equalsIgnoreCase("punch"))
			return Enchantment.ARROW_KNOCKBACK;
		else if (enchantment.equalsIgnoreCase("bane_of_arthropods"))
			return Enchantment.DAMAGE_ARTHROPODS;
		else if (enchantment.equalsIgnoreCase("smite"))
			return Enchantment.DAMAGE_UNDEAD;
		else if (enchantment.equalsIgnoreCase("efficiency"))
			return Enchantment.DIG_SPEED;
		else if (enchantment.equalsIgnoreCase("unbreaking"))
			return Enchantment.DURABILITY;
		else if (enchantment.equalsIgnoreCase("fire aspect"))
			return Enchantment.FIRE_ASPECT;
		else if (enchantment.equalsIgnoreCase("knockback"))
			return Enchantment.KNOCKBACK;
		else if (enchantment.equalsIgnoreCase("fortune"))
			return Enchantment.LOOT_BONUS_BLOCKS;
		else if (enchantment.equalsIgnoreCase("looting"))
			return Enchantment.LOOT_BONUS_MOBS;
		else if (enchantment.equalsIgnoreCase("luck_of_thesea"))
			return Enchantment.LUCK;
		else if (enchantment.equalsIgnoreCase("lure"))
			return Enchantment.LURE;
		else if (enchantment.equalsIgnoreCase("respiration"))
			return Enchantment.OXYGEN;
		else if (enchantment.equalsIgnoreCase("silk_touch"))
			return Enchantment.SILK_TOUCH;
		else if (enchantment.equalsIgnoreCase("thorns"))
			return Enchantment.THORNS;
		else if (enchantment.equalsIgnoreCase("aqua_affinity"))
			return Enchantment.WATER_WORKER;
		return null;
	}
}
