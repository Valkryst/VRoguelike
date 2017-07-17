package com.valkryst.VRoguelike.actions;

import com.valkryst.VRoguelike.entities.Entity;
import com.valkryst.VRoguelike.entities.Tile;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.component.Layer;


public class MoveAction implements Action {
    /** The change in x-axis position. */
    private final int dx;
    /** The change in y-axis position. */
    private final int dy;

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
