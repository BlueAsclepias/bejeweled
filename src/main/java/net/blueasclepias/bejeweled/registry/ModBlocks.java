package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.block.GemCuttingTableBlock;
import net.blueasclepias.bejeweled.content.ore.OreDefinitions;
import net.blueasclepias.bejeweled.record.ore.OreDefinition;
import net.blueasclepias.bejeweled.record.ore.OreVariant;
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

    public static final Map<OreDefinition, Map<OreVariant, RegistryObject<Block>>> ORE_BLOCKS = new HashMap<>();
    public static final List<RegistryObject<Block>> STORAGE_BLOCKS = new ArrayList<>();
    public static final List<RegistryObject<Block>> CORAL_POLYP_BLOCKS = new ArrayList<>();

    // ===== Workstation =====
    public static final RegistryObject<Block> GEM_CUTTING_TABLE =
            BLOCKS.register(
                    "gem_cutting_table",
                    () -> new GemCuttingTableBlock(
                            BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)
                    )
            );


    // === Shared properties for gem/rough gem blocks ===
    private static BlockBehaviour.Properties gemBlock(MapColor color) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(.5f, 1f)
                .sound(SoundType.AMETHYST);
    }

    private static BlockBehaviour.Properties roughGemBlock(MapColor color) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(.5f, 1f)
                .sound(SoundType.STONE);
    }

    // ===== Registration Helpers =====
    // === Coral Polyp Blocks ===
    private static void registerCoralPolyp(Block block){
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

    // === Gem Ore Blocks ===
    private static void registerGemOreBlock(OreDefinition def){
        Map<OreVariant, RegistryObject<Block>> variants = new HashMap<>();
        def.features().forEach(
                feature -> variants.put(
                        feature.variant(),
                        BLOCKS.register(
                                feature.variant().name() + "_" + def.name() + "_ore",
                                () -> new DropExperienceBlock(
                                        BlockBehaviour.Properties.of()
                                                .strength(feature.variant().hardness(), feature.variant().resistance())
                                                .sound(feature.variant().soundType())
                                                .requiresCorrectToolForDrops(),
                                        UniformInt.of(2, 4)
                                )
                        )
                )
        );
        ORE_BLOCKS.put(def, variants);
    }

    // === Storage Blocks ===
    private static void registerBlockOf(String name,  BlockBehaviour.Properties properties) {
        STORAGE_BLOCKS.add(BLOCKS.register(
                "block_of_" + name,
                () -> new Block(properties)
        ));
    }

    // ===== Static Initializer =====
    static {
        registerGemOreBlock(OreDefinitions.BERYL);
        registerGemOreBlock(OreDefinitions.RED_CORUNDUM);
        registerGemOreBlock(OreDefinitions.BLUE_CORUNDUM);
        registerGemOreBlock(OreDefinitions.GARNET);
        registerGemOreBlock(OreDefinitions.TOPAZ);
        registerGemOreBlock(OreDefinitions.TURQUOISE);
        registerGemOreBlock(OreDefinitions.NEPHRITE);
        registerGemOreBlock(OreDefinitions.JADEITE);
        registerGemOreBlock(OreDefinitions.OPAL);
        registerGemOreBlock(OreDefinitions.OLIVINE);

        registerCoralPolyp(Blocks.FIRE_CORAL_BLOCK);
        registerCoralPolyp(Blocks.BRAIN_CORAL_BLOCK);
        registerCoralPolyp(Blocks.BUBBLE_CORAL_BLOCK);
        registerCoralPolyp(Blocks.HORN_CORAL_BLOCK);
        registerCoralPolyp(Blocks.TUBE_CORAL_BLOCK);

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
        registerBlockOf("unpolished_pearl", gemBlock(MapColor.COLOR_LIGHT_GRAY));

        registerBlockOf("cut_aquamarine", gemBlock(MapColor.COLOR_CYAN));
        registerBlockOf("cut_ruby", gemBlock(MapColor.COLOR_RED));
        registerBlockOf("cut_sapphire", gemBlock(MapColor.COLOR_BLUE));
        registerBlockOf("cut_garnet", gemBlock(MapColor.COLOR_RED));
        registerBlockOf("cut_amethyst", gemBlock(MapColor.COLOR_PURPLE));
        registerBlockOf("cut_topaz", gemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("cut_turquoise", gemBlock(MapColor.COLOR_CYAN));
        registerBlockOf("cut_jade", gemBlock(MapColor.COLOR_LIGHT_GREEN));
        registerBlockOf("cut_opal", gemBlock(MapColor.COLOR_LIGHT_GRAY));
        registerBlockOf("cut_peridot", gemBlock(MapColor.COLOR_LIGHT_GREEN));

        registerBlockOf("polished_tube_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("polished_fire_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("polished_horn_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("polished_brain_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("polished_bubble_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
        registerBlockOf("polished_pearl", gemBlock(MapColor.COLOR_LIGHT_GRAY));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
