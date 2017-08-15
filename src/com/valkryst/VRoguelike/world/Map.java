package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
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

    /** The player entity. */
    @Getter private Player player;

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
        if (player != null) {
            player.update(this);
        }

        entities.forEach(entity -> entity.update(this));
    }

    /**
     * Determines if a position is both on the map and not
     * solid.
     *
     * @param x
     *        The x-axis value of the position.
     *
     * @param y
     *        The y-axis value of the position.
     *
     * @return
     *        If the position is free or not.
     */
    public boolean isPositionFree(final int x, final int y) {
        if (x < 0 || y < 0) {
            return false;
        }

        if (x > tiles.length || y > tiles[0].length) {
            return false;
        }

        return tiles[x][y].isSolid() == false;
    }

    /**
     * Retrieves all Entities at a position.
     *
     * @param x
     *        The x-axis value of the position.
     *
     * @param y
     *        The y-axis value of the position.
     *
     * @return
     *        The Entities.
     */
    public List<Entity> getEntityAt(final int x, final int y) {
        final List<Entity> entities = new ArrayList<>();

        for (final Entity entity : this.entities) {
            boolean matches = entity.getX() == x;
            matches &= entity.getY() == y;

            if (matches) {
                entities.add(entity);
            }
        }

        return entities;
    }

    /**
     * Adds one or more entities to the map.
     *
     * @param entities
     *        The entities.
     */
    public void addEntities(final Entity ... entities) {
        for (int i = 0 ; i < entities.length ; i++) {
            screen.addComponent(entities[i].getLayer());


            if (entities[i] instanceof Player) {
                player = (Player) entities[i];
            } else {
                this.entities.add(entities[i]);
            }
        }
    }

    /**
     * Removes one or more entities from the map.
     *
     * @param entities
     *        The entities.
     */
    public void removeEntities(final Entity ... entities) {
        for (int i = 0 ; i < entities.length ; i++) {
            screen.removeComponent(entities[i].getLayer());

            if (entities[i] instanceof Player) {
                player = null;
            } else {
                this.entities.remove(entities[i]);
            }
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
