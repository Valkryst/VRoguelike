package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.action.UpdateLOSPosition;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.entity.builder.CreatureBuilder;
import com.valkryst.VRoguelike.entity.builder.PlayerBuilder;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.Weapon;
import com.valkryst.VRoguelike.screen.GameScreen;
import com.valkryst.VRoguelike.world.Tile;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.PanelBuilder;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.font.FontLoader;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.URISyntaxException;

public class Driver {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        final Font font = FontLoader.loadFontFromJar("Fonts/DejaVu Sans Mono/20pt/bitmap.png", "Fonts/DejaVu Sans Mono/20pt/data.fnt", 1);
        final Panel panel = new PanelBuilder().setFont(font)
                .setWidthInCharacters(90)
                .setHeightInCharacters(30)
                .build();

        Thread.sleep(200);

        // Initialize map tiles:
        final GameScreen gameScreen = new GameScreen(panel);
        final Tile[][] tiles = gameScreen.getMap().getTiles();

        for (int x = 0 ; x < tiles.length ; x++) {
            for (int y = 0 ; y < tiles[x].length ; y++) {
                tiles[x][y].placeOnScreen(panel.getScreen(), x, y);
            }
        }

        // Initialize entity:
        final Player player = new PlayerBuilder().setX(25).setY(12).build();
        player.getEquipment().setItemInSlot(EquipmentSlot.MAIN_HAND, new Weapon("TWep", "DoTWep", EquipmentSlot.MAIN_HAND));
        player.getActions().add(new UpdateLOSPosition(0, 0));
        player.show();

        final Creature npc = new CreatureBuilder().setX(26).setY(13).setSprite(Sprite.ENEMY).build();
        npc.show();

        gameScreen.getMap().addEntities(player, npc);

        // Create rooms:
        createRoom(panel, tiles, new Rectangle(20, 5, 10, 15));
        createRoom(panel, tiles, new Rectangle(50, 5, 10, 15));

        // Render loop:
        while (true) {
            gameScreen.getMap().update();
            panel.draw();
            Thread.sleep(100);
        }
    }

    private static void createRoom(final Panel panel, final Tile[][] tiles, final Rectangle rectangle) {
        final int endX = rectangle.width + rectangle.x;
        final int endY = rectangle.height + rectangle.y;

        for (int x = rectangle.x ; x < endX ; x++) {
            for (int y = rectangle.y ; y < endY ; y++) {
                tiles[x][y].setSprite(Sprite.DIRT);
                tiles[x][y].setSolid(false);
                tiles[x][y].setTransparent(false);
                tiles[x][y].placeOnScreen(panel.getScreen(), x, y);
            }
        }
    }
}
