package net.blueasclepias.bejeweled.datagen;


import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
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
        ModBlocks.ORE_BLOCKS.forEach((type, variants) -> {
            variants.forEach((base, block) -> {
                add(block.get(),
                        createOreDrop(block.get(), type.drop().get())
                );
            });
        });

        ModBlocks.CORAL_POLYP_BLOCKS.forEach(block -> {
            createCoralPolypDrop(block.getId().getPath(), block.get());
        });
    }

    private void createCoralPolypDrop(String name, Block block) {
        String roughPolypName = "rough_" + name.replace("_block", "");
        Optional<RegistryObject<Item>> optItem = ModItems.ROUGH_CORAL_POLYPS.stream()
                .filter(
                        item -> item.getId().getPath().equals(roughPolypName)
                ).findAny();

        optItem.ifPresent(
                itemRegistryObject -> add(
                        block, createSingleItemTable(itemRegistryObject.get())
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                .map(RegistryObject::get)
                .toList();
    }
}
