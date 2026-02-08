package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.loot.AddBeadsToSeaMobsModifier;
import net.blueasclepias.bejeweled.loot.AddGemsAndBeadsToChestsModifier;
import net.blueasclepias.bejeweled.loot.VanillaOreDropModifier;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;
/**
 * Modifies vanilla loot tables data.
 */
public class ModLootModifierProvider extends GlobalLootModifierProvider {

    public ModLootModifierProvider(PackOutput output) {
        super(output, MOD_ID);
    }

    @Override
    protected void start() {
        add("replace_diamond",
                new VanillaOreDropModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(
                                        fromNamespaceAndPath("minecraft", "blocks/diamond_ore")
                                ).build(),
                        },
                        ModItems.ROUGH_DIAMOND.get()
                )
        );

        add("replace_deepslate_diamond",
                new VanillaOreDropModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(
                                        fromNamespaceAndPath("minecraft", "blocks/deepslate_diamond_ore")
                                ).build(),
                        },
                        ModItems.ROUGH_DIAMOND.get()
                )
        );

        add("replace_emerald",
                new VanillaOreDropModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(
                                        fromNamespaceAndPath("minecraft", "blocks/emerald_ore")
                                ).build(),
                        },
                        ModItems.ROUGH_EMERALD.get()
                )
        );

        add("replace_deepslate_emerald",
                new VanillaOreDropModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(
                                        fromNamespaceAndPath("minecraft", "blocks/deepslate_emerald_ore")
                                ).build(),
                        },
                        ModItems.ROUGH_EMERALD.get()
                )
        );

        add("add_gems_and_beads_to_chests",
                new AddGemsAndBeadsToChestsModifier(
                        new LootItemCondition[]{}
                )
        );

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
