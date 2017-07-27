package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VRoguelike.world.Tile;
import com.valkryst.VTerminal.component.Screen;

import java.util.Objects;

public class UpdateLOSPosition implements Action {
    private final Screen screen;
    private final int dx;
    private final int dy;

    public UpdateLOSPosition(final Screen screen, final int dx, final int dy) {
        Objects.requireNonNull(screen);

        this.screen = screen;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void perform(final Map map, final Entity entity) {
        if (entity instanceof Creature == false) {
            return;
        }

        final Tile[][] tiles = map.getTiles();

        if (entity instanceof Player) {
            // todo Update to only reset tiles at the player's last position.
            for (int x = 0 ; x < tiles.length ; x++) {
                for (int y = 0 ; y < tiles[x].length ; y++) {
                    if (tiles[x][y].isVisible()) {
                        tiles[x][y].setVisible(false);
                        tiles[x][y].placeOnScreen(screen, x, y);
                    }
                }
            }
        }

        final Creature creature = (Creature) entity;
        creature.getLineOfSight().move(dx, dy);
    }
}
