package net.blueasclepias.bejeweled.common.container;

import net.blueasclepias.bejeweled.common.block.entity.GemCuttingTableBlockEntity;
import net.blueasclepias.bejeweled.common.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class GemCuttingTableMenu extends AbstractContainerMenu {

    private final GemCuttingTableBlockEntity blockEntity;

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int CONTAINER_SLOT_COUNT = 2;

    // Server-side constructor
    public GemCuttingTableMenu(
            int id,
            Inventory playerInventory,
            @NotNull GemCuttingTableBlockEntity blockEntity
    ) {
        super(ModMenus.GEM_CUTTING_TABLE.get(), id);
        this.blockEntity = blockEntity;

        Container container = blockEntity.getInventory();

        // === Input slot (only 1 item) ===
        this.addSlot(new Slot(container, INPUT_SLOT, 8, 35) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public int getMaxStackSize(@NotNull ItemStack stack) {
                return 1;
            }
        });

        // === Output slot ===
        this.addSlot(new Slot(container, 1, 152, 35) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return false;
            }
        });

        // === Player inventory ===
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    // Client-side constructor
    public GemCuttingTableMenu(
            int id,
            Inventory playerInventory,
            FriendlyByteBuf buf
    ) {
        this(
                id,
                playerInventory,
                getBlockEntity(playerInventory.player.level(), buf)
        );
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();

            // Output → player
            if (index == OUTPUT_SLOT) {
                if (!this.moveItemStackTo(
                        stack,
                        CONTAINER_SLOT_COUNT,
                        this.slots.size(),
                        true
                )) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, result);
            }

            // Player → input
            else if (index >= CONTAINER_SLOT_COUNT) {
                if (!this.moveItemStackTo(
                        stack,
                        INPUT_SLOT,
                        INPUT_SLOT + 1,
                        false
                )) {
                    return ItemStack.EMPTY;
                }
                // Inventory ↔ hotbar
                else if (index < CONTAINER_SLOT_COUNT + 27) {
                    if (!this.moveItemStackTo(
                            stack,
                            CONTAINER_SLOT_COUNT + 27,
                            this.slots.size(),
                            false
                    )) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(
                            stack,
                            CONTAINER_SLOT_COUNT,
                            CONTAINER_SLOT_COUNT + 27,
                            false
                    )) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            // Input → player
            else if (index == INPUT_SLOT) {
                if (!this.moveItemStackTo(
                        stack,
                        CONTAINER_SLOT_COUNT,
                        this.slots.size(),
                        false
                )) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return result;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return isBlockEntityValid() &&
                player.distanceToSqr(
                        blockEntity.getBlockPos().getCenter()
                ) <= 64.0;
    }

    private boolean isBlockEntityValid() {
        Level level = blockEntity.getLevel();
        return level != null &&
                level.getBlockEntity(blockEntity.getBlockPos()) == blockEntity;
    }

    private static GemCuttingTableBlockEntity getBlockEntity(
            @NotNull Level level,
            @NotNull FriendlyByteBuf buf
    ) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof GemCuttingTableBlockEntity workstation)) {
            throw new IllegalStateException(
                    "Expected GemCuttingTableBlockEntity at " + pos
            );
        }
        return workstation;
    }

    // === Inventory Layout Helpers ===
    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18
                ));
            }
        }
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col,
                    8 + col * 18,
                    142
            ));
        }
    }

    public void tryProcess() {
        blockEntity.tryProcess();
    }

    public boolean canProcess() {
        return blockEntity.canProcess();
    }

    public BlockPos getBlockPos() {
        return blockEntity.getBlockPos();
    }

}
