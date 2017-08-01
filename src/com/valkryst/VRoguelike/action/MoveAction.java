package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;

import java.util.Objects;


public class MoveAction implements Action {
    /** The current position on the x-axis. */
    private final int x;
    /** The current position on the y-axis. */
    private final int y;
    /** The change in x-axis position. */
    private final int dx;
    /** The change in y-axis position. */
    private final int dy;

    /**
     * Constructs a new MoveAction.
     *
     * @param x
     *        The current position on the x-axis.
     *
     * @param y
     *        The current position on the y-axis.
     *
     * @param dx
     *        The change in x-axis position.
     *
     * @param dy
     *        The change in y-axis position.
     */
    public MoveAction(final int x, final int y, final int dx, final int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void perform(final Map map, final Entity entity) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(entity);

        if (map.isPositionFree(x + dx, y + dy)) {
            entity.getLayer().setColumnIndex(x + dy);
            entity.getLayer().setRowIndex(y + dy);
        }
    }
}
