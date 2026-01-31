package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.Bejeweled;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Bejeweled.MOD_ID);

    public static final RegistryObject<CreativeModeTab> JEWELRY_TAB =
            TABS.register("jewelry", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.bejeweled.jewelry"))
                    .icon(() -> new ItemStack(ModItems.CUT_GARNET.get()))
                    .displayItems((params, output) -> {
                        ModItems.ITEMS.getEntries()
                                .forEach(item -> output.accept(item.get()));
                    })
                    .build()
            );

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
