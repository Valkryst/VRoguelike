package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.entity.Entity;
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
        for (final Point point : getSightCircle(entity.getX(), entity.getY())) {
            linePoints.add(getLinePoints(entity.getX(), entity.getY(), point.x, point.y));
        }
    }

    /**
     * Constructs a list of points containing every point along
     * the circumference of the sight circle.
     *
     * Uses the midpoint circle algorithm.
     *
     * @param xOrigin
     *        The x-axis coordinate of the center of the circle.
     *
     * @param yOrigin
     *        The y-axis coordinate of the center of the circle.
     *
     * @return
     *        A list of every point along the circumference of
     *        the sight circle.
     */
    private List<Point> getSightCircle(int xOrigin, int yOrigin) {
        final List<Point> points = new ArrayList<>();

        int a = radius * radius;
        int fa = 4 * a;

        // Draw first half of the ellipse:
        int sigma = (2 * a) + (a * (1 - (2 * radius)));
        int x = 0;
        int y = radius;

        while ((a * x) <= (a * y)) {
            points.add(new Point(xOrigin + x, yOrigin + y));
            points.add(new Point(xOrigin - x, yOrigin + y));
            points.add(new Point(xOrigin + x, yOrigin - y));
            points.add(new Point(xOrigin - x, yOrigin - y));

            if (sigma >= 0) {
                sigma += fa * (1 - y);
                y--;
            }

            sigma += a * ((4 * x) + 6);
            x++;
        }

        // Draw second half of the ellipse:
        sigma = (2 * a) + (a * (1 - (2 * radius)));
        x = radius;
        y = 0;

        while ((a * y) <= (a * x)) {
            points.add(new Point(xOrigin + x, yOrigin + y));
            points.add(new Point(xOrigin - x, yOrigin + y));
            points.add(new Point(xOrigin + x, yOrigin - y));
            points.add(new Point(xOrigin - x, yOrigin - y));

            if (sigma >= 0) {
                sigma += fa * (1 - x);
                x--;
            }

            sigma += a * ((4 * y) + 6);
            y++;
        }


        return points;
    }

    /**
     * Constructs a list of points containing every point along
     * a line between two points.
     *
     * Uses Bresenham's line algorithm.
     *
     *
     * @param fromX
     *         The x-axis (column) coordinate of the start point
     *         of the line.
     *
     * @param fromY
     *         The y-axis (row) coordinate of the start point
     *         of the line.
     *
     * @param toX
     *         The x-axis (column) coordinate of the end point
     *         of the line.
     *
     * @param toY
     *         The y-axis (row) coordinate of the end point
     *         of the line.
     *
     * @return
     *        A list of every point along the line.
     */
    private List<Point> getLinePoints(int fromX, int fromY, int toX, int toY) {
        final List<Point> points = new ArrayList<>();

        // delta of exact value and rounded value of the dependant variable
        int d = 0;

        int dy = Math.abs(toY - fromY);
        int dx = Math.abs(toX - fromX);

        int dy2 = (dy << 1); // slope scaling factors to avoid floating
        int dx2 = (dx << 1); // point

        int ix = fromX < toX ? 1 : -1; // increment direction
        int iy = fromY < toY ? 1 : -1;

        while (true) {
            points.add(new Point(fromX, fromY));

            if (dy <= dx) {
                if (fromX == toX) {
                    break;
                }

                fromX += ix;
                d += dy2;

                if (d > dx) {
                    fromY += iy;
                    d -= dx2;
                }
            } else {
                if (fromY == toY) {
                    break;
                }

                fromY += iy;
                d += dx2;

                if (d > dy) {
                    fromX += ix;
                    d -= dy2;
                }
            }
        }

        return points;
    }

    public void move(final int dx, final int dy) {
        linePoints.forEach(list -> list.forEach(point -> {
            point.x += dx;
            point.y += dy;
        }));
    }
}
