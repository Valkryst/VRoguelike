package com.valkryst.VRoguelike;

import com.valkryst.VJSON.VJSONLoader;
import com.valkryst.VRoguelike.action.UpdateLOSAction;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.entity.builder.CreatureBuilder;
import com.valkryst.VRoguelike.entity.builder.PlayerBuilder;
import com.valkryst.VRoguelike.item.builder.equipment.WeaponBuilder;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.loot.LootTable;
import com.valkryst.VRoguelike.screen.GameScreen;
import com.valkryst.VRoguelike.screen.MainMenuScreen;
import com.valkryst.VRoguelike.world.Room;
import com.valkryst.VRoguelike.world.Tile;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.PanelBuilder;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.font.FontLoader;
import org.json.simple.parser.ParseException;

import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.net.URISyntaxException;

public class Driver {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        final Font font = FontLoader.loadFontFromJar("Fonts/DejaVu Sans Mono/18pt/bitmap.png", "Fonts/DejaVu Sans Mono/18pt/data.fnt", 1);

        final PanelBuilder panelBuilder = new PanelBuilder();
        panelBuilder.setFont(font);
        panelBuilder.setWidthInCharacters(120);
        panelBuilder.setHeightInCharacters(40);

        final Panel panel = panelBuilder.build();

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
            final PlayerBuilder playerBuilder = new PlayerBuilder();
            playerBuilder.setX(25);
            playerBuilder.setY(12);
            final Player player = playerBuilder.build();

            final WeaponBuilder weaponBuilder = new WeaponBuilder();
            try {
                VJSONLoader.loadFromJSON(weaponBuilder, System.getProperty("user.dir") + "/test_res/test_item_weapon.json");
            } catch (final ParseException | IOException e) {
                e.printStackTrace();
            }

            player.getEquipment().setItemInSlot(EquipmentSlot.MAIN_HAND, weaponBuilder.build());
            player.getActions().add(new UpdateLOSAction(new Point(25, 12), 0, 0));




            final LootTable lootTable = new LootTable();
            lootTable.add(weaponBuilder.build(), 100);

            final CreatureBuilder creatureBuilder = new CreatureBuilder();
            try {
                VJSONLoader.loadFromJSON(creatureBuilder, System.getProperty("user.dir") + "/test_res/test_entity_creature.json");
            } catch (final ParseException | IOException e) {
                e.printStackTrace();
            }
            creatureBuilder.setLootTable(lootTable);
            final Creature npc = creatureBuilder.build();
            npc.getEquipment().setItemInSlot(EquipmentSlot.MAIN_HAND, weaponBuilder.build());

            gameScreen.getMap().addEntities(player, npc);

            // Create rooms:
            final Room roomA = new Room(new Point(20, 5), new Dimension(10, 15));
            final Room roomB = new Room(new Point(50, 5), new Dimension(10, 15));
            roomA.carve(gameScreen.getMap());
            roomB.carve(gameScreen.getMap());


            // Add Player/Creature Screens to main panel
            gameScreen.setPlayer(player);
            gameScreen.setTarget(npc);


            // Test movement
            player.getMovementAI().findAndSetPath(gameScreen.getMap(), player.getPosition(), new Point(player.getPosition().x + 3, player.getPosition().y + 5));
        });

        mainMenuScreen.getButton_exit().setOnClickFunction(() -> {
            System.exit(0);
        });

        // Render loop:
        final Timer timer = new Timer(100, e -> {
            gameScreen.getMap().update();
            panel.draw();
        });

        timer.start();
    }
}
