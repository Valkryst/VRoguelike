package com.valkryst.VRoguelike.world;

import com.valkryst.VJSON.VJSONParser;
import com.valkryst.VRoguelike.enums.Sprite;
import lombok.NonNull;
import org.json.simple.JSONObject;

import java.awt.Dimension;
import java.awt.Point;

public class Room implements VJSONParser {
    /** The x/y-axis coordinate of the top-left character of the room. */
    private final Point position;
    /** The width/height of the room. */
    private final Dimension dimensions;

    /** Constructs a new Room. */
    public Room() {
        this.position = new Point(0, 0);
        this.dimensions = new Dimension(0, 0);
    }

    /**
     * Constructs a new Room.
     *
     * @param position
     *          The x/y-axis coordinates of the top-left character of the
     *          room.
     *
     * @param dimensions
     *          The width/height of the room.
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

    @Override
    public void parse(final @NonNull JSONObject jsonObject) {
        this.checkType(jsonObject, "room");

        final Integer x = getInteger(jsonObject, "x");
        final Integer y = getInteger(jsonObject, "y");

        final Integer width = getInteger(jsonObject, "width");
        final Integer height = getInteger(jsonObject, "height");

        if (x != null && y != null) {
            this.position.setLocation(x, y);
        }

        if (width != null && height != null) {
            this.dimensions.setSize(width, height);
        }
    }
}
