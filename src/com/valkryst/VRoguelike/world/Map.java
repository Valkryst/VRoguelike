package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.entities.Entity;
import com.valkryst.VRoguelike.entities.Tile;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Map {
    /** The tiles. */
    @Getter private Tile[][] tiles;

    /** The entities. */
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
