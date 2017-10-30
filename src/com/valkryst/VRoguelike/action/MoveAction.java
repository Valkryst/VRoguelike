package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.awt.Point;
import java.util.List;

@EqualsAndHashCode
@ToString
public class MoveAction extends Action {
    /** The current position on the x/y-axis. */
    private final Point position;
    /** The change in x-axis position. */
    private final int dx;
    /** The change in y-axis position. */
    private final int dy;

    /**
     * Constructs a new MoveAction.
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
    public MoveAction(final Point position, final int dx, final int dy) {
        this.position = position;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        final Point newPosition = new Point(position.x + dx, position.y + dy);

        // Attack any enemies at new location:
        final List<Entity> entities = map.getEntityAt(newPosition);

        if (entities.size() >= 1) {
            for (final Entity target : entities) {
                if (target instanceof Creature) {
                    new AttackAction((Creature) target).perform(map, entity);
                    return;
                }
            }
        }

        if (map.isPositionFree(newPosition)) {
            super.perform(map, entity);
            new UpdateLOSAction(position, dx, dy).perform(map, entity);
            entity.setPosition(newPosition);
        }
    }
}
