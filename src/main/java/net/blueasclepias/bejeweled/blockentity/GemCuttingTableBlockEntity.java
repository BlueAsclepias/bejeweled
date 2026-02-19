package net.blueasclepias.bejeweled.blockentity;

import net.blueasclepias.bejeweled.container.GemCuttingTableMenu;
import net.blueasclepias.bejeweled.recipe.GemCuttingRecipe;
import net.blueasclepias.bejeweled.registry.ModBlockEntities;
import net.blueasclepias.bejeweled.registry.ModRecipes;
import net.blueasclepias.bejeweled.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

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

    public boolean canPlaceInInput(@NotNull ItemStack stack) {
        return stack.is(ModTags.Items.RAW_GEMSTONES);
    }

    public void tryProcessRecipe() {
        Optional<GemCuttingRecipe> recipeOpt = getMatchingRecipe();
        if (recipeOpt.isEmpty()) return;

        GemCuttingRecipe recipe = recipeOpt.get();
        ItemStack result = recipe.assemble(inventory, Objects.requireNonNull(level).registryAccess());

        inventory.setItem(INPUT_SLOT, ItemStack.EMPTY);
        inventory.setItem(OUTPUT_SLOT, result);

        setChanged();
    }

    public boolean canProcess() {
        if (inventory.getItem(INPUT_SLOT).isEmpty()) return false;
        if (!inventory.getItem(OUTPUT_SLOT).isEmpty()) return false;
        return getMatchingRecipe().isPresent();
    }

    public boolean processOnce() {
        /* TODO: will be used later once minigame is implemented and player completes it.
        if (!canProcess()) return false;

        ItemStack input = inventory.getItem(INPUT_SLOT);
        ItemStack result = previewResult(input);
        ItemStack output = inventory.getItem(OUTPUT_SLOT);

        // consume input
        inventory.setItem(INPUT_SLOT, ItemStack.EMPTY);

        // place/grow output
        if (output.isEmpty()) {
            inventory.setItem(OUTPUT_SLOT, result.copy());
        } else {
            output.grow(result.getCount());
        }

        setChanged();
        */
        return true;
    }

    private Optional<GemCuttingRecipe> getMatchingRecipe() {
        if (level == null) return Optional.empty();
        return level.getRecipeManager()
                .getRecipeFor(
                        ModRecipes.GEM_CUTTING_TYPE.get(),
                        this.inventory,
                        level
                );
    }
}
