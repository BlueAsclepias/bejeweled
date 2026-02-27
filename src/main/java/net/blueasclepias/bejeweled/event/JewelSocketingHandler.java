package net.blueasclepias.bejeweled.event;

import net.blueasclepias.bejeweled.item.BaseJewelItem;
import net.blueasclepias.bejeweled.item.ProcessedGemItem;
import net.blueasclepias.bejeweled.item.SocketedJewelItemFactory;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class JewelSocketingHandler {
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = event.getLevel();

        if (level.isClientSide) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        if (isGem(off, main) && isBase(off, main)) {

            ItemStack result;
            if(main.getItem() instanceof ProcessedGemItem)
                result = SocketedJewelItemFactory.create(main, off);
            else
                result = SocketedJewelItemFactory.create(off, main);

            if (!result.isEmpty()) {

                main.shrink(1);
                off.shrink(1);

                player.setItemInHand(InteractionHand.MAIN_HAND, result);

                // Play a sound upon crafting - TODO: is this sound good?
                player.level().playSound(
                        null,
                        player.blockPosition(),
                        SoundEvents.EXPERIENCE_ORB_PICKUP,
                        SoundSource.PLAYERS,
                        0.8F,
                        1.2F
                );

                event.setCanceled(true);
            }
        }
    }

    private static boolean isGem(ItemStack off, ItemStack main){
        return main.getItem() instanceof ProcessedGemItem ||
                off.getItem() instanceof ProcessedGemItem;
    }

    private static boolean isBase(ItemStack off, ItemStack main){
        return main.getItem() instanceof BaseJewelItem ||
                off.getItem() instanceof BaseJewelItem;
    }
}
