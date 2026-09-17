package net.blueasclepias.bejeweled.common.data.ore.registry;

import net.blueasclepias.bejeweled.common.data.ore.definition.OreBlockVariant;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreDefinition;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Registry that connects {@link OreEntry} data to the actual ore blocks created for those entries.
 * Entries are registered explicitly by {@link net.blueasclepias.bejeweled.common.data.ore.defaults.OreEntries} when
 * the mod's ore list is assembled; block registration later binds each created block back to its entry id. Worldgen
 * and datagen reuse this registry to iterate every entry and to resolve the block bound to a specific ore definition
 * and block variant pair. The reverse lookup compares definitions and variants by instance identity, so callers are
 * expected to use the shared objects already stored in the registry (e.g. {@code OreDefinitions.AQUAMARINE}).
 */
public final class OreRegistry {

    private static final Map<ResourceLocation, OreEntry> ENTRIES = new LinkedHashMap<>();
    private static final Map<OreEntry, Block> BLOCKS = new LinkedHashMap<>();

    private OreRegistry() {}

    public static void register(@NotNull OreEntry entry) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MOD_ID, entry.id());
        if (ENTRIES.putIfAbsent(id, entry) != null) {
            throw new IllegalStateException("Duplicate ore entry id: " + id);
        }
    }

    /**
     * Associates a newly registered block with a previously registered entry id. Missing ids fail fast instead of
     * silently creating an orphaned binding.
     */
    public static void bind(Block block, @NotNull ResourceLocation id) {
        OreEntry entry = Objects.requireNonNull(ENTRIES.get(id), () -> "No ore entry registered for id " + id);
        BLOCKS.put(entry, block);
    }

    public static Map<ResourceLocation, OreEntry> all() {
        return Collections.unmodifiableMap(ENTRIES);
    }

    public static Collection<OreEntry> entries() {
        return Collections.unmodifiableCollection(ENTRIES.values());
    }

    public static Collection<Block> allBlocks() {
        return Collections.unmodifiableCollection(BLOCKS.values());
    }

    public static Map<OreEntry, Block> blocksByEntry() {
        return Collections.unmodifiableMap(BLOCKS);
    }

    /**
     * Resolves the block bound to the entry whose definition and variant are the exact instances supplied here.
     */
    public static Optional<Block> blockFor(OreDefinition definition, OreBlockVariant variant) {
        return BLOCKS.entrySet().stream()
                .filter(e -> e.getKey().definition() == definition && e.getKey().variant() == variant)
                .map(Map.Entry::getValue)
                .findFirst();
    }
}
