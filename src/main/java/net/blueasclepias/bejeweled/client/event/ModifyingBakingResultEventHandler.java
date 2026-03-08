package net.blueasclepias.bejeweled.client.event;

import net.blueasclepias.bejeweled.client.render.CustomRendererModel;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ModifyingBakingResultEventHandler {
    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult event) {

        Map<ResourceLocation, BakedModel> models = event.getModels();

        ModelResourceLocation id =
                new ModelResourceLocation(
                        ResourceLocation.fromNamespaceAndPath(MOD_ID, "gem_item"),
                        "inventory"
                );

        BakedModel base = models.get(id);

        if (base != null) models.put(id, new CustomRendererModel(base));
    }
}
