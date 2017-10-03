package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.enums.Sprite;

import java.awt.Dimension;
import java.awt.Point;

public class Room {
    /** The x/y-axis coordinate of the top-left character of the room. */
    private final Point position;
    /** The width/height of the room. */
    private final Dimension dimensions;

    /**
     * Constructs a new Room.
     *
     * @param position
     *          The x/y-axis coordinates of the top-left character of the
     *          room.
     *
     * @param dimensions
     */
    public Room(final Point position, final Dimension dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    /**
     * Carves-out the room on the map.
     *
     * @param map
     *          The map.
     */
    public void carve(final Map map) {
        final Tile[][] tiles = map.getTiles();

        for (int x = position.x ; x < position.x + dimensions.width ; x++) {
            for (int y = position.y ; y < position.y + dimensions.height ; y++) {
                tiles[x][y].setSprite(Sprite.DIRT);
                tiles[x][y].setSolid(false);
            }
        }
    }
}
