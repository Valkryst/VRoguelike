package com.valkryst.VRoguelike;

import com.valkryst.VJSON.VJSONLoader;
import com.valkryst.VRoguelike.action.UpdateLOSAction;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.entity.builder.PlayerBuilder;
import com.valkryst.VRoguelike.item.builder.equipment.WeaponBuilder;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.view.GameView;
import com.valkryst.VRoguelike.view.MainMenuView;
import com.valkryst.VRoguelike.world.MapTile;
import com.valkryst.VTerminal.Screen;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.font.FontLoader;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        final Font font = FontLoader.loadFontFromJar("Fonts/DejaVu Sans Mono/18pt/bitmap.png", "Fonts/DejaVu Sans Mono/18pt/data.fnt", 1);
        final Screen screen = new Screen(120, 40, font);


        final MainMenuView mainMenuView = new MainMenuView(screen);
        final GameView gameView = new GameView(screen);

        screen.addComponent(mainMenuView);

        mainMenuView.getButton_new().setOnClickFunction(() -> {
            // Initialize map tiles:
            screen.removeComponent(mainMenuView);
            screen.addComponent(gameView);

            final MapTile[][] tiles = gameView.getMap().getMapTiles();

            for (int x = 0 ; x < tiles.length ; x++) {
                for (int y = 0 ; y < tiles[x].length ; y++) {
                    tiles[x][y].placeOnScreen(gameView, x, y);
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



            /*
            final Creature npc = creatureBuilder.build();
            npc.getEquipment().setItemInSlot(EquipmentSlot.MAIN_HAND, weaponBuilder.build());
            */

            try {
                VJSONLoader.loadFromJSON(gameView.getMap(), System.getProperty("user.dir") + "/test_res/test_map.json");
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }

            gameView.getMap().addEntities(player);


            // Add Player/Creature Screens to main panel
            gameView.setPlayer(player);


            // Test movement
            player.getMovementAI().findAndSetPath(gameView.getMap(), player.getPosition(), new Point(player.getPosition().x + 3, player.getPosition().y + 5));
        });

        mainMenuView.getButton_exit().setOnClickFunction(() -> System.exit(0));

        // Render loop:
        screen.addCanvasToFrame();

        final Timer timer = new Timer(100, e -> {
            gameView.getMap().update();
            screen.draw();
        });

        timer.start();
    }
}
