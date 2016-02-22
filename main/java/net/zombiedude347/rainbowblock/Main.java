package net.zombiedude347.rainbowblock;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {
	public static final String MODID = "rainbowblock";
	public static final String VERSION = "1.7.10-1.1";
	static final Block rainbowBlock = new RainbowBlock(false);
	static final Block rainbowBlockOn = new RainbowBlock(true);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerBlock(Main.rainbowBlock, ItemRainbowBlock.class, "RainbowBlock");
		GameRegistry.registerBlock(rainbowBlockOn, ItemRainbowBlock.class, "RainbowBlockOn");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.addRecipe(new ItemStack(Main.rainbowBlock),
				new Object[] { "###", "###", "###", '#', new ItemStack(Items.dye, 1, OreDictionary.WILDCARD_VALUE) });
	}
}
