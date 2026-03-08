package net.blueasclepias.bejeweled.datagen.provider.loot;

import net.blueasclepias.bejeweled.common.registry.ModItems;
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

public class EntityLootProvider extends EntityLootSubProvider {

    public EntityLootProvider() {
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
        add(EntityType.DROWNED, mobLoot(.05f));
        add(EntityType.GUARDIAN, mobLoot(.1f));
        add(EntityType.ELDER_GUARDIAN, mobLoot(.2f));
    }

    private static LootTable.Builder mobLoot(float chance) {
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.PEARL.get()))
                                .add(LootItem.lootTableItem(ModItems.BLUE_CORAL.get()))
                                .add(LootItem.lootTableItem(ModItems.PINK_CORAL.get()))
                                .add(LootItem.lootTableItem(ModItems.PURPLE_CORAL.get()))
                                .add(LootItem.lootTableItem(ModItems.RED_CORAL.get()))
                                .add(LootItem.lootTableItem(ModItems.YELLOW_CORAL.get()))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithLootingCondition
                                        .randomChanceAndLootingBoost(chance, 0.05f))
                                .apply(ApplyBonusCount.addUniformBonusCount(
                                        Enchantments.MOB_LOOTING, 1
                                ))
                );
    }

}
