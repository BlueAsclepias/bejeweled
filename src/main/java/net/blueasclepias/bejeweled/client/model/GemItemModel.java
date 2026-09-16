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
 * Wraps the baked "gem_item" model so it can hand out a gem-specific,
 * lazily-baked model (see {@link GemModelOverrides}) for gem stacks that
 * carry a dedicated texture, while behaving exactly like the base model in
 * every other respect. Unlike the old custom-renderer approach, this model
 * is rendered through the standard, cheap quad pipeline (same as any
 * vanilla item), giving proper extrusion and vanilla-identical transforms
 * for free.
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

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon() {
        return base.getParticleIcon();
    }

    @Override
    public @NotNull ItemTransforms getTransforms() {
        return base.getTransforms();
    }

    @Override
    public @NotNull ItemOverrides getOverrides() {
        return overrides;
    }
}
