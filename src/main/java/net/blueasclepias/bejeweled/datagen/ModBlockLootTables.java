package net.blueasclepias.bejeweled.datagen;


import net.blueasclepias.bejeweled.material.registry.ModCoralPolypRegistry;
import net.blueasclepias.bejeweled.material.registry.ModOreRegistry;
import net.blueasclepias.bejeweled.material.registry.ModStorageBlockRegistry;
import net.blueasclepias.bejeweled.registry.ModBlocks;
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

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        // === Storage blocks ===
        ModStorageBlockRegistry.allBlocks().forEach(this::dropSelf);

        // === Ores ===
        ModOreRegistry.allBlocksByFeature().forEach((feat, block) -> {
            Item item = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(feat.definition().drop()));
            if(item == Items.AIR)
                throw new IllegalStateException("No drop for " + feat.id());
            add(block, createOreDrop(block, item));
        });

        // === Coral Polyps ===
        ModCoralPolypRegistry.all().forEach((id, block) -> {
            String path = "raw_" + id.getPath().replace("_block", "");
            Item item = Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(fromNamespaceAndPath(MOD_ID, path)));
            if(item == Items.AIR)
                throw new IllegalStateException("Missing item for coral polyp drop: " + path);
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
