package com.valkryst.VRoguelike.ai.movement;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import lombok.NonNull;

import java.awt.Point;
import java.util.ArrayDeque;

public abstract class MovementAI {
    /** The current path being followed. */
    private ArrayDeque<Point> currentPath = new ArrayDeque<>();

    /**
     * Moves the entity to the next position along the
     * current path being followed.
     *
     * If there are no more points along the current
     * path, then nothing happens.
     *
     * @param entity
     *          The entity to move.
     */
    public void move(final @NonNull Entity entity) {
        if (currentPath.size() == 0) {
            return;
        }

        final Point currentPosition = entity.getPosition();
        final Point newPosition = currentPath.removeFirst();

        int dx = 0;
        int dy = 0;

        // Calculate DX
        if (currentPosition.x > newPosition.x) {
            dx = currentPosition.x - newPosition.x;
        } else if (currentPosition.x < newPosition.x) {
            dx = newPosition.x - currentPosition.x;
        }

        // Calculate DY
        if (currentPosition.y > newPosition.y) {
            dy = currentPosition.y - newPosition.y;
        } else if (currentPosition.y < newPosition.y) {
            dy = newPosition.y - currentPosition.y;
        }

        entity.move(dx, dy);
    }

    /**
     * Determines a path to take, in order to move from the
     * start position to the end position.
     *
     * @param map
     *          The map.
     *
     * @param start
     *          The start position.
     *
     * @param end
     *          The end position.
     *
     * @return
     *          A path of points to follow, in order to reach
     *          the end position.
     *
     *          If no path could be found, then an empty set
     *          of points is returned.
     *
     * @throws NullPointerException
     *          If the map, start point, or end point is null.
     */
    public abstract ArrayDeque<Point> findPath(final @NonNull Map map, final @NonNull Point start, final @NonNull Point end);

    /**
     * Retrieves the four positions neighbouring a position.
     *
     * Returned positions may be null if they are not positions
     * contained within the map.
     *
     * @param map
     *          The map.
     *
     * @param position
     *          The center position.
     *
     * @return
     *          The top, bottom, left, and right positions
     *          neighbouring the center position.
     *
     * @throws NullPointerException
     *          If the map or position is null.
     */
    protected Point[] getNeighbours(final @NonNull Map map, final @NonNull Point position) {
        final Point top = getNeighbour(map, position.x, position.y - 1);
        final Point bottom = getNeighbour(map, position.x, position.y + 1);
        final Point left = getNeighbour(map, position.x - 1, position.y);
        final Point right = getNeighbour(map, position.x + 1, position.y);

        return new Point[] {top, bottom, left, right};
    }

    /**
     * Retrieves a neighbour position.
     *
     * @param map
     *          The map.
     *
     * @param x
     *          The x-axis coordinate of the neighbour.
     *
     * @param y
     *          The y-axis coordinate of the neighbour.
     *
     * @return
     *          Either the neighbour position, or null if the
     *          position is invalid and is not contained within
     *          the map.
     *
     * @throws NullPointerException
     *          If the map is null.
     */
    protected Point getNeighbour(final @NonNull Map map, final int x, final int y) {
        final Point neighbour = new Point(x, y);

        if (isValidPosition(map, neighbour)) {
            return neighbour;
        } else {
            return null;
        }
    }

    /**
     * Determines if a position is contained within the map.
     *
     * @param map
     *          The map.
     *
     * @param position
     *          The position.
     *
     * @return
     *          Whether or not the position is contained within
     *          the map.
     *
     * @throws NullPointerException
     *          If the map or position is null.
     */
    protected boolean isValidPosition(final @NonNull Map map, final @NonNull Point position) {
        if (position.x < 0 || position.x > map.getWidth()) {
            return false;
        }

        if (position.y < 0 || position.y > map.getHeight()) {
            return false;
        }

        return true;
    }

    /**
     * Calculates the distance between two points using the
     * Manhattan formula.
     *
     * @param start
     *        The x/y-axis position of the start point.
     *
     * @param end
     *        The x/y-axis position of the end point.
     *
     * @return
     *        The distance.
     *
     * @throws NullPointerException
     *          If the start or end position is null.
     */
    public static int manhattan(final @NonNull Point start, final @NonNull Point end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    /**
     * Calculates the distance between two points using the
     * Euclidean formula.
     *
     * @param start
     *        The x/y-axis position of the start point.
     *
     * @param end
     *        The x/y-axis position of the end point.
     *
     * @return
     *        The distance.
     *
     * @throws NullPointerException
     *          If the start or end position is null.
     */
    public static int euclidean(final @NonNull Point start, final @NonNull Point end) {
        int a = start.x - end.x;
        a *= a;

        int b = start.y - end.y;
        b *= b;

        return (int) Math.round(Math.sqrt(a + b));
    }

    /**
     * Calculates the distance between two points using the
     * Chebyshev formula.
     *
     * @param start
     *        The x/y-axis position of the start point.
     *
     * @param end
     *        The x/y-axis position of the end point.
     *
     * @return
     *        The distance.
     *
     * @throws NullPointerException
     *          If the start or end position is null.
     */
    public static int chebyshev(final @NonNull Point start, final @NonNull Point end) {
        return Math.max((start.x - end.x), (start.y - end.y));
    }
}
