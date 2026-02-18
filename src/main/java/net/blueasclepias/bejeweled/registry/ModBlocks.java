package net.blueasclepias.bejeweled.registry;

import net.blueasclepias.bejeweled.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.block.GemCuttingTableBlock;
import net.blueasclepias.bejeweled.material.definition.ore.OreFeature;
import net.blueasclepias.bejeweled.material.definition.ore.OreVariant;
import net.blueasclepias.bejeweled.material.instance.ore.OreFeatures;
import net.blueasclepias.bejeweled.material.registry.ModOreRegistry;
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

import java.util.Objects;
import java.util.stream.Stream;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

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
    // === RAW ===
    public static final RegistryObject<Block> BLOCK_OF_RAW_AQUAMARINE =
            registerStorageBlock("block_of_raw_aquamarine", rawGemBlock(MapColor.COLOR_CYAN));
    public static final RegistryObject<Block> BLOCK_OF_RAW_RUBY =
            registerStorageBlock("block_of_raw_ruby", rawGemBlock(MapColor.COLOR_RED));
    public static final RegistryObject<Block> BLOCK_OF_RAW_SAPPHIRE =
            registerStorageBlock("block_of_raw_sapphire", rawGemBlock(MapColor.COLOR_BLUE));
    public static final RegistryObject<Block> BLOCK_OF_RAW_GARNET =
            registerStorageBlock("block_of_raw_garnet", rawGemBlock(MapColor.COLOR_RED));
    public static final RegistryObject<Block> BLOCK_OF_RAW_EMERALD =
            registerStorageBlock("block_of_raw_emerald", rawGemBlock(MapColor.COLOR_GREEN));
    public static final RegistryObject<Block> BLOCK_OF_RAW_TOPAZ =
            registerStorageBlock("block_of_raw_topaz", rawGemBlock(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> BLOCK_OF_RAW_TURQUOISE =
            registerStorageBlock("block_of_raw_turquoise", rawGemBlock(MapColor.COLOR_CYAN));
    public static final RegistryObject<Block> BLOCK_OF_RAW_JADE =
            registerStorageBlock("block_of_raw_jade", rawGemBlock(MapColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> BLOCK_OF_RAW_OPAL =
            registerStorageBlock("block_of_raw_opal", rawGemBlock(MapColor.COLOR_LIGHT_GRAY));
    public static final RegistryObject<Block> BLOCK_OF_RAW_PERIDOT =
            registerStorageBlock("block_of_raw_peridot", rawGemBlock(MapColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> BLOCK_OF_RAW_DIAMOND =
            registerStorageBlock("block_of_raw_diamond", rawGemBlock(MapColor.COLOR_CYAN));
    public static final RegistryObject<Block> BLOCK_OF_RAW_PEARL =
            registerStorageBlock("block_of_raw_pearl", rawGemBlock(MapColor.COLOR_LIGHT_GRAY));

    // === PROCESSED ===
    // == GEMSTONES ==
    public static final RegistryObject<Block> BLOCK_OF_AQUAMARINE =
            registerStorageBlock("block_of_aquamarine", gemBlock(MapColor.COLOR_CYAN));
    public static final RegistryObject<Block> BLOCK_OF_RUBY =
            registerStorageBlock("block_of_ruby", gemBlock(MapColor.COLOR_RED));
    public static final RegistryObject<Block> BLOCK_OF_SAPPHIRE =
            registerStorageBlock("block_of_sapphire", gemBlock(MapColor.COLOR_BLUE));
    public static final RegistryObject<Block> BLOCK_OF_GARNET =
            registerStorageBlock("block_of_garnet", gemBlock(MapColor.COLOR_RED));
    public static final RegistryObject<Block> BLOCK_OF_AMETHYST =
            registerStorageBlock("block_of_amethyst", gemBlock(MapColor.COLOR_PURPLE));
    public static final RegistryObject<Block> BLOCK_OF_TOPAZ =
            registerStorageBlock("block_of_topaz", gemBlock(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> BLOCK_OF_TURQUOISE =
            registerStorageBlock("block_of_turquoise", gemBlock(MapColor.COLOR_CYAN));
    public static final RegistryObject<Block> BLOCK_OF_JADE =
            registerStorageBlock("block_of_jade", gemBlock(MapColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> BLOCK_OF_OPAL =
            registerStorageBlock("block_of_opal", gemBlock(MapColor.COLOR_LIGHT_GRAY));
    public static final RegistryObject<Block> BLOCK_OF_PERIDOT =
            registerStorageBlock("block_of_peridot", gemBlock(MapColor.COLOR_LIGHT_GREEN));
    // == BEADS ==
    public static final RegistryObject<Block> BLOCK_OF_TUBE_CORAL_BEAD =
            registerStorageBlock("block_of_tube_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> BLOCK_OF_FIRE_CORAL_BEAD =
            registerStorageBlock("block_of_fire_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> BLOCK_OF_HORN_CORAL_BEAD =
            registerStorageBlock("block_of_horn_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> BLOCK_OF_BRAIN_CORAL_BEAD =
            registerStorageBlock("block_of_brain_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> BLOCK_OF_BUBBLE_CORAL_BEAD =
            registerStorageBlock("block_of_bubble_coral_bead", gemBlock(MapColor.COLOR_ORANGE));
    public static final RegistryObject<Block> BLOCK_OF_PEARL =
            registerStorageBlock("block_of_pearl", gemBlock(MapColor.COLOR_LIGHT_GRAY));

    // ===== CORAL POLYP BLOCKS =====
    public static final RegistryObject<Block> FIRE_CORAL_BLOCK_POLYP =
            registerCoralPolyp("fire_coral_block_polyp", Blocks.FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> BRAIN_CORAL_BLOCK_POLYP =
            registerCoralPolyp("brain_coral_block_polyp", Blocks.BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> BUBBLE_CORAL_BLOCK_POLYP =
            registerCoralPolyp("bubble_coral_block_polyp", Blocks.BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> HORN_CORAL_BLOCK_POLYP =
            registerCoralPolyp("horn_coral_block_polyp", Blocks.HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> TUBE_CORAL_BLOCK_POLYP =
            registerCoralPolyp("tube_coral_block_polyp", Blocks.TUBE_CORAL_BLOCK);

    // === Shared properties for gem/raw gem blocks ===
    private static BlockBehaviour.Properties gemBlock(MapColor color) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(.5f, 1f)
                .sound(SoundType.AMETHYST);
    }

    private static BlockBehaviour.Properties rawGemBlock(MapColor color) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(.5f, 1f)
                .sound(SoundType.STONE);
    }

    // ===== Registration Helpers =====
    // === Storage Blocks ===
    private static RegistryObject<Block> registerStorageBlock(String path, BlockBehaviour.Properties properties) {
        return BLOCKS.register(path, () -> new Block(properties));
    }

    // === Coral Polyp Blocks ===
    private static RegistryObject<Block> registerCoralPolyp(String path, Block coralBlock){
        return BLOCKS.register(path, () -> new CoralPolypBlock(
                coralBlock,
                BlockBehaviour.Properties.of()
                        .mapColor(coralBlock.defaultMapColor())
                        .strength(0.3f)
                        .sound(SoundType.CORAL_BLOCK)
                        .noOcclusion()
                )
        );
    }

    // === Gem Ore Blocks ===
    private static RegistryObject<Block> registerGemOreBlock(OreFeature feat) {
        OreVariant var = feat.variant();
        return BLOCKS.register(feat.id(),
                () -> {
                    Block block = new DropExperienceBlock(
                            BlockBehaviour.Properties.of()
                                    .strength(var.hardness(), var.resistance())
                                    .sound(var.soundType())
                                    .requiresCorrectToolForDrops(),
                            UniformInt.of(2,4)
                    );
                    ModOreRegistry.bind(block, fromNamespaceAndPath(MOD_ID, feat.id()));
                    return block;
                }
        );
    }

    public static Stream<Block> oreBlocks(){
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(b -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(b)).getNamespace().equals(MOD_ID))
                .filter(b -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(b)).getPath().endsWith("_ore"));
    }

    public static Stream<Block> storageBlocks(){
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(b -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(b)).getNamespace().equals(MOD_ID))
                .filter(b -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(b)).getPath().startsWith("block_of_"));
    }

    public static Stream<Block> coralPolypBlocks(){
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(b -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(b)).getNamespace().equals(MOD_ID))
                .filter(b -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(b)).getPath().endsWith("_block_polyp"));
    }

    public static CoralPolypBlock getPolypVariantFor(Block coralBlock) {
        String path = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(coralBlock)).getPath() + "_polyp";
        return (CoralPolypBlock) ForgeRegistries.BLOCKS.getValue(fromNamespaceAndPath(MOD_ID, path));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
