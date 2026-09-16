package net.blueasclepias.bejeweled.client.event;

import net.blueasclepias.bejeweled.client.model.GemItemModel;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Activates dynamic gem item rendering during Forge's model bake result pass. Once the normal
 * {@code bejeweled:gem_item#inventory} model has been baked, this handler replaces that single
 * entry with a {@link GemItemModel} wrapper that preserves the base model while installing the
 * custom item override logic. The swap happens once per model bake/resource reload, so every
 * rendered gem stack can participate in the gem-specific model selection path afterward.
 */
@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ModifyingBakingResultEventHandler {
    /**
     * Replaces the baked inventory model for {@code gem_item} with a wrapper that exposes the
     * dynamic gem override logic while keeping the original baked model data intact.
     */
    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult event) {

        Map<ResourceLocation, BakedModel> models = event.getModels();

        ModelResourceLocation id =
                new ModelResourceLocation(
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "gem_item"),
                        "inventory"
                );

        BakedModel base = models.get(id);

        if (base != null) models.put(id, new GemItemModel(base));
    }
}
