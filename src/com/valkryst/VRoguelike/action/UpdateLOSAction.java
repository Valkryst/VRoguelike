package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.LineOfSight;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VRoguelike.world.Tile;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.component.TextArea;
import lombok.Data;
import lombok.NonNull;

import java.awt.Point;

@Data
public class UpdateLOSAction implements Action {
    /** The current position on the x/y-axis. */
    private final Point position;
    /** The change in x-axis position. */
    private final int dx;
    /** The change in y-axis position. */
    private final int dy;

    /**
     * Constructs a new UpdateLOSPosition.
     *
     * @param position
     *        The current position on the x/y-axis.
     *
     * @param dx
     *        The change in x-axis position.
     *
     * @param dy
     *        The change in y-axis position.
     */
    public UpdateLOSAction(final Point position, final int dx, final int dy) {
        this.position = position;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void perform(final @NonNull Map map, final @NonNull TextArea messageBox, final @NonNull Entity entity) {
        if (entity instanceof Creature == false) {
            return;
        }

        if (map.isPositionFree(new Point(position.x + dx, position.y + dy)) == false) {
            return;
        }

        final Screen screen = map.getScreen();
        final Tile[][] tiles = map.getTiles();
        final Creature creature = (Creature) entity;

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
