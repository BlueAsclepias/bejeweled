package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.loot.AddBeadsToSeaMobsModifier;
import net.blueasclepias.bejeweled.loot.AddGemsToChestsModifier;
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
public class ModLootModifierProvider extends GlobalLootModifierProvider {

    public ModLootModifierProvider(PackOutput output) {
        super(output, MOD_ID);
    }

    @Override
    protected void start() {
        add("add_gems_to_chests",
                new AddGemsToChestsModifier(
                        new LootItemCondition[]{}
                )
        );

        // TODO: REPLACE
        add("add_beads_to_sea_mobs",
                new AddBeadsToSeaMobsModifier(
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
