package net.zombiedude347.rainbowblock;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class RainbowBlock extends BlockCompressed {
	private static MapColor mapColor;

	public RainbowBlock(Boolean isPowered) {
		super(mapColor);
		this.isPowered = isPowered;
		if (isPowered) {
			setLightLevel(1F);
			setCreativeTab(null);
		}
	}

	@Override
	public MapColor getMapColor(int meta) {
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

	@SideOnly(Side.CLIENT)
	private IIcon[] texture;
	private boolean isPowered;
	static String[] subBlocks = new String[] { "Black", "White", "Red", "Yellow", "Green", "Cyan", "Blue", "Magenta" };

	@Override
	public int damageDropped(int meta) {
		return meta >= 0 && meta < subBlocks.length - 1 ? meta + 1 : 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = new IIcon[subBlocks.length];
		for (int i = 0; i < subBlocks.length; i++)
			texture[i] = iconRegister.registerIcon(Main.MODID + ":RainbowBlock_" + subBlocks[i]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < subBlocks.length; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return texture[meta];
	}

	@Override
	public Item getItemDropped(int i, Random random, int j) {
		return Item.getItemFromBlock(Main.rainbowBlock);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote)
			if (this.isPowered && !world.isBlockIndirectlyGettingPowered(x, y, z))
				world.scheduleBlockUpdate(x, y, z, this, 4);
			else if (!this.isPowered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, Main.rainbowBlockOn, meta, 2);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote && this.isPowered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			world.setBlock(x, y, z, Main.rainbowBlock, meta, 2);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote)
			if (this.isPowered && !world.isBlockIndirectlyGettingPowered(x, y, z))
				world.scheduleBlockUpdate(x, y, z, this, 4);
			else if (!this.isPowered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, Main.rainbowBlockOn, meta, 2);
	}

}
