package com.valkryst.VRoguelike;

public class Distance {
    /**
     * Calculates the distance between two points using the
     * Manhattan formula.
     *
     * @param x1
     *        The x-axis position of the first point.
     *
     * @param y1
     *        The y-axis position of the first point.
     *
     * @param x2
     *        The x-axis position of the second point.
     *
     * @param y2
     *        The y-axis position of the second point.
     *
     * @return
     *        The distance.
     */
    public static int manhattan(final int x1, final int y1, final int x2, final int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /**
     * Calculates the distance between two points using the
     * Euclidean formula.
     *
     * @param x1
     *        The x-axis position of the first point.
     *
     * @param y1
     *        The y-axis position of the first point.
     *
     * @param x2
     *        The x-axis position of the second point.
     *
     * @param y2
     *        The y-axis position of the second point.
     *
     * @return
     *        The distance.
     */
    public static int euclidean(final int x1, final int y1, final int x2, final int y2) {
        int a = x1 - x2;
        a *= a;

        int b = y1 - y2;
        b *= b;

        return (int) Math.round(Math.sqrt(a + b));
    }

    /**
     * Calculates the distance between two points using the
     * Chebyshev formula.
     *
     * @param x1
     *        The x-axis position of the first point.
     *
     * @param y1
     *        The y-axis position of the first point.
     *
     * @param x2
     *        The x-axis position of the second point.
     *
     * @param y2
     *        The y-axis position of the second point.
     *
     * @return
     *        The distance.
     */
    public static int chebyshev(final int x1, final int y1, final int x2, final int y2) {
        return Math.max((x1 - x2), (y1 - y2));
    }
}
