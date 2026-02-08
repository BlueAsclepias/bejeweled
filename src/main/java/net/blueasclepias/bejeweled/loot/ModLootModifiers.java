package net.blueasclepias.bejeweled.loot;

import com.mojang.serialization.Codec;
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

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> REPLACE_GEM =
            LOOT_MODIFIERS.register(
                    "replace_gem",
                    () -> VanillaOreDropModifier.CODEC);

    public static final RegistryObject<Codec<AddGemsAndBeadsToChestsModifier>> ADD_GEMS_AND_BEADS_TO_CHESTS =
            LOOT_MODIFIERS.register(
                    "add_gems_and_beads_to_chests",
                    () -> AddGemsAndBeadsToChestsModifier.CODEC
            );

    public static void register(IEventBus modEventBus){
        LOOT_MODIFIERS.register(modEventBus);
    }
}

