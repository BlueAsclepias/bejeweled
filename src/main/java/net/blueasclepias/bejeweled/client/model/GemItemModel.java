package net.blueasclepias.bejeweled.client.model;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Thin wrapper around the baked {@code gem_item} inventory model that replaces only the parts
 * needed for per-stack gem selection. All normal baked-model behavior still comes from the
 * wrapped base model, but {@link #getOverrides()} now points at {@link GemModelOverrides} so a
 * stack can swap itself to a gem-specific baked model at render time. The wrapper also forces
 * {@link #isCustomRenderer()} to stay false, ensuring gem items render through Minecraft's
 * normal static quad pipeline instead of a per-frame Java renderer. Its explicit
 * {@link #getTransforms()} delegation is important as well: the previous wrapper forgot that
 * override and accidentally fell back to {@link ItemTransforms#NO_TRANSFORMS}.
 */
public class GemItemModel implements BakedModel {

    private final BakedModel base;
    private final ItemOverrides overrides = new GemModelOverrides();

    public GemItemModel(BakedModel base) {
        this.base = base;
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(BlockState s, Direction d, @NotNull RandomSource r) {
        return base.getQuads(s, d, r);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return base.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return base.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return base.usesBlockLight();
    }

    /**
     * Keeps gem items on the normal baked-model render path. Per-stack specialization now
     * happens by swapping baked models through overrides, so a custom renderer is no longer
     * needed.
     */
    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon() {
        return base.getParticleIcon();
    }

    /**
     * Delegates the original GUI/hand/ground transforms from the wrapped model. Without this
     * override, the interface default would silently return
     * {@link ItemTransforms#NO_TRANSFORMS}.
     */
    @Override
    public @NotNull ItemTransforms getTransforms() {
        return base.getTransforms();
    }

    /**
     * Returns the custom override handler that inspects stack NBT and swaps to a gem-specific
     * baked model when appropriate, while leaving all other base-model behavior alone.
     */
    @Override
    public @NotNull ItemOverrides getOverrides() {
        return overrides;
    }
}
