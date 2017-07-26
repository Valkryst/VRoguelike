package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.entity.Tile;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.component.Screen;

import java.util.Objects;

public class UpdateLOS implements Action {
    private final Screen screen;
    private final int sightRadius;

    public UpdateLOS(final Screen screen, final int sightRadius) {
        Objects.requireNonNull(screen);

        this.screen = screen;
        this.sightRadius = sightRadius;
    }

    @Override
    public void perform(final Map map, final Entity entity) {
        if (entity instanceof Player == false) {
            return;
        }

        final Player player = (Player) entity;
        final int playerX = player.getX();
        final int playerY = player.getY();

        final Tile[][] tiles = map.getTiles();

        for (int x = 0 ; x < tiles.length ; x++) {
            for (int y = 0 ; y < tiles[x].length ; y++) {
                if (tiles[x][y].isVisible()) {
                    tiles[x][y].setVisible(false);
                    tiles[x][y].placeOnScreen(screen, x, y);
                }
            }
        }

        final int xStart = Math.max(0, playerX - sightRadius);
        final int yStart = Math.max(0, playerY - sightRadius);
        final int xEnd = Math.min(screen.getWidth(), playerX + sightRadius);
        final int yEnd = Math.min(screen.getHeight(), playerY + sightRadius);

        for (int x = xStart ; x < xEnd ; x++) {
            for (int y = yStart ; y < yEnd ; y++) {
                tiles[x][y].setVisible(true);
                tiles[x][y].setVisited(true);
                tiles[x][y].placeOnScreen(screen, x, y);
            }
        }
    }
}
