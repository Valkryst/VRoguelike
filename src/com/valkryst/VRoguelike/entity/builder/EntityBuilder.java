package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.enums.Sprite;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@EqualsAndHashCode
@ToString
public class EntityBuilder {
    /** The name. */
    @Getter @Setter private String name;
    /** The description. */
    @Getter @Setter private String description;
    /** The x-axis coordinate. */
    @Getter @Setter private int x;
    /** The y-axis coordinate. */
    @Getter @Setter private int y;
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

        if (x < 0) {
            throw new IllegalArgumentException("The x value (" + x + ") cannot be less than zero.");
        }

        if (y < 0) {
            throw new IllegalArgumentException("The y value (" + y + ") cannot be less than zero.");
        }

        Objects.requireNonNull(sprite);
    }

    /** Resets the builder to it's default state. */
    public void reset() {
        name = "";
        description = "";
        x = -1;
        y = -1;
        sprite = null;
    }
}
