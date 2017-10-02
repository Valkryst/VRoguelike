package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.enums.Sprite;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.Point;
import java.util.Objects;

@EqualsAndHashCode
@ToString
public class EntityBuilder {
    /** The name. */
    @Getter @Setter private String name;
    /** The description. */
    @Getter @Setter private String description;
    /** The x/y-axis coordinates. */
    @Getter @Setter private Point position;
    /** The sprite. */
    @Getter @Setter private Sprite sprite;

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
     *        If the name or description are empty.
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
}
