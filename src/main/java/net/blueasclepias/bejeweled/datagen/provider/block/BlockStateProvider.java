package net.blueasclepias.bejeweled.datagen.provider.block;

import net.blueasclepias.bejeweled.common.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.common.data.coral.registry.CoralPolypRegistry;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreBlockVariant;
import net.blueasclepias.bejeweled.common.data.ore.definition.OreEntry;
import net.blueasclepias.bejeweled.common.data.ore.registry.OreRegistry;
import net.blueasclepias.bejeweled.common.data.storage.registry.StorageBlockRegistry;
import net.blueasclepias.bejeweled.common.registry.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Generates blockstate and model JSON for the gem cutting table, data-driven ore and storage blocks, and directional
 * coral polyp blocks.
 * Ore models are composed from the host block's top and side textures plus a cutout overlay, while coral polyps use a
 * small protruding model rotated by facing.
 */
public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, MOD_ID, helper);
    }

    /**
     * Generates models for the workstation and storage blocks, layered ore models from the ore registry,
     * and directional coral polyp states and items from the coral registry.
     */
    @Override
    protected void registerStatesAndModels() {


        // Gem Cutting Table
        ModelFile gemCuttingTableModel = new ModelFile.UncheckedModelFile(
                modLoc("block/gem_cutting_table")
        );

        horizontalBlock(
                ModBlocks.GEM_CUTTING_TABLE.get(),
                gemCuttingTableModel
        );

        itemModels()
                .getBuilder("gem_cutting_table")
                .parent(gemCuttingTableModel)
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 225, 0)
                .scale(0.625F, 0.625F, 0.625F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, 45, 0)
                .scale(0.4F, 0.4F, 0.4F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(0, 225, 0)
                .scale(0.4F, 0.4F, 0.4F)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(10, -45, 170)
                .translation(0, 1.5F, -2.75F)
                .scale(0.4F, 0.4F, 0.4F)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(10, 135, 170)
                .translation(0, 1.5F, -2.75F)
                .scale(0.4F, 0.4F, 0.4F)
                .end();

        // Ore blocks
        OreRegistry.blocksByEntry().forEach(this::oreBlock);

        // Storage blocks
        StorageBlockRegistry.allBlocks().forEach(block ->
                simpleBlockWithItem(block, cubeAll(block))
        );

        // Coral Polyp blocks
        CoralPolypRegistry.allBlocks().forEach(this::coralPolyp);
    }

    /**
     * Builds a two-layer cube model for an ore block by borrowing top and side textures from its configured host block
     * variant and rendering the gem overlay as a cutout second element.
     */
    private void oreBlock(OreEntry entry, Block block){
        OreBlockVariant variant = entry.variant();
        ResourceLocation blockId = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
        ResourceLocation baseBlockId = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(variant.baseBlock()));

        String path = blockId.getPath();
        String basePath = baseBlockId.getPath();

        ResourceLocation vertical = variant.hasTop()
                ? mcLoc("block/" + basePath + "_top")
                : mcLoc("block/" + basePath);

        ResourceLocation horizontal = variant.hasSide()
                ? mcLoc("block/" + basePath + "_side")
                : mcLoc("block/" + basePath);

        ResourceLocation overlay = modLoc("block/ore/" + entry.definition().id());

        BlockModelBuilder model = models().getBuilder(path)
                .parent(models().getExistingFile(mcLoc("block/cube")))
                .texture("particle", horizontal)
                .texture("up", vertical)
                .texture("down", vertical)
                .texture("east", horizontal)
                .texture("west", horizontal)
                .texture("north", horizontal)
                .texture("south", horizontal)
                .texture("overlay", overlay)
                .renderType("minecraft:cutout");

        model.element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((dir, face) ->
                        face.texture(dir == Direction.UP || dir == Direction.DOWN
                                ? "#up"
                                : "#north"
                        ).cullface(dir)
                );

        model.element()
                .from(0, 0, 0)
                .to(16, 16, 16)
                .allFaces((dir, face) ->
                        face.texture("#overlay").cullface(dir)
                );

        simpleBlockWithItem(block, model);
    }

    /**
     * Creates the attached coral polyp model, rotates it for each horizontal facing while ignoring
     * {@link CoralPolypBlock#WATERLOGGED}, and reuses that model for the inventory item.
     */
    private void coralPolyp(Block block) {
        String path = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
        ModelFile model = models()
                .withExistingParent(path, mcLoc("block/block"))
                .texture("texture", modLoc("block/" + path))
                .texture("particle", modLoc("block/" + path))
                .element()
                .from(5, 5, 10) // 10
                .to(11, 11, 16) // 16
                .allFaces((dir, face) ->
                        face.texture("#texture").uvs(0, 0, 16, 16))
                .end();

        getVariantBuilder(block)
                .forAllStatesExcept(state ->
                                ConfiguredModel.builder()
                                        .modelFile(model)
                                        .rotationY(
                                                ((int) state.getValue(CoralPolypBlock.FACING).getOpposite().toYRot()))
                                        .build(),
                        CoralPolypBlock.WATERLOGGED
                );

        simpleBlockItem(block, model);
    }

}