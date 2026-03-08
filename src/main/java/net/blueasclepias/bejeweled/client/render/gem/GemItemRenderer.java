package net.blueasclepias.bejeweled.client.render.gem;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.blueasclepias.bejeweled.client.texture.GemSpriteCache;
import net.blueasclepias.bejeweled.common.data.gem.state.GemState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class GemItemRenderer extends BlockEntityWithoutLevelRenderer {

    public GemItemRenderer() {
        super(
                Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels()
        );
    }

    @Override
    public void renderByItem(
            @NotNull ItemStack stack,
            @NotNull ItemDisplayContext context,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource buffer,
            int light,
            int overlay
    ) {

        Optional<String> gemPath = GemState.getGem(stack);

        if (gemPath.isEmpty()) {
            renderSprite(stack,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "item/gem_item"),
                    poseStack,
                    buffer,
                    light,
                    overlay);
            return;
        }

        ResourceLocation gemId = ResourceLocation.parse(gemPath.get());
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(
                MOD_ID,
                "item/gem/processed/" + gemId.getPath()
        );

        renderSprite(stack,
                texture,
                poseStack,
                buffer,
                light,
                overlay);
    }

    private void renderSprite(
            ItemStack stack,
            ResourceLocation texture,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int light,
            int overlay
    ) {

        TextureAtlasSprite sprite = GemSpriteCache.get(texture);

        VertexConsumer consumer =
                ItemRenderer.getFoilBufferDirect(
                        buffer,
                        RenderType.itemEntityTranslucentCull(TextureAtlas.LOCATION_BLOCKS),
                        true,
                        stack.hasFoil()
                );

        Matrix4f matrix = poseStack.last().pose();
        Matrix3f normal = poseStack.last().normal();

        float minU = sprite.getU0();
        float maxU = sprite.getU1();
        float minV = sprite.getV0();
        float maxV = sprite.getV1();

        float size = 0.5f;

        consumer.vertex(matrix, 0, 0, 0)
                .color(255,255,255,255)
                .uv(minU, maxV)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normal,0,0,1)
                .endVertex();

        consumer.vertex(matrix, 1, 0, 0)
                .color(255,255,255,255)
                .uv(maxU, maxV)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normal,0,0,1)
                .endVertex();

        consumer.vertex(matrix, 1, 1, 0)
                .color(255,255,255,255)
                .uv(maxU, minV)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normal,0,0,1)
                .endVertex();

        consumer.vertex(matrix, 0, 1, 0)
                .color(255,255,255,255)
                .uv(minU, minV)
                .overlayCoords(overlay)
                .uv2(light)
                .normal(normal,0,0,1)
                .endVertex();
    }
}