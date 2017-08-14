package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VTerminal.misc.ShapeAlgorithms;
import lombok.Getter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LineOfSight {
    @Getter private List<List<Point>> linePoints;

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
     */
    public LineOfSight(final Entity entity, final int radius) {
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
    public void recompute(final Entity entity) {
        Objects.requireNonNull(entity);

        linePoints = new ArrayList<>();
        for (final Point point : ShapeAlgorithms.getEllipse(entity.getX(), entity.getY(), radius, radius)) {
            linePoints.add(ShapeAlgorithms.getLine(entity.getX(), entity.getY(), point.x, point.y));
        }
    }

    public void move(final int dx, final int dy) {
        linePoints.forEach(list -> list.forEach(point -> {
            point.x += dx;
            point.y += dy;
        }));
    }
}
