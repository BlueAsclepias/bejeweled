package net.blueasclepias.bejeweled.common.loot.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.loot.GemLootHelper;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Generalized global loot modifier that adds Bejeweled gem drops to any entity's death loot.
 * Unlike a hardcoded per-mob subclass, every tunable - which mob(s) it applies to (via the standard
 * {@code conditions} array, e.g. an entity-type predicate), the raw/processed drop chances, the per-Looting-level
 * bonus, and which gems are eligible - is plain modifier data. A datapack can therefore target any mob in the game
 * with its own instance of {@code bejeweled:add_gems_to_mobs}, without writing any Java code. The mod's own shipped
 * instances (see {@code LootModifierProvider}) reproduce the original drowned/guardian/elder guardian bead drops
 * purely through these data fields rather than an {@code instanceof} chain.
 * <p>
 * Eligibility narrows in two independent ways: {@link #category} restricts the pool to one {@link GemCategory}
 * (e.g. "creepers only drop gemstones, never beads"), while {@link #gems} restricts it to one or more specific gem
 * ids - from this mod, another mod, or a datapack - regardless of category (e.g. "zombies only drop
 * {@code bejeweled:garnet}" or "zombies drop garnet or a modded ruby"). When {@link #gems} is non-empty it takes
 * precedence over {@link #category} and also bypasses each definition's {@code generate_loot} flag, since naming a
 * gem explicitly is a stronger signal of intent than the generic "can this gem show up in random loot" opt-in.
 */
public class GemsToMobsModifier extends LootModifier {

    public static final float DEFAULT_RAW_CHANCE = 0.05f;
    public static final float DEFAULT_PROCESSED_CHANCE = 0.025f;
    public static final float DEFAULT_LOOTING_BONUS = 0.015f;

    private final float rawChance;
    private final float processedChance;
    private final float lootingBonus;
    private final Optional<GemCategory> category;
    private final List<ResourceLocation> gems;

    public static final Codec<GemsToMobsModifier> CODEC = RecordCodecBuilder.create(inst ->
            codecStart(inst)
                    .and(Codec.FLOAT.optionalFieldOf("raw_chance", DEFAULT_RAW_CHANCE).forGetter(m -> m.rawChance))
                    .and(Codec.FLOAT.optionalFieldOf("processed_chance", DEFAULT_PROCESSED_CHANCE).forGetter(m -> m.processedChance))
                    .and(Codec.FLOAT.optionalFieldOf("looting_bonus", DEFAULT_LOOTING_BONUS).forGetter(m -> m.lootingBonus))
                    .and(categoryCodec().optionalFieldOf("category").forGetter(m -> m.category))
                    .and(ResourceLocation.CODEC.listOf().optionalFieldOf("gems", List.of()).forGetter(m -> m.gems))
                    .apply(inst, GemsToMobsModifier::new)
    );

    public GemsToMobsModifier(
            LootItemCondition[] conditionsIn,
            float rawChance,
            float processedChance,
            float lootingBonus,
            Optional<GemCategory> category,
            List<ResourceLocation> gems
    ) {
        super(conditionsIn);
        this.rawChance = rawChance;
        this.processedChance = processedChance;
        this.lootingBonus = lootingBonus;
        this.category = category;
        this.gems = gems;
    }

    private static Codec<GemCategory> categoryCodec() {
        return Codec.STRING.xmap(GemCategory::fromString, category -> category.name().toLowerCase());
    }

    /**
     * Applies one mutually exclusive raw-vs-processed roll, then one bonus reroll per Looting level. All chances
     * come from this instance's own data; the {@code conditions} array (checked before {@code doApply} runs at all)
     * is what determines which mob(s) this applies to.
     */
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot,
            LootContext context
    ) {
        RandomSource random = context.getRandom();
        int looting = context.getLootingModifier();

        ItemStack result = rollExclusive(random, looting);
        if (result != null) {
            generatedLoot.add(result);
        }

        // One bonus reroll per Looting level; the looting bonus itself was already folded into the main roll above.
        for (int i = 0; i < Math.max(0, looting); i++) {
            ItemStack extra = rollExclusive(random, 0);
            if (extra != null) {
                generatedLoot.add(extra);
            }
        }

        return generatedLoot;
    }

    @Nullable
    private ItemStack rollExclusive(RandomSource random, int looting) {
        float processed = processedChance + looting * lootingBonus;
        float raw = rawChance + looting * lootingBonus;

        float roll = random.nextFloat();

        if (roll < processed) return rollWeighted(random, false);
        if (roll < processed + raw) return rollWeighted(random, true);

        return null;
    }

    /**
     * Resolves the eligible gem pool for this roll. An explicit {@link #gems} list wins outright (and bypasses
     * {@code generate_loot}, since the author named these gems on purpose); otherwise an optional {@link #category}
     * narrows the usual loot-eligible pool, falling back to every loot-eligible gem of any category.
     */
    @Nullable
    private ItemStack rollWeighted(RandomSource random, boolean generateRaw) {
        Collection<GemDefinition> pool;
        if (!gems.isEmpty()) {
            pool = gems.stream()
                    .map(GemDefinitionRegistry::getDefinition)
                    .filter(Objects::nonNull)
                    .toList();
        } else if (category.isPresent()) {
            pool = GemDefinitionRegistry.getAllLootByCategory(category.get()).values();
        } else {
            pool = GemDefinitionRegistry.getAllLoot().values();
        }

        return GemLootHelper.pickWeighted(pool, random)
                .map(def -> GemLootHelper.roll(def, random, generateRaw))
                .orElse(null);
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
