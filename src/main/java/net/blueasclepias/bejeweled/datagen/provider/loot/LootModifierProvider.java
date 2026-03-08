package net.blueasclepias.bejeweled.datagen.provider.loot;

import net.blueasclepias.bejeweled.common.loot.modifier.BeadsToSeaMobsModifier;
import net.blueasclepias.bejeweled.common.loot.modifier.GemsToChestsModifier;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

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

        // TODO: REPLACE
        add("add_beads_to_sea_mobs",
                new BeadsToSeaMobsModifier(
                        new LootItemCondition[]{
                                LootItemKilledByPlayerCondition
                                        .killedByPlayer().build(),
                                LootItemEntityPropertyCondition
                                        .hasProperties(
                                                LootContext.EntityTarget.THIS,
                                                EntityPredicate.Builder.entity()
                                                        .of(EntityType.DROWNED)
                                        ).or(
                                                LootItemEntityPropertyCondition
                                                        .hasProperties(
                                                                LootContext.EntityTarget.THIS,
                                                                EntityPredicate.Builder.entity()
                                                                        .of(EntityType.GUARDIAN)
                                                        )
                                        ).or(
                                                LootItemEntityPropertyCondition
                                                        .hasProperties(
                                                                LootContext.EntityTarget.THIS,
                                                                EntityPredicate.Builder.entity()
                                                                        .of(EntityType.ELDER_GUARDIAN)
                                                        )
                                        ).build()
                        }
                )
        );
    }
}
