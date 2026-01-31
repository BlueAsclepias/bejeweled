package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.Bejeweled;
import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Bejeweled.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Storage blocks
        ModBlocks.STORAGE_BLOCKS.forEach(block ->
                simpleBlockWithItem(block.get(), cubeAll(block.get()))
        );

        // Ore blocks
        ModBlocks.ORE_BLOCKS.forEach((type, variants) -> {
            variants.forEach((base, block) -> {
                simpleBlockWithItem(block.get(), cubeAll(block.get()));
            });
        });
    }


}