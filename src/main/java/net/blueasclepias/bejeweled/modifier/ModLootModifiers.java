package net.blueasclepias.bejeweled.modifier;

import com.mojang.serialization.Codec;
import net.blueasclepias.bejeweled.Bejeweled;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> MODIFIERS =
            DeferredRegister.create(
                    ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    Bejeweled.MOD_ID
            );

    public static final RegistryObject<Codec<VanillaOreDropModifier>> REPLACE_GEM =
            MODIFIERS.register("replace_gem", () -> VanillaOreDropModifier.CODEC);

}

