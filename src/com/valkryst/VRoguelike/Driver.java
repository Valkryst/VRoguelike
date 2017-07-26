package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.entity.Tile;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.PanelBuilder;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.font.FontLoader;
import com.valkryst.generator.MarkovNameGenerator;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
                tiles[x][y] = new Tile(Sprite.WALL);
                tiles[x][y].setSolid(true);
                tiles[x][y].setTransparent(false);
                tiles[x][y].placeOnScreen(panel.getScreen(), x, y);
            }
        }

        final Map map = new Map(tiles);

        // Initialize entity:
        final Player player = new Player(25, 12);
        player.show(panel);

        final Creature npc = new Creature(40, 15, Sprite.ENEMY);
        npc.show(panel);

        map.addEntity(player);
        map.addEntity(npc);

        // Initialize entity names:
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream is = classloader.getResourceAsStream("Human/Viking/Male.txt");
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        final List<String> trainingNames = new ArrayList<>();
        String line = "";

        while ((line = br.readLine()) != null) {
            trainingNames.add(line);
        }

        final MarkovNameGenerator nameGenerator = new MarkovNameGenerator(trainingNames);

        player.setName(nameGenerator.generateName(6));
        npc.setName(nameGenerator.generateName(6));

        System.out.println(player.getName() + " " + npc.getName());

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
