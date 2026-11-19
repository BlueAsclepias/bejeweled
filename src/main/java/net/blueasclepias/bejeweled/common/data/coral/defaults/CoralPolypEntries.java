package net.blueasclepias.bejeweled.common.data.coral.defaults;

import net.blueasclepias.bejeweled.common.data.coral.definition.CoralPolypEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * The mod's complete list of generated coral polyp blocks. {@link #ALL} lets consumers (block/item registration)
 * iterate every entry without needing one named field per polyp block.
 */
public final class CoralPolypEntries {

    public static final CoralPolypEntry FIRE = new CoralPolypEntry(
            "fire_coral_block_polyp", modLoc("red_coral"), Blocks.FIRE_CORAL_BLOCK
    );

    public static final CoralPolypEntry BRAIN = new CoralPolypEntry(
            "brain_coral_block_polyp", modLoc("pink_coral"), Blocks.BRAIN_CORAL_BLOCK
    );

    public static final CoralPolypEntry BUBBLE = new CoralPolypEntry(
            "bubble_coral_block_polyp", modLoc("purple_coral"), Blocks.BUBBLE_CORAL_BLOCK
    );

    public static final CoralPolypEntry HORN = new CoralPolypEntry(
            "horn_coral_block_polyp", modLoc("yellow_coral"), Blocks.HORN_CORAL_BLOCK
    );

    public static final CoralPolypEntry TUBE = new CoralPolypEntry(
            "tube_coral_block_polyp", modLoc("blue_coral"), Blocks.TUBE_CORAL_BLOCK
    );

    public static final List<CoralPolypEntry> ALL = List.of(FIRE, BRAIN, BUBBLE, HORN, TUBE);

    private static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
