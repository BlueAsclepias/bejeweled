package net.blueasclepias.bejeweled.datagen.provider.loot;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.loot.modifier.GemsToChestsModifier;
import net.blueasclepias.bejeweled.common.loot.modifier.GemsToMobsModifier;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

import java.util.List;
import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
/**
 * Modifies vanilla loot tables data.
 */
public class LootModifierProvider extends GlobalLootModifierProvider {

    public LootModifierProvider(PackOutput output) {
        super(output, MOD_ID);
    }

    @Override
    protected void start() {
        add("add_gems_to_chests",
                new GemsToChestsModifier(
                        new LootItemCondition[]{}
                )
        );

        // Reproduces the mod's original sea-mob bead drops through plain modifier data (chance/looting-bonus/
        // category) rather than a hardcoded instanceof chain, so any other mob can be targeted the same way by any
        // datapack simply by shipping its own add_gems_to_mobs instance with different conditions/chances.
        add("add_gems_to_drowned", gemsToMobs(EntityType.DROWNED, 0.025f, 0.0125f));
        add("add_gems_to_guardians", gemsToMobs(EntityType.GUARDIAN, 0.075f, 0.0375f));
        add("add_gems_to_elder_guardians", gemsToMobs(EntityType.ELDER_GUARDIAN, 0.15f, 0.075f));
    }

    private static GemsToMobsModifier gemsToMobs(EntityType<?> type, float rawChance, float processedChance) {
        return new GemsToMobsModifier(
                new LootItemCondition[]{
                        LootItemKilledByPlayerCondition.killedByPlayer().build(),
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(type)
                        ).build()
                },
                rawChance,
                processedChance,
                GemsToMobsModifier.DEFAULT_LOOTING_BONUS,
                Optional.of(GemCategory.BEAD),
                List.of()
        );
    }
}
