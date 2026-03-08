package net.blueasclepias.bejeweled.datagen.provider.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
/**
 * Provides loot tables data for the mod.
 */
public class LootTableProvider extends net.minecraft.data.loot.LootTableProvider {

    public LootTableProvider(PackOutput output) {
        super(
                output,
                Set.of(),
                List.of(
                        new SubProviderEntry(
                                BlockLootTables::new,
                                LootContextParamSets.BLOCK
                        )
                )
        );
    }
}
