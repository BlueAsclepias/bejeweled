package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.material.definition.ore.OreFeature;
import net.blueasclepias.bejeweled.material.definition.ore.OreVariant;
import net.blueasclepias.bejeweled.material.registry.ModCoralPolypRegistry;
import net.blueasclepias.bejeweled.material.registry.ModOreRegistry;
import net.blueasclepias.bejeweled.material.registry.ModStorageBlockRegistry;
import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Generates Block States and Models for the mod.
 */
public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {

        // Gem Cutting Table
        // TODO: PROPER TEXTURES FOR A CUSTOM MODEL BLOCK
        simpleBlockWithItem(ModBlocks.GEM_CUTTING_TABLE.get(), cubeAll(ModBlocks.GEM_CUTTING_TABLE.get()));

        // Ore blocks
        ModOreRegistry.allBlocksByFeature().forEach(this::oreBlock);

        // Storage blocks
        ModStorageBlockRegistry.allBlocks().forEach(block ->
                simpleBlockWithItem(block, cubeAll(block))
        );

        // Coral Polyp blocks
        ModCoralPolypRegistry.allBlocks().forEach(this::coralPolyp);
    }

    private void oreBlock(OreFeature feat, Block block){
        OreVariant variant = feat.variant();
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

        ResourceLocation overlay = modLoc("block/ore/" + feat.definition().id());

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