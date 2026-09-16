package net.blueasclepias.bejeweled.common.data.gem.loot;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.item.factory.GemItemFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Shared loot utility for turning loaded gem definitions into actual drops.
 * Raw rolls return the registered item whose id matches the definition, while processed rolls create the generic
 * Bejeweled cut-gem item with a freshly randomized grade. Weighted selection ignores definitions whose rarity weight
 * is not positive, then performs a standard cumulative-weight roll across the remaining pool.
 */
public class GemLootHelper {

    /**
     * Builds the item stack represented by the chosen definition. {@code generateRaw} keeps the definition's own item,
     * while processed drops are converted into the shared cut-gem item with a random grade.
     */
    public static ItemStack roll(GemDefinition def, RandomSource random, boolean generateRaw) {
        if(generateRaw) {
            ResourceLocation gemId = def.id();
            Item item = ForgeRegistries.ITEMS.getValue(gemId);
            if (item == null)
                throw new IllegalStateException("Missing item for GemDefinition: " + gemId);
            return new ItemStack(item);
        } else {
            return GemItemFactory.create(def, GemGrade.random(random));
        }
    }

    /**
     * Chooses one definition using {@link GemDefinition#rarity()} weights after discarding entries that cannot roll.
     * Empty pools, or pools whose remaining weights sum to zero, return {@link Optional#empty()}.
     */
    public static Optional<GemDefinition> pickWeighted(
            Collection<GemDefinition> pool,
            RandomSource random
    ) {

        if (pool.isEmpty()) return Optional.empty();

        List<GemDefinition> validPool = pool.stream()
                .filter(def -> def.rarity().weight > 0)
                .toList();

        if(validPool.isEmpty()) return Optional.empty();

        int totalWeight = validPool.stream()
                .mapToInt(def -> def.rarity().weight)
                .sum();

        int roll = random.nextInt(totalWeight);

        int running = 0;

        for(GemDefinition def : validPool) {
            running += def.rarity().weight;

            if(roll < running) {
                return Optional.of(def);
            }
        }

        return Optional.empty();
    }
}