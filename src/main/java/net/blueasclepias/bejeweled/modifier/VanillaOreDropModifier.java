package net.blueasclepias.bejeweled.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * A loot modifier that replaces the default drop of vanilla ores with a specified item,
 * unless the tool used has the Silk Touch enchantment.
 */
public class VanillaOreDropModifier extends LootModifier {

    private final Item replacement;

    public VanillaOreDropModifier(
            LootItemCondition[] conditions,
            Item replacement
    ) {
        super(conditions);
        this.replacement = replacement;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot,
            LootContext context
    ) {
        // If silk touch was used, DO NOTHING
        if (context.hasParam(LootContextParams.TOOL)) {
            ItemStack tool = context.getParam(LootContextParams.TOOL);
            if (tool.getEnchantmentLevel(Enchantments.SILK_TOUCH) > 0) {
                return generatedLoot;
            }
        }

        // Remove vanilla drops
        generatedLoot.clear();

        // Add replacement
        generatedLoot.add(new ItemStack(this.replacement));

        return generatedLoot;
    }


    @Override
    public Codec<? extends LootModifier> codec() {
        return CODEC;
    }

    public static final Codec<VanillaOreDropModifier> CODEC =
            RecordCodecBuilder.create(inst -> inst.group(
                    LOOT_CONDITIONS_CODEC
                            .fieldOf("conditions")
                            .forGetter(m -> m.conditions),
                    ForgeRegistries.ITEMS.getCodec()
                            .fieldOf("replacement")
                            .forGetter(m -> m.replacement)
            ).apply(inst, VanillaOreDropModifier::new));
}
