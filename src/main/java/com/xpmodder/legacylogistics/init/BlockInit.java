package com.xpmodder.legacylogistics.init;

import com.xpmodder.legacylogistics.LegacyLogistics;
import com.xpmodder.legacylogistics.block.BasicPipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LegacyLogistics.MODID);

    public static final RegistryObject<Block> BASIC_PIPE = BLOCKS.register("basic_pipe", () -> new BasicPipe(BlockBehaviour.Properties.of(Material.STONE)));



}
