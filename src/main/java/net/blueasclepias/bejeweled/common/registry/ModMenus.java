package net.blueasclepias.bejeweled.common.registry;

import net.blueasclepias.bejeweled.common.container.GemCuttingTableMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);

    public static final RegistryObject<MenuType<GemCuttingTableMenu>> GEM_CUTTING_TABLE =
            MENUS.register(
                    "gem_cutting_table",
                    () -> IForgeMenuType.create(GemCuttingTableMenu::new)
            );

    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}

