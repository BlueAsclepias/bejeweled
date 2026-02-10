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
        ModBlocks.STORAGE_BLOCKS.forEach(block -> dropSelf(block.get()));

        // === Ores ===
        ModBlocks.ORE_BLOCKS.forEach((def, variants) -> {
            variants.forEach((variant, block) -> {
                add(
                        block.get(),
                        createOreDrop(block.get(), def.drop().get())
                );
            });
        });

        // === Coral Polyps ===
        ModBlocks.CORAL_POLYP_BLOCKS.forEach(block -> {
            String name = block.getId().getPath().replace("_block", "");
            ModItems.ROUGH_BEADS.forEach((item, def) -> {
                if (name.contains(def.name())) {
                    add(block.get(), createSingleItemTable(item.get()));
                }
            });
        });

        // === Workstation ===
        dropSelf(ModBlocks.GEM_CUTTING_TABLE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                .map(RegistryObject::get)
                .toList();
    }
}
