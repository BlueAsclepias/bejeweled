package net.blueasclepias.bejeweled.common.registry;

import net.blueasclepias.bejeweled.common.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.common.block.GemCuttingTableBlock;
import net.blueasclepias.bejeweled.common.data.coral.registry.CoralPolypRegistry;
import net.blueasclepias.bejeweled.common.data.ore.defaults.OreFeatures;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreBlockVariant;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreGenerationFeature;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreFeatureRegistry;
import net.blueasclepias.bejeweled.common.data.storage.registry.StorageBlockRegistry;
import net.minecraft.resources.ResourceLocation;
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

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Register Mod Blocks
 */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    // ===== GEM CUTTING TABLE =====
    public static final RegistryObject<Block> GEM_CUTTING_TABLE =
            BLOCKS.register(
                    "gem_cutting_table",
                    () -> new GemCuttingTableBlock(
                            BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)
                    )
            );

    // ===== ORE BLOCKS ====
    public static final RegistryObject<Block> STONE_BERYL_ORE =
            registerGemOreBlock(OreFeatures.STONE_BERYL);
    public static final RegistryObject<Block> GRANITE_BERYL_ORE =
            registerGemOreBlock(OreFeatures.GRANITE_BERYL);

    public static final RegistryObject<Block> STONE_RED_CORUNDUM_ORE =
            registerGemOreBlock(OreFeatures.STONE_RED_CORUNDUM);
    public static final RegistryObject<Block> DEEPSLATE_RED_CORUNDUM_ORE =
            registerGemOreBlock(OreFeatures.DEEPSLATE_RED_CORUNDUM);

    public static final RegistryObject<Block> DEEPSLATE_BLUE_CORUNDUM_ORE =
            registerGemOreBlock(OreFeatures.DEEPSLATE_BLUE_CORUNDUM);
    public static final RegistryObject<Block> BASALT_BLUE_CORUNDUM_ORE =
            registerGemOreBlock(OreFeatures.BASALT_BLUE_CORUNDUM);

    public static final RegistryObject<Block> STONE_GARNET_ORE =
            registerGemOreBlock(OreFeatures.STONE_GARNET);
    public static final RegistryObject<Block> DEEPSLATE_GARNET_ORE =
            registerGemOreBlock(OreFeatures.DEEPSLATE_GARNET);

    public static final RegistryObject<Block> STONE_TOPAZ_ORE =
            registerGemOreBlock(OreFeatures.STONE_TOPAZ);
    public static final RegistryObject<Block> GRANITE_TOPAZ_ORE =
            registerGemOreBlock(OreFeatures.GRANITE_TOPAZ);
    public static final RegistryObject<Block> DEEPSLATE_TOPAZ_ORE =
            registerGemOreBlock(OreFeatures.DEEPSLATE_TOPAZ);

    public static final RegistryObject<Block> STONE_TURQUOISE_ORE =
            registerGemOreBlock(OreFeatures.STONE_TURQUOISE);

    public static final RegistryObject<Block> STONE_NEPHRITE_ORE =
            registerGemOreBlock(OreFeatures.STONE_NEPHRITE);
    public static final RegistryObject<Block> DEEPSLATE_NEPHRITE_ORE =
            registerGemOreBlock(OreFeatures.DEEPSLATE_NEPHRITE);

    public static final RegistryObject<Block> STONE_JADEITE_ORE =
            registerGemOreBlock(OreFeatures.STONE_JADEITE);
    public static final RegistryObject<Block> DEEPSLATE_JADEITE_ORE =
            registerGemOreBlock(OreFeatures.DEEPSLATE_JADEITE);


    public static final RegistryObject<Block> TERRACOTTA_OPAL_ORE =
            registerGemOreBlock(OreFeatures.TERRACOTTA_OPAL);

    public static final RegistryObject<Block> DEEPSLATE_OLIVINE_ORE =
            registerGemOreBlock(OreFeatures.DEEPSLATE_OLIVINE);

    // ===== STORAGE BLOCKS =====
    public static final RegistryObject<Block> AQUAMARINE_BLOCK =
            registerStorageBlock("aquamarine_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "aquamarine"), getStorageBlockProperties(MapColor.COLOR_CYAN));
    public static final RegistryObject<Block> RUBY_BLOCK =
            registerStorageBlock("ruby_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "ruby"), getStorageBlockProperties(MapColor.COLOR_RED));
    public static final RegistryObject<Block> SAPPHIRE_BLOCK =
            registerStorageBlock("sapphire_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "sapphire"), getStorageBlockProperties(MapColor.COLOR_BLUE));
    public static final RegistryObject<Block> GARNET_BLOCK =
            registerStorageBlock("garnet_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "garnet"), getStorageBlockProperties(MapColor.COLOR_RED));
    public static final RegistryObject<Block> TOPAZ_BLOCK =
            registerStorageBlock("topaz_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "topaz"), getStorageBlockProperties(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> TURQUOISE_BLOCK =
            registerStorageBlock("turquoise_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "turquoise"), getStorageBlockProperties(MapColor.COLOR_CYAN));
    public static final RegistryObject<Block> JADE_BLOCK =
            registerStorageBlock("jade_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "jade"), getStorageBlockProperties(MapColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> OPAL_BLOCK =
            registerStorageBlock("opal_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "opal"), getStorageBlockProperties(MapColor.COLOR_LIGHT_GRAY));
    public static final RegistryObject<Block> PERIDOT_BLOCK =
            registerStorageBlock("peridot_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "peridot"), getStorageBlockProperties(MapColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> PEARL_BLOCK =
            registerStorageBlock("pearl_block", ResourceLocation.fromNamespaceAndPath(MOD_ID, "pearl"), getStorageBlockProperties(MapColor.COLOR_LIGHT_GRAY));

    // ===== CORAL POLYP BLOCKS =====
    public static final RegistryObject<Block> FIRE_CORAL_BLOCK_POLYP =
            registerCoralPolyp("fire_coral_block_polyp", ResourceLocation.fromNamespaceAndPath(MOD_ID, "red_coral"), Blocks.FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> BRAIN_CORAL_BLOCK_POLYP =
            registerCoralPolyp("brain_coral_block_polyp", ResourceLocation.fromNamespaceAndPath(MOD_ID, "pink_coral"), Blocks.BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> BUBBLE_CORAL_BLOCK_POLYP =
            registerCoralPolyp("bubble_coral_block_polyp", ResourceLocation.fromNamespaceAndPath(MOD_ID, "purple_coral"), Blocks.BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> HORN_CORAL_BLOCK_POLYP =
            registerCoralPolyp("horn_coral_block_polyp", ResourceLocation.fromNamespaceAndPath(MOD_ID, "yellow_coral"), Blocks.HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> TUBE_CORAL_BLOCK_POLYP =
            registerCoralPolyp("tube_coral_block_polyp", ResourceLocation.fromNamespaceAndPath(MOD_ID, "blue_coral"), Blocks.TUBE_CORAL_BLOCK);

    private static BlockBehaviour.Properties getStorageBlockProperties(MapColor color) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(.5f, 1f)
                .sound(SoundType.AMETHYST);
    }

    // ===== Registration Helpers =====
    // === Storage Blocks ===
    private static RegistryObject<Block> registerStorageBlock(String path, ResourceLocation ingredient, BlockBehaviour.Properties properties) {
        return BLOCKS.register(path,
                () -> {
                    Block block = new Block(properties);
                    StorageBlockRegistry.bind(block, ingredient, ResourceLocation.fromNamespaceAndPath(MOD_ID, path));
                    return block;
                });
    }

    // === Coral Polyp Blocks ===
    private static RegistryObject<Block> registerCoralPolyp(String path, ResourceLocation drop, Block coralBlock){
        return BLOCKS.register(path,
                () -> {
                    CoralPolypBlock polypBlock = new CoralPolypBlock(
                            coralBlock,
                            drop,
                            BlockBehaviour.Properties.of()
                                    .mapColor(coralBlock.defaultMapColor())
                                    .strength(0.3f)
                                    .sound(SoundType.CORAL_BLOCK)
                                    .noOcclusion()
                    );
                    CoralPolypRegistry.bind(polypBlock, coralBlock, ResourceLocation.fromNamespaceAndPath(MOD_ID, path));
                    return polypBlock;
                }
        );
    }

    // === Gem Ore Blocks ===
    private static RegistryObject<Block> registerGemOreBlock(OreGenerationFeature feat) {
        OreBlockVariant var = feat.variant();
        return BLOCKS.register(feat.id(),
                () -> {
                    Block block = new DropExperienceBlock(
                            BlockBehaviour.Properties.of()
                                    .strength(var.hardness(), var.resistance())
                                    .sound(var.soundType())
                                    .requiresCorrectToolForDrops(),
                            UniformInt.of(2,4)
                    );
                    OreFeatureRegistry.bind(block, ResourceLocation.fromNamespaceAndPath(MOD_ID, feat.id()));
                    return block;
                }
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
