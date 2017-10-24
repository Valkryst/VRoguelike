package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VJSON.VJSONParser;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.enums.Sprite;
import lombok.Data;
import lombok.NonNull;
import org.json.simple.JSONObject;

import java.awt.Point;
import java.util.Objects;

@Data
public class EntityBuilder implements VJSONParser {
    /** The name. */
    private String name;
    /** The description. */
    private String description;
    /** The x/y-axis coordinates. */
    private Point position = new Point(-1, -1);
    /** The sprite. */
    private Sprite sprite;

    /** Constructs a new EntityBuilder. */
    public EntityBuilder() {
        reset();
    }

    /** Constructs an Entity. */
    public Entity build() {
        checkState();
        return new Entity(this);
    }

    /**
     * Ensures the builder is in a valid state.
     *
     * @throws NullPointerException
     *        If the name or description are null.
     *
     * @throws IllegalArgumentException
     *        If the x/y positions are below zero.
     */
    protected void checkState() {
        if (name == null || name.isEmpty()) {
            name = "Entity";
        }

        if (description == null || description.isEmpty()) {
            description = "This is an unnamed Entity.";
        }

        if (position.x < 0) {
            throw new IllegalArgumentException("The x value (" + position.x + ") cannot be less than zero.");
        }

        if (position.y < 0) {
            throw new IllegalArgumentException("The y value (" + position.y + ") cannot be less than zero.");
        }

        Objects.requireNonNull(sprite);
    }

    /** Resets the builder to it's default state. */
    public void reset() {
        name = "";
        description = "";
        position.setLocation(-1, -1);
        sprite = null;
    }

    public void setX(final int x) {
        if (x < 0) {
            throw new IllegalArgumentException("The x value (" + position.x + ") cannot be less than zero.");
        }

        position.setLocation(x, position.y);
    }

    public void setY(final int y) {
        if (y < 0) {
            throw new IllegalArgumentException("The y value (" + position.y + ") cannot be less than zero.");
        }

        position.setLocation(position.x, y);
    }
    @Override
    public void parse(final @NonNull JSONObject jsonObject) {
        reset();

        try {
            this.checkType(jsonObject, "entity");
        } catch (final IllegalStateException ea) {
            try {
                this.checkType(jsonObject, "entity_creature");
            } catch (final IllegalStateException eb) {
                this.checkType(jsonObject, "entity_player");
            }
        }

        final String name = (String) jsonObject.get("name");
        final String description = (String) jsonObject.get("description");

        final Integer x = getInteger(jsonObject, "x");
        final Integer y = getInteger(jsonObject, "y");

        final Sprite sprite = Sprite.valueOf((String) jsonObject.get("sprite"));


        if (name != null && name.isEmpty() == false) {
            this.name = name;
        }

        if (description != null && description.isEmpty() == false) {
            this.description = description;
        }



        if (x != null && y != null) {
            this.position = new Point(x, y);
        }



        if (sprite != null) {
            this.sprite = sprite;
        }
    }
}
