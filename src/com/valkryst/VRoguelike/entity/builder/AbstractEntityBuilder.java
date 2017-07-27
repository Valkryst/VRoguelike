package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.enums.Sprite;
import lombok.Getter;

import java.util.Objects;

public abstract class AbstractEntityBuilder<B extends AbstractEntityBuilder<B>> {
    /** The name. */
    @Getter private String name;
    /** The description. */
    @Getter private String description;
    /** The x-axis coordinate. */
    @Getter private int x;
    /** The y-axis coordinate. */
    @Getter private int y;
    /** The sprite. */
    @Getter private Sprite sprite;

    /** Constructs a new AbstractEntityBuilder. */
    public AbstractEntityBuilder() {
        reset();
    }

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

    /**
     * Sets the name.
     *
     * @param name
     *        The name.
     *
     * @return
     *        This.
     */
    public B setName(final String name) {
        this.name = name;
        return (B) this;
    }

    /**
     * Sets the description.
     *
     * @param description
     *        The description.
     *
     * @return
     *        This.
     */
    public B setDescription(final String description) {
        this.description = description;
        return (B) this;
    }

    /**
     * Sets the x-axis coordinate.
     *
     * @param x
     *        The x-axis coordinate.
     *
     * @return
     *        This.
     */
    public B setX(final int x) {
        this.x = x;
        return (B) this;
    }

    /**
     * Sets the y-axis coordinate.
     *
     * @param y
     *        The y-axis coordinate.
     *
     * @return
     *        This.
     */
    public B setY(final int y) {
        this.y = y;
        return (B) this;
    }

    /**
     * Sets the sprite.
     *
     * @param sprite
     *        The sprite.
     *
     * @return
     *        This.
     */
    public B setSprite(final Sprite sprite) {
        this.sprite = sprite;
        return (B) this;
    }
}
