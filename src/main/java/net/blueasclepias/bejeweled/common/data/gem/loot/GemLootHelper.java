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

public class GemLootHelper {

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