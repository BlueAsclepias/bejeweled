package net.blueasclepias.bejeweled.common.data.storage.registry;

import net.blueasclepias.bejeweled.common.data.storage.definition.StorageBlockEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Registry that connects {@link StorageBlockEntry} data to the actual storage blocks created for those entries.
 * Entries are registered explicitly by
 * {@link net.blueasclepias.bejeweled.common.data.storage.defaults.StorageBlockEntries} when the mod's storage block
 * list is assembled; block registration later binds each created block back to its entry id. Datagen (recipes, tags,
 * loot, models) reuses this registry to iterate every entry and resolve its compression ingredient.
 */
public final class StorageBlockRegistry {
    private static final Map<ResourceLocation, StorageBlockEntry> ENTRIES = new LinkedHashMap<>();
    private static final Map<ResourceLocation, Block> BLOCKS = new LinkedHashMap<>();

    private StorageBlockRegistry() {}

    public static void register(@NotNull StorageBlockEntry entry) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MOD_ID, entry.id());
        if (ENTRIES.putIfAbsent(id, entry) != null) {
            throw new IllegalStateException("Duplicate storage block entry id: " + id);
        }
    }

    /**
     * Associates a newly registered block with a previously registered entry id. Missing ids fail fast instead of
     * silently creating an orphaned binding.
     */
    public static void bind(Block block, @NotNull ResourceLocation id) {
        if (!ENTRIES.containsKey(id)) {
            throw new IllegalStateException("No storage block entry registered for id " + id);
        }
        BLOCKS.put(id, block);
    }

    public static Map<ResourceLocation, Block> all() {
        return Collections.unmodifiableMap(BLOCKS);
    }

    public static Collection<Block> allBlocks() {
        return Collections.unmodifiableCollection(BLOCKS.values());
    }

    public static Collection<StorageBlockEntry> entries() {
        return Collections.unmodifiableCollection(ENTRIES.values());
    }

    public static ResourceLocation getIngredient(ResourceLocation id) {
        StorageBlockEntry entry = ENTRIES.get(id);
        return entry != null ? entry.ingredient() : null;
    }

}
