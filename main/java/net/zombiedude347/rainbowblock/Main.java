package net.zombiedude347.rainbowblock;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {

	public static final String MODID = "rainbowblock";

	public static final String VERSION = "1.8-1.0";

	public static final String clientproxy = "net.zombiedude347.rainbowblock.ClientProxy";
	public static final String commonproxy = "net.zombiedude347.rainbowblock.ServerProxy";

	@SidedProxy(clientSide = clientproxy, serverSide = commonproxy)
	public static CommonProxy proxy;

	static final Block rainbowBlock = new RainbowBlock();

	@EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		GameRegistry.registerBlock(rainbowBlock, ItemRainbowBlock.class, "RainbowBlock");
	}

	@EventHandler
	public void init(final FMLInitializationEvent event) {
		GameRegistry.addRecipe(new ItemStack(rainbowBlock),
				new Object[] { "###", "###", "###", '#', new ItemStack(Items.dye, 1, OreDictionary.WILDCARD_VALUE) });
	}

	@EventHandler
	public void postInit(final FMLInitializationEvent event) {
		proxy.initTextures();
	}
}
