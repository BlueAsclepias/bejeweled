package net.blueasclepias.bejeweled.common.screen;

import net.blueasclepias.bejeweled.common.container.GemCuttingTableMenu;
import net.blueasclepias.bejeweled.common.network.ModNetwork;
import net.blueasclepias.bejeweled.common.network.packet.ProcessGemCuttingPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Client-side screen for the gem cutting table menu.
 * It renders the workstation background, the vanilla-style slot overlays, and the button that asks the server to
 * process the current gemstone.
 * The screen never performs the cut locally; it only reflects menu state and sends the process request packet.
 */
public class GemCuttingTableScreen extends AbstractContainerScreen<GemCuttingTableMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/gui/gem_cutting_table.png");

    private static final ResourceLocation SLOT =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/generic_54.png");


    private Button processButton;

    public GemCuttingTableScreen(
            GemCuttingTableMenu menu,
            Inventory inventory,
            Component title
    ) {
        super(menu, inventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        processButton = Button.builder(
                Component.literal(">"),
                b -> sendProcess()
        ).bounds(leftPos + imageWidth/2 - 10, topPos + 35, 20, 20).build();

        addRenderableWidget(processButton);
    }

    /**
     * Mirrors the table's current processability each tick so the client only enables the action button when the
     * menu reports a valid input/output state.
     */
    @Override
    public void containerTick() {
        super.containerTick();
        processButton.active = menu.canProcess();
    }

    @Override
    protected void renderBg(
            GuiGraphics gui,
            float partialTick,
            int mouseX,
            int mouseY
    ) {
        // Background
        gui.blit(
                TEXTURE,
                leftPos,
                topPos,
                0,
                0,
                imageWidth,
                imageHeight);

        // Inventory
        gui.blit(
                SLOT,
                leftPos + 7,
                topPos + 83,
                7,
                17,
                162,
                54
        );
        // Hotbar
        gui.blit(
                SLOT,
                leftPos + 7,
                topPos + 141,
                7,
                197,
                162,
                18
        );

        // input
        gui.blit(
                SLOT,
                leftPos + 7,
                topPos + 34,
                7,
                17,
                18,
                18
        );


        // output
        gui.blit(
                SLOT,
                leftPos + 151,
                topPos + 34,
                7,
                17,
                18,
                18
        );

    }

    @Override
    public void render(
            @NotNull GuiGraphics gui,
            int mouseX,
            int mouseY,
            float partialTick
    ) {
        renderBackground(gui);
        super.render(gui, mouseX, mouseY, partialTick);
        renderTooltip(gui, mouseX, mouseY);
    }

    /**
     * Sends a request for the currently opened table to process its input on the server.
     * The server re-validates the open menu and block position before any item state is changed.
     */
    private void sendProcess() {
        ModNetwork.CHANNEL.sendToServer(
                new ProcessGemCuttingPacket(menu.getBlockPos())
        );
    }
}
