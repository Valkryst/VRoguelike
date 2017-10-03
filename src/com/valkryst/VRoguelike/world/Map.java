package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VTerminal.builder.component.ScreenBuilder;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.component.TextArea;
import lombok.Getter;
import lombok.NonNull;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map {
    /** The screen on which the map is drawn. */
    @Getter private Screen screen;

    /** The tiles. */
    @Getter private Tile[][] tiles;

    /** The player entity. */
    @Getter private Player player;

    /** The entity. */
    @Getter private List<Entity> entities = new ArrayList<>();

    /** The component to display messages within. */
    @Getter private TextArea messageBox;

    /**
     * Constructs a new Map.
     *
     * @param messageBox
     *        The component to display messages within.
     *
     * @param width
     *        The width of the map to create.
     *
     * @param height
     *        The height of the map to create.
     *
     * @throws NullPointerException
     *         If the messageBox is null.
     */
    public Map(final @NonNull TextArea messageBox, final int width, final int height) {
        this.screen = new ScreenBuilder(width, height).build();
        this.messageBox = messageBox;

        screen.setBackgroundColor(Color.BLACK);

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
     * @param position
     *        The x/y-axis values of the position.
     *
     * @return
     *        If the position is free or not.
     */
    public boolean isPositionFree(final Point position) {
        if (position.x < 0 || position.y < 0) {
            return false;
        }

        if (position.x > tiles.length || position.y > tiles[0].length) {
            return false;
        }

        return tiles[position.x][position.y].isSolid() == false;
    }

    /**
     * Retrieves all Entities at a position.
     *
     * @param position
     *        The x/y-axis value of the position.
     *
     * @return
     *        The Entities.
     */
    public List<Entity> getEntityAt(final Point position) {
        final List<Entity> entities = new ArrayList<>();

        for (final Entity entity : this.entities) {
            if (entity.getPosition().equals(position)) {
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
