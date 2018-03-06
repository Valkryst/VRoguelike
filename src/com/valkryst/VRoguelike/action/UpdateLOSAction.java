package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.LineOfSight;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VRoguelike.world.MapTile;
import com.valkryst.VTerminal.component.Layer;
import lombok.Data;
import lombok.NonNull;

import java.awt.Point;

@Data
public class UpdateLOSAction extends Action {
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
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        if (entity instanceof Creature == false) {
            return;
        }

        if (map.isPositionFree(new Point(position.x + dx, position.y + dy)) == false) {
            return;
        }

        final Layer layer = map;
        final MapTile[][] tiles = map.getTiles();
        final Creature creature = (Creature) entity;

        final LineOfSight lineOfSight = creature.getLineOfSight();
        lineOfSight.move(dx, dy);

        if (entity instanceof Player) {
            // Set all tiles to non-visible:
            final int sightRadius = lineOfSight.getRadius() + 2;

            final int startX = Math.max(position.x - sightRadius, 0);
            final int endX = Math.min(position.x + sightRadius, tiles.length);

            final int startY = Math.max(position.y - sightRadius, 0);
            final int endY = Math.min(position.y + sightRadius, tiles[0].length);

            for (int x = startX ; x < endX ; x++) {
                for (int y = startY ; y < endY ; y++) {
                    if (tiles[x][y].isVisible()) {
                        tiles[x][y].setVisible(false);
                        tiles[x][y].placeOnScreen(layer, x, y);
                    }
                }
            }

            // Set visible tiles:
            lineOfSight.getLinePoints().forEach(line -> {
                for (final Point point : line) {
                    final MapTile tile = tiles[point.x][point.y];
                    tile.setVisible(true);
                    tile.setVisited(true);
                    tile.placeOnScreen(layer, point.x, point.y);

                    if (tile.isSolid()) {
                        break;
                    }
                }
            });

            super.perform(map, entity);
        }
    }
}
