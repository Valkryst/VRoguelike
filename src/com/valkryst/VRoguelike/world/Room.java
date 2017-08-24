package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.screen.GameScreen;

public class Room {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Room(final int x, final int y, final int width, final int height) {
        if (x < 0) {
            throw new IllegalArgumentException("The x-axis position cannot be less than zero.");
        }

        if (y < 0) {
            throw new IllegalArgumentException("The y-axis position cannot be less than zero.");
        }

        if (width < 1) {
            throw new IllegalArgumentException("The width cannot be less than one.");
        }

        if (height < 1) {
            throw new IllegalArgumentException("The height cannot be less than one.");
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void carve(final GameScreen gameScreen) {
        final Tile[][] tiles = gameScreen.getMap().getTiles();

        for (int x = this.x ; x < width + this.x ; x++) {
            for (int y = this.y ; y < height +  this.y ; y++) {
                tiles[x][y].setSprite(Sprite.DIRT);
                tiles[x][y].setSolid(false);
                tiles[x][y].setTransparent(false);
                tiles[x][y].placeOnScreen(gameScreen, x, y);
            }
        }
    }
}
