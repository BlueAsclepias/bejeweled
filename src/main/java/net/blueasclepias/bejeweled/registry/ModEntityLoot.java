package net.blueasclepias.bejeweled.registry;

import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class ModEntityLoot extends EntityLootSubProvider {

    public ModEntityLoot() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return Stream.of(
                EntityType.DROWNED,
                EntityType.GUARDIAN,
                EntityType.ELDER_GUARDIAN
        );
    }

    @Override
    public void generate() {
        add(EntityType.DROWNED, waterMobLoot(.025f, .05f));
        add(EntityType.GUARDIAN, waterMobLoot(.05f, .1f));
        add(EntityType.ELDER_GUARDIAN, waterMobLoot(.1f, .2f));
    }

    private static LootTable.Builder waterMobLoot(float processedChance, float rawChance) {
        return LootTable.lootTable()
                // === PROCESSED ===
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.PEARL.get()))
                                .add(LootItem.lootTableItem(ModItems.TUBE_CORAL_BEAD.get()))
                                .add(LootItem.lootTableItem(ModItems.BRAIN_CORAL_BEAD.get()))
                                .add(LootItem.lootTableItem(ModItems.BUBBLE_CORAL_BEAD.get()))
                                .add(LootItem.lootTableItem(ModItems.FIRE_CORAL_BEAD.get()))
                                .add(LootItem.lootTableItem(ModItems.HORN_CORAL_BEAD.get()))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithLootingCondition
                                        .randomChanceAndLootingBoost(processedChance, 0.05f))
                                .apply(ApplyBonusCount.addUniformBonusCount(
                                        Enchantments.MOB_LOOTING, 1
                                ))
                )

                // === RAW ===
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.RAW_PEARL.get()))
                                .add(LootItem.lootTableItem(ModItems.RAW_TUBE_CORAL_POLYP.get()))
                                .add(LootItem.lootTableItem(ModItems.RAW_BRAIN_CORAL_POLYP.get()))
                                .add(LootItem.lootTableItem(ModItems.RAW_BUBBLE_CORAL_POLYP.get()))
                                .add(LootItem.lootTableItem(ModItems.RAW_FIRE_CORAL_POLYP.get()))
                                .add(LootItem.lootTableItem(ModItems.RAW_HORN_CORAL_POLYP.get()))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithLootingCondition
                                        .randomChanceAndLootingBoost(rawChance, 0.05f))
                                .apply(ApplyBonusCount.addUniformBonusCount(
                                        Enchantments.MOB_LOOTING, 1
                                ))
                );
    }

}
