package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.LineOfSight;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VRoguelike.world.Tile;
import com.valkryst.VTerminal.component.Screen;

import java.awt.Point;

public class UpdateLOSPosition implements Action {
    /** The change in x-axis position. */
    private final int dx;
    /** The change in y-axis position. */
    private final int dy;

    /**
     * Constructs a new UpdateLOSPosition.
     *
     * @param dx
     *        The change in x-axis position.
     *
     * @param dy
     *        The change in y-axis position.
     */
    public UpdateLOSPosition(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void perform(final Map map, final Entity entity) {
        if (entity instanceof Creature == false) {
            return;
        }

        final Screen screen = map.getScreen();
        final Tile[][] tiles = map.getTiles();
        final Creature creature = (Creature) entity;

        if (tiles[creature.getX()][creature.getY()].isSolid()) {
            return;
        }

        creature.getLineOfSight().move(dx, dy);

        if (entity instanceof Player) {
            // Set all tiles to non-visible:
            // todo Update to only reset tiles at the player's last position.
            for (int x = 0 ; x < tiles.length ; x++) {
                for (int y = 0 ; y < tiles[x].length ; y++) {
                    if (tiles[x][y].isVisible()) {
                        tiles[x][y].setVisible(false);
                        tiles[x][y].placeOnScreen(screen, x, y);
                    }
                }
            }

            // Set visible tiles:
            final LineOfSight los = creature.getLineOfSight();

            los.getLinePoints().forEach(line -> {
                for (final Point point : line) {
                    final Tile tile = tiles[point.x][point.y];
                    tile.setVisible(true);
                    tile.setVisited(true);
                    tile.placeOnScreen(screen, point.x, point.y);

                    if (tile.isSolid()) {
                        break;
                    }
                }
            });
        }
    }
}
