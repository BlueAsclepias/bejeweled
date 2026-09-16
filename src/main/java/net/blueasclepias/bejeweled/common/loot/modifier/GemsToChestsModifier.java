package net.blueasclepias.bejeweled.common.loot.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.loot.GemLootHelper;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Global loot modifier that injects Bejeweled gem rolls into chest loot tables.
 * For loot tables whose path starts with {@code chests/}, it first removes vanilla diamonds, emeralds, and
 * amethyst shards, then rolls from every loaded definition with {@code generateLoot=true}. The raw and processed
 * chances are adjusted by depth, luck, and a few special-case chest types such as mineshafts, buried treasure,
 * and simple dungeons.
 */
public class GemsToChestsModifier extends LootModifier {

    public static final Codec<GemsToChestsModifier> CODEC =
            RecordCodecBuilder.create(inst ->
                    codecStart(inst).apply(inst, GemsToChestsModifier::new));

    public GemsToChestsModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    /**
     * Rewrites qualifying chest loot by stripping vanilla gem entries, then independently rolling one processed gem
     * and one raw gem using the context-sensitive chance calculations for that chest.
     */
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
            ItemStack result = rollWeighted(random, false);
            generatedLoot.add(result);
        }

        // Roll for raw gems
        if (random.nextFloat() < rawChance) {
            generatedLoot.add(rollWeighted(random, true));
        }

        return generatedLoot;
    }

    private static ItemStack rollWeighted(RandomSource random, boolean generateRaw) {
        Optional<GemDefinition> gem =
                GemLootHelper.pickWeighted(
                        GemDefinitionRegistry.getAllLoot().values(),
                        random
                );

        if(gem.isPresent()){
            return GemLootHelper.roll(gem.get(), random, generateRaw);
        }
        throw new IllegalStateException("Weighted roll failed");
    }

    @Override
    public Codec<? extends LootModifier> codec() {
        return CODEC;
    }
}
