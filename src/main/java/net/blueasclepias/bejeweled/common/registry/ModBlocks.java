package net.blueasclepias.bejeweled.common.registry;

import net.blueasclepias.bejeweled.common.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.common.block.GemCuttingTableBlock;
import net.blueasclepias.bejeweled.common.data.coral.defaults.CoralPolypEntries;
import net.blueasclepias.bejeweled.common.data.coral.definition.CoralPolypEntry;
import net.blueasclepias.bejeweled.common.data.coral.registry.CoralPolypRegistry;
import net.blueasclepias.bejeweled.common.data.ore.defaults.OreEntries;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreBlockVariant;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreEntry;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreRegistry;
import net.blueasclepias.bejeweled.common.data.storage.defaults.StorageBlockEntries;
import net.blueasclepias.bejeweled.common.data.storage.definition.StorageBlockEntry;
import net.blueasclepias.bejeweled.common.data.storage.registry.StorageBlockRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;

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
                            BlockBehaviour.Properties.of()
                                    .strength(2.5F)
                                    .sound(SoundType.WOOD)
                    )
            );

    // ===== ORE BLOCKS ====
    // One block per OreEntries.ALL entry, keyed by ore id, so adding/removing an ore only requires a change in
    // OreEntries rather than also touching this class.
    public static final Map<String, RegistryObject<Block>> ORE_BLOCKS = registerOreBlocks();

    // ===== STORAGE BLOCKS =====
    // One block per StorageBlockEntries.ALL entry, keyed by entry id, mirroring ORE_BLOCKS above.
    public static final Map<String, RegistryObject<Block>> STORAGE_BLOCKS = registerStorageBlocks();

    // ===== CORAL POLYP BLOCKS =====
    // One block per CoralPolypEntries.ALL entry, keyed by entry id, mirroring ORE_BLOCKS above.
    public static final Map<String, RegistryObject<Block>> CORAL_POLYP_BLOCKS = registerCoralPolyps();

    private static BlockBehaviour.Properties getStorageBlockProperties(StorageBlockEntry entry) {
        return BlockBehaviour.Properties.of()
                .mapColor(entry.color())
                .strength(.5f, 1f)
                .sound(SoundType.AMETHYST);
    }

    // ===== Registration Helpers =====
    // === Storage Blocks ===
    private static Map<String, RegistryObject<Block>> registerStorageBlocks() {
        Map<String, RegistryObject<Block>> blocks = new LinkedHashMap<>();
        for (StorageBlockEntry entry : StorageBlockEntries.ALL) {
            blocks.put(entry.id(), registerStorageBlock(entry));
        }
        return blocks;
    }

    private static RegistryObject<Block> registerStorageBlock(StorageBlockEntry entry) {
        return BLOCKS.register(entry.id(),
                () -> {
                    Block block = new Block(getStorageBlockProperties(entry));
                    StorageBlockRegistry.bind(block, ResourceLocation.fromNamespaceAndPath(MOD_ID, entry.id()));
                    return block;
                });
    }

    // === Coral Polyp Blocks ===
    private static Map<String, RegistryObject<Block>> registerCoralPolyps() {
        Map<String, RegistryObject<Block>> blocks = new LinkedHashMap<>();
        for (CoralPolypEntry entry : CoralPolypEntries.ALL) {
            blocks.put(entry.id(), registerCoralPolyp(entry));
        }
        return blocks;
    }

    private static RegistryObject<Block> registerCoralPolyp(CoralPolypEntry entry){
        return BLOCKS.register(entry.id(),
                () -> {
                    CoralPolypBlock polypBlock = new CoralPolypBlock(
                            entry.coralBlock(),
                            entry.drop(),
                            BlockBehaviour.Properties.of()
                                    .mapColor(entry.coralBlock().defaultMapColor())
                                    .strength(0.3f)
                                    .sound(SoundType.CORAL_BLOCK)
                                    .noOcclusion()
                    );
                    CoralPolypRegistry.bind(polypBlock, entry.coralBlock(), ResourceLocation.fromNamespaceAndPath(MOD_ID, entry.id()));
                    return polypBlock;
                }
        );
    }

    // === Gem Ore Blocks ===
    private static Map<String, RegistryObject<Block>> registerOreBlocks() {
        Map<String, RegistryObject<Block>> blocks = new LinkedHashMap<>();
        for (OreEntry entry : OreEntries.ALL) {
            blocks.put(entry.id(), registerGemOreBlock(entry));
        }
        return blocks;
    }

    private static RegistryObject<Block> registerGemOreBlock(OreEntry entry) {
        OreBlockVariant var = entry.variant();
        return BLOCKS.register(entry.id(),
                () -> {
                    Block block = new DropExperienceBlock(
                            BlockBehaviour.Properties.of()
                                    .strength(var.hardness(), var.resistance())
                                    .sound(var.soundType())
                                    .requiresCorrectToolForDrops(),
                            UniformInt.of(2,4)
                    );
                    OreRegistry.bind(block, ResourceLocation.fromNamespaceAndPath(MOD_ID, entry.id()));
                    return block;
                }
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
