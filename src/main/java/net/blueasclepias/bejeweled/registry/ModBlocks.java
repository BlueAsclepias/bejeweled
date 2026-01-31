package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.Bejeweled;
import net.blueasclepias.bejeweled.enums.OreBase;
import net.blueasclepias.bejeweled.oretypes.OreTypes;
import net.blueasclepias.bejeweled.record.OreType;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Bejeweled.MOD_ID);

    public static final Map<OreType, Map<OreBase, RegistryObject<Block>>> ORE_BLOCKS = new HashMap<>();
    public static final List<RegistryObject<Block>> STORAGE_BLOCKS = new ArrayList<>();

    // Shared properties for gem blocks
    private static BlockBehaviour.Properties cutGemBlock(MapColor color) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(5.0f, 6.0f)
                .sound(SoundType.AMETHYST);
    }

    private static BlockBehaviour.Properties roughGemBlock(MapColor color) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(5.0f, 6.0f)
                .sound(SoundType.STONE);
    }

    // ===== ORE BLOCKS =====
    static {
        registerOreBlockType(OreTypes.BERYL);
        registerOreBlockType(OreTypes.RED_CORUNDUM);
        registerOreBlockType(OreTypes.BLUE_CORUNDUM);
        registerOreBlockType(OreTypes.GARNET);
        registerOreBlockType(OreTypes.TOPAZ);
        registerOreBlockType(OreTypes.TURQUOISE);
        registerOreBlockType(OreTypes.NEPHRITE);
        registerOreBlockType(OreTypes.JADEITE);
        registerOreBlockType(OreTypes.OPAL);
        registerOreBlockType(OreTypes.OLIVINE);

        registerBlockOf("rough_aquamarine", roughGemBlock(MapColor.COLOR_CYAN));
        registerBlockOf("rough_ruby", roughGemBlock(MapColor.COLOR_RED));
        registerBlockOf("rough_sapphire", roughGemBlock(MapColor.COLOR_BLUE));
        registerBlockOf("rough_garnet", roughGemBlock(MapColor.COLOR_RED));
        //registerBlockOf("rough_amethyst", roughGemBlock(MapColor.COLOR_PURPLE)); // vanilla amethyst is rough amethyst
        registerBlockOf("rough_emerald", roughGemBlock(MapColor.COLOR_GREEN));
        registerBlockOf("rough_topaz", roughGemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("rough_turquoise", roughGemBlock(MapColor.COLOR_CYAN));
        registerBlockOf("rough_jade", roughGemBlock(MapColor.COLOR_LIGHT_GREEN));
        registerBlockOf("rough_opal", roughGemBlock(MapColor.COLOR_LIGHT_GRAY));
        registerBlockOf("rough_coral_polyp", roughGemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("rough_peridot", roughGemBlock(MapColor.COLOR_LIGHT_GREEN));
        registerBlockOf("rough_diamond", roughGemBlock(MapColor.COLOR_CYAN));

        registerBlockOf("cut_aquamarine", cutGemBlock(MapColor.COLOR_CYAN));
        registerBlockOf("cut_ruby", cutGemBlock(MapColor.COLOR_RED));
        registerBlockOf("cut_sapphire", cutGemBlock(MapColor.COLOR_BLUE));
        registerBlockOf("cut_garnet", cutGemBlock(MapColor.COLOR_RED));
        registerBlockOf("cut_amethyst", cutGemBlock(MapColor.COLOR_PURPLE));
        registerBlockOf("cut_topaz", cutGemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("cut_turquoise", cutGemBlock(MapColor.COLOR_CYAN));
        registerBlockOf("cut_jade", cutGemBlock(MapColor.COLOR_LIGHT_GREEN));
        registerBlockOf("cut_opal", cutGemBlock(MapColor.COLOR_LIGHT_GRAY));
        registerBlockOf("pearl", cutGemBlock(MapColor.COLOR_LIGHT_GRAY));
        registerBlockOf("cut_coral", cutGemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("cut_peridot", cutGemBlock(MapColor.COLOR_LIGHT_GREEN));
    }

    private static void registerBlockOf(String name,  BlockBehaviour.Properties properties) {
        STORAGE_BLOCKS.add(BLOCKS.register(
                "block_of_" + name,
                () -> new Block(properties)
        ));
    }

    private static RegistryObject<Block> registerOreBlock(String name, OreBase base) {
        return BLOCKS.register(
                base.name().toLowerCase() + "_" + name + "_ore",
                () -> new DropExperienceBlock(
                        BlockBehaviour.Properties.of()
                                .strength(base.hardness(), base.resistance())
                                .sound(base.sound())
                                .requiresCorrectToolForDrops(),
                        UniformInt.of(1, 3))
        );
    }

    private static void registerOreBlockType(OreType type) {
        Map<OreBase, RegistryObject<Block>> variants = new HashMap<>();

        for (OreBase base : type.bases()) {
            variants.put(
                    base,
                    registerOreBlock(type.name(), base)
            );
        }

        ORE_BLOCKS.put(type, variants);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
