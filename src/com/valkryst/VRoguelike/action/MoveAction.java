package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Tile;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.component.Layer;
import lombok.Getter;

import java.util.Objects;


public class MoveAction implements Action {
    /** The change in x-axis position. */
    @Getter private final int dx;
    /** The change in y-axis position. */
    @Getter private final int dy;

    /**
     * Constructs a new MoveAction.
     *
     * @param dx
     *        The change in x-axis position.
     *
     * @param dy
     *        The change in y-axis position.
     */
    public MoveAction(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void perform(final Map map, final Entity entity) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(entity);

        // Get curr/new position:
        final Layer layer = entity.getLayer();
        final int curX = layer.getColumnIndex();
        final int curY = layer.getRowIndex();

        final int newX = curX + dx;
        final int newY = curY + dy;

        // Move to new position, if possible:
        final Tile tile = map.getTiles()[newX][newY];

        if (tile.isSolid() == false) {
            layer.setColumnIndex(newX);
            layer.setRowIndex(newY);
        }
    }
}
