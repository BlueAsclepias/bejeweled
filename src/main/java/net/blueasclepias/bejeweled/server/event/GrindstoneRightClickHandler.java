package net.blueasclepias.bejeweled.server.event;

import net.blueasclepias.bejeweled.common.data.gem.definition.GemCategory;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemDefinition;
import net.blueasclepias.bejeweled.common.data.gem.definition.GemGrade;
import net.blueasclepias.bejeweled.common.data.gem.registry.GemDefinitionRegistry;
import net.blueasclepias.bejeweled.common.item.factory.GemItemFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class GrindstoneRightClickHandler {
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

        // We are handling this interaction
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);

        // Produce output
        ItemStack result = assemble(held.getItem());
        if (result.isEmpty()) return;

        // Consume input
        held.shrink(1);

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

    private static @NotNull ItemStack assemble(@NotNull Item item) {
        // STUB
        RandomSource random = RandomSource.create();
        GemGrade gemGrade = GemGrade.random(random);
        ItemStack result = ItemStack.EMPTY;
        GemDefinition def = GemDefinitionRegistry.getDefinition(item);
        if (def != null && def.category() == GemCategory.BEAD) {
            result = GemItemFactory.create(def, gemGrade);
        }
        return result;
    }

}
