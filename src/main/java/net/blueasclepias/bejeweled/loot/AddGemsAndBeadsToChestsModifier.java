package net.blueasclepias.bejeweled.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.blueasclepias.bejeweled.record.CoreType;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.loot.LootModifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AddGemsAndBeadsToChestsModifier extends LootModifier {

    public static final Codec<AddGemsAndBeadsToChestsModifier> CODEC =
            RecordCodecBuilder.create(inst ->
                    codecStart(inst).apply(inst, AddGemsAndBeadsToChestsModifier::new));

    // Lazy cached pools
    private static Map<Supplier<Item>, CoreType> PROCESSED_POOL;
    private static Map<Supplier<Item>, CoreType> processedPool() {
        if (PROCESSED_POOL == null) {
            PROCESSED_POOL = new HashMap<>();
            PROCESSED_POOL.putAll(ModItems.CUT_GEMS);
            PROCESSED_POOL.putAll(ModItems.POLISHED_BEADS);
        }
        return PROCESSED_POOL;
    }

    private static Map<Supplier<Item>, CoreType> RAW_POOL;
    private static Map<Supplier<Item>, CoreType> rawPool() {
        if (RAW_POOL == null) {
            RAW_POOL = new HashMap<>();
            RAW_POOL.putAll(ModItems.ROUGH_GEMS);
            RAW_POOL.putAll(ModItems.ROUGH_BEADS);
        }
        return RAW_POOL;
    }

    public AddGemsAndBeadsToChestsModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot,
            LootContext context
    ) {
        ResourceLocation table = context.getQueriedLootTableId();
        if (!table.getPath().startsWith("chests/")) return generatedLoot;

        // Remove vanilla gems first, to avoid conflicts with our custom pools.
        generatedLoot.removeIf(stack ->
                stack.is(Items.DIAMOND) || stack.is(Items.EMERALD) || stack.is(Items.AMETHYST_SHARD)
        );

        RandomSource random = context.getRandom();

        // === Scale chance by depth ===
        Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);
        double y = origin != null ? origin.y : 64; // fallback

        // Normalize depth factor (example: Y 0–64)
        double depthFactor = Mth.clamp((64 - y) / 64.0, 0.0, 1.0);

        // Base chances
        double roughChance = 0.125;
        double cutChance   = 0.075;

        // Scale with depth
        roughChance += depthFactor * 0.05; // up to +5%
        cutChance   += depthFactor * 0.025; // up to +2.5%

        // Luck scaling (1% per luck point)
        float luck = Mth.clamp(context.getLuck(), 0.0f, 10f);
        roughChance += luck * 0.01;

        ResourceLocation lootTable = context.getQueriedLootTableId();
        String path = lootTable.getPath();

        // Increase chances for certain loot tables
        boolean isBuriedTreasure = path.contains("buried_treasure");
        boolean isDungeon = path.contains("simple_dungeon");
        boolean isMineshaft = path.contains("abandoned_mineshaft");

        if (isMineshaft)
            roughChance *= 1.5;
        if(isBuriedTreasure)
            cutChance *= 1.5;
        if(isDungeon) {
            roughChance *= 1.2;
            cutChance *= 1.2;
        }

        // Roll for cut gems
        if (random.nextFloat() < cutChance) {
            generatedLoot.add(new ItemStack(pickWeighted(random, processedPool())));
        }

        // Roll for rough gems
        if (random.nextFloat() < roughChance) {
            generatedLoot.add(new ItemStack(pickWeighted(random, rawPool())));
        }

        return generatedLoot;
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
    public Codec<? extends LootModifier> codec() {
        return CODEC;
    }
}

