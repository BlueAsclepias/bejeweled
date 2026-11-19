package net.blueasclepias.bejeweled.common.registry;

import com.mojang.serialization.Codec;
import net.blueasclepias.bejeweled.common.loot.modifier.GemsToChestsModifier;
import net.blueasclepias.bejeweled.common.loot.modifier.GemsToMobsModifier;
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

    public static final RegistryObject<Codec<GemsToChestsModifier>> ADD_GEMS_TO_CHESTS =
            LOOT_MODIFIERS.register(
                    "add_gems_to_chests",
                    () -> GemsToChestsModifier.CODEC
            );

    public static final RegistryObject<Codec<GemsToMobsModifier>> ADD_GEMS_TO_MOBS =
            LOOT_MODIFIERS.register(
                    "add_gems_to_mobs",
                    () -> GemsToMobsModifier.CODEC
            );

    public static void register(IEventBus modEventBus){
        LOOT_MODIFIERS.register(modEventBus);
    }
}

