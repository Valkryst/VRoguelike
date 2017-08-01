package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.action.UpdateLOSPosition;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.entity.builder.CreatureBuilder;
import com.valkryst.VRoguelike.entity.builder.PlayerBuilder;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.Weapon;
import com.valkryst.VRoguelike.screen.GameScreen;
import com.valkryst.VRoguelike.screen.MainMenuScreen;
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

        Thread.sleep(50);

        final MainMenuScreen mainMenuScreen = new MainMenuScreen(panel);
        final GameScreen gameScreen = new GameScreen(panel);
        panel.swapScreen(mainMenuScreen);

        mainMenuScreen.getButton_new().setOnClickFunction(() -> {
            // Initialize map tiles:
            panel.swapScreen(gameScreen);

            final Tile[][] tiles = gameScreen.getMap().getTiles();

            for (int x = 0 ; x < tiles.length ; x++) {
                for (int y = 0 ; y < tiles[x].length ; y++) {
                    tiles[x][y].placeOnScreen(panel.getScreen(), x, y);
                }
            }

            // Initialize entities:
            final Player player = new PlayerBuilder().setX(25).setY(12).setRace(Race.HUMAN).build();
            player.getEquipment().setItemInSlot(EquipmentSlot.MAIN_HAND, new Weapon("TWep", "DoTWep", EquipmentSlot.MAIN_HAND));
            player.getActions().add(new UpdateLOSPosition(25, 12, 0, 0));

            final Creature npc = new CreatureBuilder().setX(26).setY(13).setRace(Race.HUMAN).setSprite(Sprite.ENEMY).build();

            gameScreen.getMap().addEntities(player, npc);

            // Create rooms:
            createRoom(panel, tiles, new Rectangle(20, 5, 10, 15));
            createRoom(panel, tiles, new Rectangle(50, 5, 10, 15));
        });

        mainMenuScreen.getButton_exit().setOnClickFunction(() -> {
            System.exit(0);
        });

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
