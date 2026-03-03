package net.blueasclepias.bejeweled.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.blueasclepias.bejeweled.data.accessor.GemDefinitionAccessor;
import net.blueasclepias.bejeweled.data.definition.gem.GemCategory;
import net.blueasclepias.bejeweled.data.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.data.definition.gem.GemGrade;
import net.blueasclepias.bejeweled.data.state.gem.GemState;
import net.blueasclepias.bejeweled.item.GemItemFactory;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Map;


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
    protected @NotNull ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot,
            LootContext context
    ) {
        RandomSource random = context.getRandom();
        int looting = context.getLootingModifier();
        float multiplier = mobMultiplier(context);

        // === Main roll ===
        ItemStack result = rollExclusive(
                random,
                multiplier,
                looting
        );

        if (result != null) {
            generatedLoot.add(result);
        }

        // === Extra looting rolls ===
        for (int i = 0; i < extraRolls(looting); i++) {
            ItemStack extra = rollExclusive(
                    random,
                    multiplier,
                    0 // looting bonus already consumed
            );

            if (extra != null) {
                generatedLoot.add(extra);
            }
        }

        return generatedLoot;
    }

    private static ItemStack rollExclusive(
            RandomSource random,
            float multiplier,
            int looting
    ) {
        float processedChance = AddBeadsToSeaMobsModifier.BASE_PROCESSED_CHANCE * multiplier + looting * LOOTING_BONUS;
        float rawChance = AddBeadsToSeaMobsModifier.BASE_RAW_CHANCE * multiplier + looting * LOOTING_BONUS;

        float roll = random.nextFloat();

        // Mutually Exclusive Rolls
        if (roll < processedChance) {
            ItemStack result = pickWeighted(random, false);
            GemState.set(result, GemGrade.random(random));
            return result;
        }

        if (roll < processedChance + rawChance)
            return pickWeighted(random, true);

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

    private static ItemStack pickWeighted(RandomSource random, boolean isRaw) {
        Map<ResourceLocation, GemDefinition> definitions = GemDefinitionAccessor.getAllLootByCategory(GemCategory.BEAD);
        int total = definitions.values().stream()
                .mapToInt(def -> def.rarity().weight).sum();

        if (total <= 0)
            throw new IllegalStateException("No weighted entries for gem pool");
        int roll = random.nextInt(total);

        for (var entry : definitions.entrySet()) {
            roll -= entry.getValue().rarity().weight;
            if (roll < 0) {
                if(isRaw) {
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
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
