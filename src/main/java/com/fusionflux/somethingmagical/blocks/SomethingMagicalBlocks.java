package com.fusionflux.somethingmagical.blocks;

import com.fusionflux.somethingmagical.SomethingMagical;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SomethingMagicalBlocks {
    public static final CustomCake POISENEDCAKE = new CustomCake(FabricBlockSettings.of(Material.CAKE).nonOpaque());

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(SomethingMagical.MOD_ID, "the_cake_is_a_lie"), POISENEDCAKE);
        Registry.register(Registry.ITEM, new Identifier(SomethingMagical.MOD_ID, "the_cake_is_a_lie"), new BlockItem(POISENEDCAKE, new Item.Settings().group(ItemGroup.FOOD)));
    }
}
