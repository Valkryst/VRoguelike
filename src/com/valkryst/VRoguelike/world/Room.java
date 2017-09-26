package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.screen.GameScreen;

import java.awt.Point;

public class Room {
    private final Point position;
    private int width;
    private int height;

    public Room(final int columnIndex, final int rowIndex, final int width, final int height) {
        this(new Point(columnIndex, rowIndex), width, height);
    }

    public Room(final Point position, final int width, final int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public void carve(final GameScreen screen) {
        final Map map = screen.getMap();
        final Tile[][] tiles = map.getTiles();

        for (int x = position.x ; x < position.x + width ; x++) {
            for (int y = position.y ; y < position.y + height ; y++) {
                tiles[x][y].setSprite(Sprite.DIRT);
                tiles[x][y].setSolid(false);
            }
        }
    }
}
