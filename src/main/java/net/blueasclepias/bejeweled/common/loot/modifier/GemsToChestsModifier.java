package net.blueasclepias.bejeweled.common.loot.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.blueasclepias.bejeweled.common.item.factory.GemItemFactory;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class GemsToChestsModifier extends LootModifier {

    public static final Codec<GemsToChestsModifier> CODEC =
            RecordCodecBuilder.create(inst ->
                    codecStart(inst).apply(inst, GemsToChestsModifier::new));

    public GemsToChestsModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(
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
        double rawChance = 0.125;
        double processedChance   = 0.075;

        // Scale with depth
        rawChance += depthFactor * 0.05; // up to +5%
        processedChance   += depthFactor * 0.025; // up to +2.5%

        // Luck scaling (1% per luck point)
        float luck = Mth.clamp(context.getLuck(), 0.0f, 10f);
        rawChance += luck * 0.01;

        ResourceLocation lootTable = context.getQueriedLootTableId();
        String path = lootTable.getPath();

        // Increase chances for certain loot tables
        boolean isBuriedTreasure = path.contains("buried_treasure");
        boolean isDungeon = path.contains("simple_dungeon");
        boolean isMineshaft = path.contains("abandoned_mineshaft");

        if (isMineshaft)
            rawChance *= 1.5;
        if(isBuriedTreasure)
            processedChance *= 1.5;
        if(isDungeon) {
            rawChance *= 1.2;
            processedChance *= 1.2;
        }

        // Roll for gems
        if (random.nextFloat() < processedChance) {
            ItemStack result = pickWeighted(random, false);
            generatedLoot.add(result);
        }

        // Roll for raw gems
        if (random.nextFloat() < rawChance) {
            generatedLoot.add(pickWeighted(random, true));
        }

        return generatedLoot;
    }

    private static ItemStack pickWeighted(RandomSource random, boolean generateRaw) {
        Map<ResourceLocation, GemDefinition> definitions = GemDefinitionRegistry.getAllLoot();
        int total = definitions.values().stream()
                .mapToInt(def -> def.rarity().weight).sum();

        if (total <= 0)
            throw new IllegalStateException("No weighted entries for gem pool");
        int roll = random.nextInt(total);

        for (var entry : definitions.entrySet()) {
            roll -= entry.getValue().rarity().weight;
            if (roll < 0) {
                if(generateRaw) {
                    Item item = ForgeRegistries.ITEMS.getValue(entry.getKey());
                    if (item == null)
                        throw new IllegalStateException("Missing item for GemDefinition: " + entry.getKey());
                    return new ItemStack(item);
                } else {
                    return GemItemFactory.create(entry.getValue(), GemGrade.random(random));
                }
            }
        }
        throw new IllegalStateException("Weighted roll failed");
    }

    @Override
    public Codec<? extends LootModifier> codec() {
        return CODEC;
    }
}

