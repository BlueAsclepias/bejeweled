package net.blueasclepias.bejeweled.client.model;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.state.GemState;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Item override hook for the shared {@code gem_item} model. Each time Minecraft resolves a
 * model for a specific {@link net.minecraft.world.item.ItemStack}, this class reads the gem
 * definition from {@link GemState} and decides whether that stack should keep the generic base
 * model or swap to a lazily baked processed-texture model. Returning the incoming model
 * unchanged when no gem data is present preserves vanilla's normal {@link ItemOverrides}
 * contract and keeps the generic tinted rendering path untouched.
 */
public class GemModelOverrides extends ItemOverrides {
    /**
     * Resolves a per-stack model based on the gem id stored in the stack's NBT. Stacks without
     * a valid gem definition simply continue using the already-selected base model.
     */
    @Override
    public BakedModel resolve(
            @NotNull BakedModel model,
            @NotNull ItemStack stack,
            @Nullable ClientLevel level,
            @Nullable LivingEntity entity,
            int seed
    ) {
        Optional<GemDefinition> def = GemState.getDefinition(stack);
        if (def.isEmpty()) return model;

        return GemModelBakery.resolve(def.get(), model);
    }
}
