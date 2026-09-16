package net.blueasclepias.bejeweled.server.event;

import net.blueasclepias.bejeweled.common.item.BaseJewelItem;
import net.blueasclepias.bejeweled.common.item.GemItem;
import net.blueasclepias.bejeweled.common.item.factory.SocketedJewelItemFactory;
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

/**
 * Global right-click handler for socketing gemstones into empty jewelry.
 * It listens for players using two items together and treats the main hand and offhand symmetrically, so the
 * gemstone and base jewel may be held in either order.
 * On success it consumes both inputs and replaces the hand that held the empty jewel with the new socketed item.
 */
@Mod.EventBusSubscriber(modid = MOD_ID)
public class JewelSocketingHandler {
    /**
     * Attempts to combine the player's two held items into a socketed jewel on the server.
     * One hand must hold a {@link GemItem} and the other a {@link BaseJewelItem}; whichever hand held the base
     * jewel receives the crafted result.
     */
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = event.getLevel();

        if (level.isClientSide) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();
        if (isGem(off, main) && isBase(off, main)) {

            ItemStack result;
            boolean isBaseOnOffHand = off.getItem() instanceof BaseJewelItem;
            if(isBaseOnOffHand)
                result = SocketedJewelItemFactory.create(main, off);
            else
                result = SocketedJewelItemFactory.create(off, main);

            if (!result.isEmpty()) {

                main.shrink(1);
                off.shrink(1);

                if(isBaseOnOffHand)
                    player.setItemInHand(InteractionHand.OFF_HAND, result);
                else
                    player.setItemInHand(InteractionHand.MAIN_HAND, result);

                // Play a sound upon crafting
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
        return main.getItem() instanceof GemItem ||
                off.getItem() instanceof GemItem;
    }

    private static boolean isBase(ItemStack off, ItemStack main){
        return main.getItem() instanceof BaseJewelItem ||
                off.getItem() instanceof BaseJewelItem;
    }
}
