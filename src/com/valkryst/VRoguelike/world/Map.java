package com.valkryst.VRoguelike.world;

import com.valkryst.VJSON.VJSONParser;
import com.valkryst.VRoguelike.Message;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.entity.builder.CreatureBuilder;
import com.valkryst.VRoguelike.loot.LootTable;
import com.valkryst.VRoguelike.loot.builder.LootTableBuilder;
import com.valkryst.VTerminal.builder.component.ScreenBuilder;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.component.TextArea;
import lombok.Getter;
import lombok.NonNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map extends Screen implements VJSONParser {
    /** The tiles. */
    @Getter private Tile[][] tiles;

    /** The player entity. */
    @Getter private Player player;

    /** The entity. */
    @Getter private List<Entity> entities = new ArrayList<>();

    /** The component to display messages within. */
    private TextArea messageBox;

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
        super(new ScreenBuilder(width, height));
        this.setBackgroundColor(Color.BLACK);

        this.messageBox = messageBox;

        tiles = new Tile[width][height];

        for (int x = 0 ; x < width ; x++) {
            for (int y = 0 ; y < height ; y++) {
                tiles[x][y] = new Tile();
            }
        }
    }

    @Override
    public void parse(final @NonNull JSONObject jsonObject) {
        final JSONArray jsonObject_rooms = (JSONArray) jsonObject.get("rooms");
        final JSONArray jsonObject_lootTables = (JSONArray) jsonObject.get("loot_tables");
        final JSONArray jsonObject_entities = (JSONArray) jsonObject.get("entities");

        final LootTableBuilder lootTableBuilder = new LootTableBuilder();
        final CreatureBuilder creatureBuilder = new CreatureBuilder();

        final HashMap<String, LootTable> lootTables = new HashMap<>();

        jsonObject_rooms.forEach(roomJSON -> {
            final Room room = new Room();
            room.parse((JSONObject) roomJSON);
            room.carve(this);
        });

        jsonObject_lootTables.forEach(tableJSON -> {
            final String name = getString((JSONObject) tableJSON, "name");
            lootTableBuilder.parse((JSONObject) ((JSONObject) tableJSON).get("loot"));

            lootTables.put(name, lootTableBuilder.build());
        });

        jsonObject_entities.forEach(entityJSON -> {
            switch (getString((JSONObject) entityJSON, "type")) {
                case "entity_creature": {
                    final String lootTableName = getString((JSONObject) entityJSON, "loot_table_name");
                    creatureBuilder.parse((JSONObject) entityJSON);
                    creatureBuilder.setLootTable(lootTables.get(lootTableName));

                    addEntities(creatureBuilder.build());
                    break;
                }
            }
        });
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

        if (position.x >= tiles.length || position.y >= tiles[0].length) {
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
        for (final Entity entity : entities) {
            this.addComponent(entity.getLayer());


            if (entity instanceof Player) {
                player = (Player) entity;
            } else {
                this.entities.add(entity);
            }

            // Ensure player is drawn above everything else:
            if (player != null) {
                this.changeDrawOrder(entity.getLayer(), player.getLayer());
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
        for (final Entity entity : entities) {
            this.removeComponent(entity.getLayer());

            if (entity instanceof Player) {
                player = null;
            } else {
                this.entities.remove(entity);
            }
        }
    }

    /**
     * Adds a message to the message box.
     *
     * @param message
     *           The message.
     *
     * @throws NullPointerException
     *           If the message is null.
     */
    public void addMessage(final @NonNull Message message) {
        messageBox.appendText(message.getMessage());
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
