package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.block.CoralPolypBlock;
import net.blueasclepias.bejeweled.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

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
        // Storage blocks
        ModBlocks.STORAGE_BLOCKS.forEach(block ->
                simpleBlockWithItem(block.get(), cubeAll(block.get()))
        );

        // Ore blocks
        ModBlocks.ORE_BLOCKS.forEach((type, variants) -> {
            variants.forEach((base, block) -> {
                simpleBlockWithItem(block.get(), cubeAll(block.get()));
            });
        });

        // Coral Polyp blocks
        ModBlocks.CORAL_POLYP_BLOCKS.forEach(block -> {
            coralPolyp(block.getId().getPath(), block.get());
        });
    }

    private void coralPolyp(String name, Block block) {
        ModelFile model = models()
                .withExistingParent(name, mcLoc("block/block"))
                .texture("texture", modLoc("block/" + name))
                .texture("particle", modLoc("block/" + name))
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