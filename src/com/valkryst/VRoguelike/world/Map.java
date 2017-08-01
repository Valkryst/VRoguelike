package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VTerminal.component.Screen;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map {
    /** The screen on which the map is drawn. */
    @Getter private Screen screen;

    /** The tiles. */
    @Getter private Tile[][] tiles;

    /** The entity. */
    @Getter private List<Entity> entities = new ArrayList<>();

    /**
     * Constructs a new Map.
     *
     * @param screen
     *        The screen on which the map is drawn.
     *
     * @param tiles
     *        The tiles.
     *
     * @throws NullPointerException
     *         If the screen or tiles are null.
     */
    public Map(final Screen screen, final Tile[][] tiles) {
        Objects.requireNonNull(screen);
        Objects.requireNonNull(tiles);

        this.screen = screen;
        this.tiles = tiles;
    }

    /**
     * Constructs a new Map.
     *
     * @param screen
     *        The screen on which the map is drawn.
     *
     * @param width
     *        The width of the map to create.
     *
     * @param height
     *        The height of the map to create.
     *
     * @throws NullPointerException
     *         If the screen is null.
     */
    public Map(final Screen screen, final int width, final int height) {
        this.screen = screen;

        tiles = new Tile[width][height];

        for (int x = 0 ; x < width ; x++) {
            for (int y = 0 ; y < height ; y++) {
                tiles[x][y] = new Tile();
            }
        }
    }

    /** Updates the map. */
    public void update() {
        entities.forEach(entity -> entity.update(this));
    }

    /**
     * Adds one or more entities to the map.
     *
     * @param entities
     *        The entities.
     */
    public void addEntity(final Entity ... entities) {
        for (int i = 0 ; i < entities.length ; i++) {
            screen.addComponent(entities[i].getLayer());
            this.entities.add(entities[i]);
        }
    }

    /**
     * Removes one or more entities from the map.
     *
     * @param entities
     *        The entities.
     */
    public void removeEntity(final Entity ... entities) {
        for (int i = 0 ; i < entities.length ; i++) {
            screen.removeComponent(entities[i].getLayer());
            this.entities.remove(entities[i]);
        }
    }

    /** @return The width, in tiles, of the map. */
    public int getWidth() {
        return tiles.length;
    }

    /** @return The height, in tiles, of the map. */
    public int getHeight() {
        return tiles[0].length;
    }
}
