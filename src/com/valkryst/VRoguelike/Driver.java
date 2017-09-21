package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.action.UpdateLOSAction;
import com.valkryst.VRoguelike.ai.AggressiveCombatAI;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.entity.builder.CreatureBuilder;
import com.valkryst.VRoguelike.entity.builder.PlayerBuilder;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.item.builder.equipment.WeaponBuilder;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.screen.GameScreen;
import com.valkryst.VRoguelike.screen.MainMenuScreen;
import com.valkryst.VRoguelike.world.Tile;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.PanelBuilder;
import com.valkryst.VTerminal.builder.component.ScreenBuilder;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.font.FontLoader;

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

        final ScreenBuilder screenBuilder = new ScreenBuilder();
        screenBuilder.setRowIndex(0);
        screenBuilder.setColumnIndex(0);
        screenBuilder.setWidth(panelBuilder.getHeightInCharacters());
        screenBuilder.setHeight(panelBuilder.getHeightInCharacters());

        final MainMenuScreen mainMenuScreen = new MainMenuScreen(panel, screenBuilder);
        final GameScreen gameScreen = new GameScreen(panel, screenBuilder);
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
            playerBuilder.setRace(Race.HUMAN);
            final Player player = playerBuilder.build();

            final WeaponBuilder weaponBuilder = new WeaponBuilder();
            weaponBuilder.setName("TWep");
            weaponBuilder.setDescription("DoTWep");
            weaponBuilder.setSlot(EquipmentSlot.MAIN_HAND);

            player.getEquipment().setItemInSlot(EquipmentSlot.MAIN_HAND, weaponBuilder.build());
            player.getActions().add(new UpdateLOSAction(25, 12, 0, 0));




            final CreatureBuilder creatureBuilder = new CreatureBuilder();
            creatureBuilder.setX(26);
            creatureBuilder.setY(13);
            creatureBuilder.setRace(Race.HUMAN);
            creatureBuilder.setCombatAI(new AggressiveCombatAI());
            creatureBuilder.setSprite(Sprite.ENEMY);
            final Creature npc = creatureBuilder.build();
            npc.getEquipment().setItemInSlot(EquipmentSlot.MAIN_HAND, weaponBuilder.build());

            gameScreen.getMap().addEntities(player, npc);
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
}
