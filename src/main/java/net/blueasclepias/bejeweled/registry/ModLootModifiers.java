package net.blueasclepias.bejeweled.registry;

import com.mojang.serialization.Codec;
import net.blueasclepias.bejeweled.loot.AddBeadsToSeaMobsModifier;
import net.blueasclepias.bejeweled.loot.AddGemsToChestsModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Registers Loot Modifiers for the mod.
 */
public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(
                    ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    MOD_ID
            );

    public static final RegistryObject<Codec<AddGemsToChestsModifier>> ADD_GEMS_TO_CHESTS =
            LOOT_MODIFIERS.register(
                    "add_gems_to_chests",
                    () -> AddGemsToChestsModifier.CODEC
            );

    public static final RegistryObject<Codec<AddBeadsToSeaMobsModifier>> ADD_BEADS_TO_SEA_MOBS =
            LOOT_MODIFIERS.register(
                    "add_beads_to_sea_mobs",
                    () -> AddBeadsToSeaMobsModifier.CODEC
            );

    public static void register(IEventBus modEventBus){
        LOOT_MODIFIERS.register(modEventBus);
    }
}

