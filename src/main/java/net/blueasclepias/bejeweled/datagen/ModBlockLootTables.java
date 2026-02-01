package net.blueasclepias.bejeweled.datagen;


import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        // === Storage blocks ===
        ModBlocks.STORAGE_BLOCKS.forEach(b -> dropSelf(b.get()));

        // === Ores ===
        ModBlocks.ORE_BLOCKS.forEach((type, variants) -> {
            variants.forEach((base, block) -> {
                add(block.get(),
                        createOreDrop(block.get(), type.drop().get())
                );
            });
        });

        add(ModBlocks.CORAL_POLYP.get(), createSingleItemTable(ModItems.ROUGH_CORAL_POLYP.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                .map(RegistryObject::get)
                .toList();
    }
}
