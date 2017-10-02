package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.screen.GameScreen;

import java.awt.Dimension;
import java.awt.Point;

public class Room {
    private final Point position;
    final Dimension dimensions;

    public Room(final Point position, final Dimension dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    public void carve(final GameScreen screen) {
        final Map map = screen.getMap();
        final Tile[][] tiles = map.getTiles();

        for (int x = position.x ; x < position.x + dimensions.width ; x++) {
            for (int y = position.y ; y < position.y + dimensions.height ; y++) {
                tiles[x][y].setSprite(Sprite.DIRT);
                tiles[x][y].setSolid(false);
            }
        }
    }
}
