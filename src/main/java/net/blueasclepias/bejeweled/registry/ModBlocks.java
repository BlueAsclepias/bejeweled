package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.oretype.OreTypes;
import net.blueasclepias.bejeweled.record.OreBase;
import net.blueasclepias.bejeweled.record.OreType;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Register Mod Blocks
 */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final Map<OreType, Map<OreBase, RegistryObject<Block>>> ORE_BLOCKS = new HashMap<>();
    public static final List<RegistryObject<Block>> STORAGE_BLOCKS = new ArrayList<>();
    public static final List<RegistryObject<Block>> CORAL_POLYP_BLOCKS = new ArrayList<>();

    // Shared properties for gem/rough gem blocks
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

    // ===== Static Initializer =====
    static {
        registerGemOreBlockType(OreTypes.BERYL);
        registerGemOreBlockType(OreTypes.RED_CORUNDUM);
        registerGemOreBlockType(OreTypes.BLUE_CORUNDUM);
        registerGemOreBlockType(OreTypes.GARNET);
        registerGemOreBlockType(OreTypes.TOPAZ);
        registerGemOreBlockType(OreTypes.TURQUOISE);
        registerGemOreBlockType(OreTypes.NEPHRITE);
        registerGemOreBlockType(OreTypes.JADEITE);
        registerGemOreBlockType(OreTypes.OPAL);
        registerGemOreBlockType(OreTypes.OLIVINE);

        registerCoralPolypType(Blocks.FIRE_CORAL_BLOCK);
        registerCoralPolypType(Blocks.BRAIN_CORAL_BLOCK);
        registerCoralPolypType(Blocks.BUBBLE_CORAL_BLOCK);
        registerCoralPolypType(Blocks.HORN_CORAL_BLOCK);
        registerCoralPolypType(Blocks.TUBE_CORAL_BLOCK);

        registerBlockOf("rough_aquamarine", roughGemBlock(MapColor.COLOR_CYAN));
        registerBlockOf("rough_ruby", roughGemBlock(MapColor.COLOR_RED));
        registerBlockOf("rough_sapphire", roughGemBlock(MapColor.COLOR_BLUE));
        registerBlockOf("rough_garnet", roughGemBlock(MapColor.COLOR_RED));
        registerBlockOf("rough_emerald", roughGemBlock(MapColor.COLOR_GREEN));
        registerBlockOf("rough_topaz", roughGemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("rough_turquoise", roughGemBlock(MapColor.COLOR_CYAN));
        registerBlockOf("rough_jade", roughGemBlock(MapColor.COLOR_LIGHT_GREEN));
        registerBlockOf("rough_opal", roughGemBlock(MapColor.COLOR_LIGHT_GRAY));
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
        // TODO: BLOCK OF CUT CORAL TYPES THROUGH TINTING
        //registerBlockOf("cut_coral", cutGemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("cut_peridot", cutGemBlock(MapColor.COLOR_LIGHT_GREEN));
    }

    private static void registerCoralPolypType(Block block){
        String name = ForgeRegistries.BLOCKS.getKey(block).getPath() + "_polyp";
        CORAL_POLYP_BLOCKS.add(
          BLOCKS.register(name,
                  () -> new CoralPolypBlock(
                          block,
                          BlockBehaviour.Properties.of()
                                  .mapColor(block.defaultMapColor())
                                  .strength(0.3f)
                                  .sound(SoundType.CORAL_BLOCK)
                                  .noOcclusion()
                  )
          )
        );
    }

    private static void registerGemOreBlockType(OreType type){
        Map<OreBase, RegistryObject<Block>> variants = new HashMap<>();
        type.features().forEach(
                feature -> variants.put(
                        feature.base(),
                        BLOCKS.register(
                                feature.base().name() + "_" + type.name() + "_ore",
                                () -> new DropExperienceBlock(
                                        BlockBehaviour.Properties.of()
                                                .strength(feature.base().hardness(), feature.base().resistance())
                                                .sound(feature.base().soundType())
                                                .requiresCorrectToolForDrops(),
                                        UniformInt.of(2, 4)
                                )
                        )
                )
        );
        ORE_BLOCKS.put(type, variants);
    }

    private static void registerBlockOf(String name,  BlockBehaviour.Properties properties) {
        STORAGE_BLOCKS.add(BLOCKS.register(
                "block_of_" + name,
                () -> new Block(properties)
        ));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
