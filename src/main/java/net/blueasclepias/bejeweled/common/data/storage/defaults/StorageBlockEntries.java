package net.blueasclepias.bejeweled.common.data.storage.defaults;

import net.blueasclepias.bejeweled.common.data.storage.definition.StorageBlockEntry;
import net.blueasclepias.bejeweled.common.data.storage.registry.StorageBlockRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;

import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * The mod's complete list of generated storage blocks.
 * Each {@link StorageBlockEntry} is plain data; registering it into {@link StorageBlockRegistry} happens explicitly
 * via {@link #register(StorageBlockEntry)} below instead of as a hidden constructor side effect. {@link #ALL} lets
 * consumers (block registration, datagen) iterate every entry without needing one named field per storage block.
 */
public final class StorageBlockEntries {

    public static final StorageBlockEntry AQUAMARINE = register(new StorageBlockEntry(
            "aquamarine_block", modLoc("aquamarine"), MapColor.COLOR_CYAN
    ));

    public static final StorageBlockEntry RUBY = register(new StorageBlockEntry(
            "ruby_block", modLoc("ruby"), MapColor.COLOR_RED
    ));

    public static final StorageBlockEntry SAPPHIRE = register(new StorageBlockEntry(
            "sapphire_block", modLoc("sapphire"), MapColor.COLOR_BLUE
    ));

    public static final StorageBlockEntry GARNET = register(new StorageBlockEntry(
            "garnet_block", modLoc("garnet"), MapColor.COLOR_RED
    ));

    public static final StorageBlockEntry TOPAZ = register(new StorageBlockEntry(
            "topaz_block", modLoc("topaz"), MapColor.COLOR_ORANGE
    ));

    public static final StorageBlockEntry TURQUOISE = register(new StorageBlockEntry(
            "turquoise_block", modLoc("turquoise"), MapColor.COLOR_CYAN
    ));

    public static final StorageBlockEntry JADE = register(new StorageBlockEntry(
            "jade_block", modLoc("jade"), MapColor.COLOR_LIGHT_GREEN
    ));

    public static final StorageBlockEntry OPAL = register(new StorageBlockEntry(
            "opal_block", modLoc("opal"), MapColor.COLOR_LIGHT_GRAY
    ));

    public static final StorageBlockEntry PERIDOT = register(new StorageBlockEntry(
            "peridot_block", modLoc("peridot"), MapColor.COLOR_LIGHT_GREEN
    ));

    public static final StorageBlockEntry PEARL = register(new StorageBlockEntry(
            "pearl_block", modLoc("pearl"), MapColor.COLOR_LIGHT_GRAY
    ));

    /**
     * Every storage block entry the mod defines, in declaration order. Block/item registration and datagen iterate
     * this list instead of needing a dedicated field per storage block.
     */
    public static final List<StorageBlockEntry> ALL = List.of(
            AQUAMARINE, RUBY, SAPPHIRE, GARNET, TOPAZ, TURQUOISE, JADE, OPAL, PERIDOT, PEARL
    );

    private static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private static StorageBlockEntry register(StorageBlockEntry entry) {
        StorageBlockRegistry.register(entry);
        return entry;
    }
}
