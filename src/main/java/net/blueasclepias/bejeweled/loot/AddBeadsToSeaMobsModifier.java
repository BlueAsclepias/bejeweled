package net.blueasclepias.bejeweled.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.blueasclepias.bejeweled.record.CoreType;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import java.util.Map;
import java.util.function.Supplier;


public class AddBeadsToSeaMobsModifier extends LootModifier {

    // === Base chances ===
    private static final float BASE_RAW_CHANCE = 0.05f; // 5%
    private static final float BASE_PROCESSED_CHANCE = 0.025f; // 2.5%

    // === Looting bonus per level ===
    private static final float LOOTING_BONUS = 0.015f; // +1.5% per looting level


    public static final Codec<AddBeadsToSeaMobsModifier> CODEC =
            RecordCodecBuilder.create(inst ->
                    codecStart(inst).apply(inst, AddBeadsToSeaMobsModifier::new)
            );

    public AddBeadsToSeaMobsModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot,
            LootContext context
    ) {
        RandomSource random = context.getRandom();
        int looting = context.getLootingModifier();
        float multiplier = mobMultiplier(context);

        // === Main roll ===
        Item result = rollExclusive(
                random,
                BASE_RAW_CHANCE,
                BASE_PROCESSED_CHANCE,
                multiplier,
                looting
        );

        if (result != null) {
            generatedLoot.add(new ItemStack(result));
        }

        // === Extra looting rolls ===
        for (int i = 0; i < extraRolls(looting); i++) {
            Item extra = rollExclusive(
                    random,
                    BASE_RAW_CHANCE,
                    BASE_PROCESSED_CHANCE,
                    multiplier,
                    0 // looting bonus already consumed
            );

            if (extra != null) {
                generatedLoot.add(new ItemStack(extra));
            }
        }

        return generatedLoot;
    }

    private static Item rollExclusive(
            RandomSource random,
            float rawBase,
            float processedBase,
            float multiplier,
            int looting
    ) {
        float processedChance = processedBase * multiplier + looting * LOOTING_BONUS;
        float rawChance       = rawBase       * multiplier + looting * LOOTING_BONUS;

        float roll = random.nextFloat();

        // Mutually Exclusive Rolls
        if (roll < processedChance)
            return pickWeighted(random, ModItems.POLISHED_BEADS);

        if (roll < processedChance + rawChance)
            return pickWeighted(random, ModItems.ROUGH_BEADS);

        return null;
    }


    private static int extraRolls(int looting) {
        return Math.max(0, looting);
    }

    private static float mobMultiplier(LootContext context) {
        Entity entity = context.getParam(LootContextParams.THIS_ENTITY);

        if (entity instanceof ElderGuardian) return 3.0f;
        if (entity instanceof Guardian) return 1.5f;
        return .5f; // Drowned
    }

    private static Item pickWeighted(RandomSource random, Map<Supplier<Item>, CoreType> gems) {
        int total = gems.values().stream().mapToInt(v -> v.rarity().weight).sum();
        if (total <= 0)
            throw new IllegalStateException("No weighted entries for gem pool");
        int roll = random.nextInt(total);

        for (var entry : gems.entrySet()) {
            roll -= entry.getValue().rarity().weight;
            if (roll < 0) return entry.getKey().get();
        }
        throw new IllegalStateException("Weighted roll failed");
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
