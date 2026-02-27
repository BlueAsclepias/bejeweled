package net.blueasclepias.bejeweled.event;

import net.blueasclepias.bejeweled.material.definition.gem.GemDefinition;
import net.blueasclepias.bejeweled.material.definition.jewel.JewelMaterial;
import net.blueasclepias.bejeweled.material.registry.ModGemRegistry;
import net.blueasclepias.bejeweled.registry.ModItems;
import net.blueasclepias.bejeweled.registry.ModMenus;
import net.blueasclepias.bejeweled.screen.GemCuttingTableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

@Mod.EventBusSubscriber(
        modid = MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(
                    ModMenus.GEM_CUTTING_TABLE.get(),
                    GemCuttingTableScreen::new
            );
        });

        event.enqueueWork(() -> {
            registerMaterialPredicate(ModItems.SOCKETED_RING.get());
            registerMaterialPredicate(ModItems.SOCKETED_AMULET.get());
            registerMaterialPredicate(ModItems.SOCKETED_BRACELET.get());
            registerMaterialPredicate(ModItems.SOCKETED_CIRCLET.get());
        });

        event.enqueueWork(() -> {
            Minecraft.getInstance().getItemColors().register(
                    ClientSetup::tintSocketLayer,
                    ModItems.SOCKETED_RING.get(),
                    ModItems.SOCKETED_AMULET.get(),
                    ModItems.SOCKETED_BRACELET.get(),
                    ModItems.SOCKETED_CIRCLET.get()
            );
        });
    }

    private static void registerMaterialPredicate(Item item) {
        ItemProperties.register(
                item,
                fromNamespaceAndPath(MOD_ID, "material"),
                (stack, level, entity, seed) -> getMaterialValue(stack)
        );
    }

    private static float getMaterialValue(ItemStack stack) {
        CompoundTag tag = stack.getTagElement(MOD_ID);
        if (tag == null) return 0.0F;
        try {
            JewelMaterial material = JewelMaterial.valueOf(tag.getString("material"));
            return material.ordinal();
        } catch (Exception e) {
            return 0.0F;
        }
    }

    private static int tintSocketLayer(ItemStack stack, int tintIndex) {

        // Only tint overlay
        if (tintIndex != 1) {
            return 0xFFFFFFFF;
        }

        CompoundTag tag = stack.getTagElement(MOD_ID);
        if (tag == null) {
            return 0xFFFFFFFF;
        }

        String gemId = tag.getString("gem");
        GemDefinition def = ModGemRegistry.get(ResourceLocation.parse(gemId));
        if (def == null) {
            return 0xFFFFFFFF;
        }

        int rgb = def.color(); // 0xRRGGBB
        return 0xFF000000 | rgb;  // add alpha
    }
}
