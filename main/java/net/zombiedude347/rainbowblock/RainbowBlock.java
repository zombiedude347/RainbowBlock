package net.zombiedude347.rainbowblock;

import java.util.List;

import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RainbowBlock extends BlockCompressed {
	private static MapColor mapColor;

	public RainbowBlock() {
		super(mapColor);
	}

	@Override
	public MapColor getMapColor(final IBlockState state) {
		final int meta = getMetaFromState(state);
		if (meta == 0)
			return MapColor.blackColor;
		if (meta == 1)
			return MapColor.snowColor;
		if (meta == 2)
			return MapColor.tntColor;
		if (meta == 3)
			return MapColor.yellowColor;
		if (meta == 4)
			return MapColor.emeraldColor;
		if (meta == 5)
			return MapColor.diamondColor;
		if (meta == 6)
			return MapColor.waterColor;
		if (meta == 7)
			return MapColor.pinkColor;
		return MapColor.airColor;
	}

	static String[] subBlocks = new String[] { "Black", "White", "Red", "Yellow", "Green", "Cyan", "Blue", "Magenta" };
	public static PropertyInteger color = PropertyInteger.create("color", 0, subBlocks.length - 1);

	@Override
	public int damageDropped(final IBlockState state) {
		final int meta = getMetaFromState(state);
		return meta >= 0 && meta < subBlocks.length - 1 ? meta + 1 : 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs creativeTab, final List list) {
		for (int i = 0; i < subBlocks.length; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { color });
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		return getDefaultState().withProperty(color, meta);
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		final int meta = (Integer) state.getValue(color);
		return meta;
	}

	static void textures() {
		ModelBakery.addVariantName(Item.getItemFromBlock(Main.rainbowBlock), "rainbowblock:blockRainbowBlack",
				"rainbowblock:blockRainbowWhite", "rainbowblock:blockRainbowRed", "rainbowblock:blockRainbowYellow",
				"rainbowblock:blockRainbowGreen", "rainbowblock:blockRainbowCyan", "rainbowblock:blockRainbowBlue",
				"rainbowblock:blockRainbowMagenta");
		for (int i = 0; i < subBlocks.length; i++)
			registerTexture(i);
	}

	private static void registerTexture(final int i) {
		final ItemModelMesher texture = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		texture.register(Item.getItemFromBlock(Main.rainbowBlock), i,
				new ModelResourceLocation(Main.MODID + ":" + "blockRainbow" + subBlocks[i], "inventory"));
	}
}
