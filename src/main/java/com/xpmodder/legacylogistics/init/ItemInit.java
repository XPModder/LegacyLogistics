package com.xpmodder.legacylogistics.init;

import com.xpmodder.legacylogistics.LegacyLogistics;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LegacyLogistics.MODID);


    public static final RegistryObject<Item> BASIC_PIPE_ITEM = ITEMS.register("basic_pipe", () -> new BlockItem(BlockInit.BASIC_PIPE.get(), new Item.Properties().tab(LegacyLogistics.TAB)));


}
