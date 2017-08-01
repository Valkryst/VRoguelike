package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.entity.Entity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Map {
    /** The tiles. */
    @Getter private Tile[][] tiles;

    /** The entity. */
    @Getter private List<Entity> entities = new ArrayList<>();

    /**
     * Constructs a new Map.
     *
     * @param tiles
     *        The tiles.
     */
    public Map(final Tile[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * Constructs a new Map.
     *
     * @param width
     *        The width of the map to create.
     *
     * @param height
     *        The height of the map to create.
     */
    public Map(final int width, final int height) {
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
     * Adds an entity to the map.
     *
     * @param entity
     *        The entity.
     */
    public void addEntity(final Entity entity) {
        entities.add(entity);
    }
}
