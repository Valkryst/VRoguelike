package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VTerminal.misc.ShapeAlgorithms;
import lombok.Getter;
import lombok.NonNull;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class LineOfSight {
    @Getter private List<List<Point>> linePoints = new ArrayList<>();

    /** The radius. */
    @Getter private int radius = 4;

    /**
     * Constructs a new LineOfSight.
     *
     * @param entity
     *        The entity who owns the LOS.
     *
     * @param radius
     *        The radius.
     *
     * @throws NullPointerException
     *        If the entity is null.
     */
    public LineOfSight(final @NonNull Entity entity, final int radius) {
        if (radius < 1) {
            throw new IllegalArgumentException("The radius cannot be below one.");
        }

        this.radius = radius;
        recompute(entity);
    }

    /**
     * Recomputes the line points.
     *
     * @param entity
     *        The entity whose LOS is being recalculated.
     *
     * @throws NullPointerException
     *        If the entity is null.
     */
    public void recompute(final @NonNull Entity entity) {
        linePoints.clear();

        final Point position = entity.getPosition();

        for (final Point point : ShapeAlgorithms.getEllipse(position, new Dimension(radius, radius))) {
            linePoints.add(ShapeAlgorithms.getLine(position.x, position.y, point.x, point.y));
        }
    }

    public void move(final int dx, final int dy) {
        linePoints.forEach(list -> list.forEach(point -> {
            point.x += dx;
            point.y += dy;
        }));
    }
}
