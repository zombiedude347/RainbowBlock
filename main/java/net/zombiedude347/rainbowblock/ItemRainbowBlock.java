package net.zombiedude347.rainbowblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemRainbowBlock extends ItemBlock {

	public ItemRainbowBlock(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int i = itemStack.getItemDamage();
		return i >= 0 && i < RainbowBlock.subBlocks.length ? "RainbowBlock_" + RainbowBlock.subBlocks[i]
				: "RainbowBlock";
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}
