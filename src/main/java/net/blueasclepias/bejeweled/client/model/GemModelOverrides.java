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
 * Swaps the generic "gem_item" model for a lazily-baked, gem-specific model
 * whenever the stack carries a {@link GemDefinition} that ships its own
 * "processed" texture (from this mod, another mod, or a datapack). Falls
 * back to the passed-in (generic, tinted) model otherwise, exactly like
 * vanilla's own {@link ItemOverrides#resolve} no-op contract.
 */
public class GemModelOverrides extends ItemOverrides {
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
