package net.blueasclepias.bejeweled.modifier;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> MODIFIERS =
            DeferredRegister.create(
                    ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    MOD_ID
            );

    public static final RegistryObject<Codec<VanillaOreDropModifier>> REPLACE_GEM =
            MODIFIERS.register("replace_gem", () -> VanillaOreDropModifier.CODEC);

    public static void register(IEventBus modEventBus){
        MODIFIERS.register(modEventBus);
    }
}

