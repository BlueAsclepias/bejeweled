package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.blockentity.GemCuttingTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<BlockEntityType<GemCuttingTableBlockEntity>>
            GEM_CUTTING_TABLE =
            BLOCK_ENTITIES.register(
                    "gem_cutting_table",
                    () -> BlockEntityType.Builder.of(
                            GemCuttingTableBlockEntity::new,
                            ModBlocks.GEM_CUTTING_TABLE.get()
                    ).build(null)
            );

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}

