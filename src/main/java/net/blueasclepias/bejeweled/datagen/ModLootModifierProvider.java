package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.Bejeweled;
import net.blueasclepias.bejeweled.modifier.VanillaOreDropModifier;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModLootModifierProvider extends GlobalLootModifierProvider {

    public ModLootModifierProvider(PackOutput output) {
        super(output, Bejeweled.MOD_ID);
    }

    @Override
    protected void start() {
        add("replace_diamond",
                new VanillaOreDropModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(
                                        fromNamespaceAndPath("minecraft", "blocks/diamond_ore")
                                ).build(),
                        },
                        ModItems.ROUGH_DIAMOND.get()
                )
        );

        add("replace_deepslate_diamond",
                new VanillaOreDropModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(
                                        fromNamespaceAndPath("minecraft", "blocks/deepslate_diamond_ore")
                                ).build(),
                        },
                        ModItems.ROUGH_DIAMOND.get()
                )
        );

        add("replace_emerald",
                new VanillaOreDropModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(
                                        fromNamespaceAndPath("minecraft", "blocks/emerald_ore")
                                ).build(),
                        },
                        ModItems.ROUGH_EMERALD.get()
                )
        );

        add("replace_deepslate_emerald",
                new VanillaOreDropModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(
                                        fromNamespaceAndPath("minecraft", "blocks/deepslate_emerald_ore")
                                ).build(),
                        },
                        ModItems.ROUGH_EMERALD.get()
                )
        );
    }
}
