package net.blueasclepias.bejeweled.event;

import net.blueasclepias.bejeweled.recipe.BeadPolishingRecipe;
import net.blueasclepias.bejeweled.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class GrindstoneRightClickEvent {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide) return;

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (!state.is(Blocks.GRINDSTONE)) return;

        Player player = event.getEntity();
        if (!player.isCrouching()) return;

        ItemStack held = player.getItemInHand(event.getHand());
        if (held.isEmpty()) return;

        // Try to find a polishing recipe
        SimpleContainer container = new SimpleContainer(1);
        container.setItem(0, held);

        Optional<BeadPolishingRecipe> recipe =
                level.getRecipeManager().getRecipeFor(
                        ModRecipes.BEAD_POLISHING_TYPE.get(),
                        container,
                        level
                );

        if (recipe.isEmpty()) return;

        // We are handling this interaction
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);

        // Consume input
        held.shrink(1);

        // Produce output
        ItemStack result =
                recipe.get().assemble(container, level.registryAccess());

        if (!player.addItem(result)) {
            player.drop(result, false);
        }

        // Feedback
        level.playSound(
                null,
                pos,
                SoundEvents.GRINDSTONE_USE,
                SoundSource.BLOCKS,
                1.0f,
                1.0f
        );

        player.swing(event.getHand(), true);
    }

}
