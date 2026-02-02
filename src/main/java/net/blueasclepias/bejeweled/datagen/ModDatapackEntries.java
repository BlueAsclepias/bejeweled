package net.blueasclepias.bejeweled.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class ModDatapackEntries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER =
            new RegistrySetBuilder()
                    .add(Registries.CONFIGURED_FEATURE, ModWorldGen::bootstrap)
                    .add(Registries.PLACED_FEATURE, ModWorldGen::bootstrapPlaced);

    public ModDatapackEntries(PackOutput output,
                              CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MOD_ID));
    }
}
