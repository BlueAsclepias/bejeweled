package net.blueasclepias.bejeweled.datagen.provider.loot;


import net.blueasclepias.bejeweled.common.data.coral.registry.CoralPolypRegistry;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreFeatureRegistry;
import net.blueasclepias.bejeweled.common.data.storage.registry.StorageBlockRegistry;
import net.blueasclepias.bejeweled.common.registry.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {

    public BlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        // === Storage blocks ===
        StorageBlockRegistry.allBlocks().forEach(this::dropSelf);

        // === Ores ===
        OreFeatureRegistry.allBlocksByFeature().forEach((feat, block) -> {
            Item item = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(feat.definition().drop()));
            if(item == Items.AIR)
                throw new IllegalStateException("No drop for " + feat.id());
            add(block, createOreDrop(block, item));
        });

        // === Coral Polyps ===
        CoralPolypRegistry.all().forEach((id, block) -> {
            Item item = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(block.getDrop()));
            if(item == Items.AIR)
                throw new IllegalStateException("No drop for " + id);
            add(block, createSingleItemTable(item));
        });

        // === Workstation ===
        dropSelf(ModBlocks.GEM_CUTTING_TABLE.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                .map(RegistryObject::get)
                .toList();
    }
}
