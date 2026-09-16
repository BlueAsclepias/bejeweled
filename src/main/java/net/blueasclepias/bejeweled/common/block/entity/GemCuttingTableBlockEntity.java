package net.blueasclepias.bejeweled.common.block.entity;

import net.blueasclepias.bejeweled.common.container.GemCuttingTableMenu;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.blueasclepias.bejeweled.common.item.factory.GemItemFactory;
import net.blueasclepias.bejeweled.common.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * Server-side inventory and processing state for the gem cutting table workstation.
 * It owns the shared two-slot container used by the block, menu, and screen during the gem-cutting loop.
 * The current cutting result is still placeholder logic: processing simply rolls a random grade for gemstone
 * inputs until the real minigame or skill-check system is implemented.
 */
public class GemCuttingTableBlockEntity extends BlockEntity implements MenuProvider {

    private final SimpleContainer inventory = new SimpleContainer(2);

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    public GemCuttingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEM_CUTTING_TABLE.get(), pos, state);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable(
                "container.bejeweled.gem_cutting"
        );
    }

    // Server, please construct the menu
    @Override
    public AbstractContainerMenu createMenu(
            int id,
            @NotNull Inventory playerInventory,
            @NotNull Player player
    ) {
        return new GemCuttingTableMenu(
                id,
                playerInventory,
                this
        );
    }

    public Container getInventory() {
        return inventory;
    }

    /**
     * Consumes the current input and writes the assembled result into the output slot when processing succeeds.
     * This is the server-authoritative entry point invoked after the menu and packet flow have been validated.
     */
    public void tryProcess() {
        ItemStack result = assemble(inventory);
        if (result.isEmpty()) return;

        inventory.setItem(INPUT_SLOT, ItemStack.EMPTY);
        inventory.setItem(OUTPUT_SLOT, result);
        setChanged();
    }

    /**
     * Reports whether the table is ready to process its contents.
     * Processing is only allowed when an input item is present and the output slot is empty.
     */
    public boolean canProcess() {
        return inventory.getItem(OUTPUT_SLOT).isEmpty() && (!inventory.getItem(INPUT_SLOT).isEmpty());
    }

    /**
     * Builds the output stack for the current input item.
     * This is intentionally a stub: valid gemstone inputs are accepted, but their resulting grade is chosen at
     * random instead of being determined by a finished gem-cutting mechanic.
     */
    private @NotNull ItemStack assemble(@NotNull Container container) {
        // STUB
        RandomSource random = RandomSource.create();
        GemGrade gemGrade = GemGrade.random(random);
        ItemStack input = container.getItem(0);
        Item item = input.getItem();
        ItemStack result = ItemStack.EMPTY;
        GemDefinition def = GemDefinitionRegistry.getDefinition(item);
        if (def != null && def.category() == GemCategory.GEMSTONE) {
            result = GemItemFactory.create(def, gemGrade);
        }
        return result;
    }
}
