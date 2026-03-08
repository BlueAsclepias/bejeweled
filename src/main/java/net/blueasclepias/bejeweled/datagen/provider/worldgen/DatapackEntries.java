package net.blueasclepias.bejeweled.datagen.provider.worldgen;

import net.blueasclepias.bejeweled.server.worldgen.bootstrap.WorldGenBootstrap;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Automate the registration of mod datapack entries such as worldgen features.
 */
public class DatapackEntries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER =
            new RegistrySetBuilder()
                    .add(Registries.CONFIGURED_FEATURE, WorldGenBootstrap::bootstrap)
                    .add(Registries.PLACED_FEATURE, WorldGenBootstrap::bootstrapPlaced);

    public DatapackEntries(PackOutput output,
                           CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MOD_ID));
    }
}
