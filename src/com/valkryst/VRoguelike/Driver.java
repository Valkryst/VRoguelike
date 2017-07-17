package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.entity.Tile;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.PanelBuilder;
import com.valkryst.VTerminal.component.Screen;
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
        final Tile[][] tiles = new Tile[90][30];

        for (int x = 0 ; x < tiles.length ; x++) {
            for (int y = 0 ; y < tiles[x].length ; y++) {
                tiles[x][y] = new Tile(Sprite.DARK_WALL);
                tiles[x][y].setSolid(true);
                tiles[x][y].setTransparent(false);
                tiles[x][y].placeOnScreen(panel.getScreen(), x, y);
            }
        }

        final Map map = new Map(tiles);

        // Initialize entity:
        final Entity player = new Player(25, 12, Sprite.PLAYER);
        player.show(panel);

        final Entity npc = new Entity(40, 15, Sprite.ENEMY);
        npc.show(panel);

        map.addEntity(player);
        map.addEntity(npc);

        // Create rooms:
        createRoom(panel, tiles, new Rectangle(20, 5, 10, 15));
        createRoom(panel, tiles, new Rectangle(50, 5, 10, 15));

        // Render loop:
        while (true) {
            map.update();
            panel.draw();
            Thread.sleep(100);
        }
    }

    private static void createRoom(final Panel panel, final Tile[][] tiles, final Rectangle rectangle) {
        final Screen screen = panel.getScreen();
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
