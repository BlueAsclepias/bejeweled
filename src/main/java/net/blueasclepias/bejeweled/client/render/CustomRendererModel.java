package net.blueasclepias.bejeweled.client.render;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomRendererModel implements BakedModel {

    private final BakedModel base;

    public CustomRendererModel(BakedModel base) {
        this.base = base;
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(BlockState s, Direction d, @NotNull RandomSource r){
        return base.getQuads(s,d,r);
    }

    @Override
    public boolean useAmbientOcclusion(){
        return base.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d(){
        return base.isGui3d();
    }

    @Override
    public boolean usesBlockLight(){
        return base.usesBlockLight();
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon(){
        return base.getParticleIcon();
    }

    @Override
    public @NotNull ItemOverrides getOverrides(){
        return base.getOverrides();
    }

}