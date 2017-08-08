package com.valkryst.VRoguelike.item.builder;

import com.valkryst.VRoguelike.item.Item;
import lombok.Getter;
import lombok.Setter;

public class ItemBuilder {
    /** The name. */
    @Getter @Setter private String name;
    /** The description.. */
    @Getter @Setter private String description;

    /** The health. */
    @Getter private int stat_health;
    /** The strength. */
    @Getter private int stat_strength;
    /** The defense. */
    @Getter private int stat_defense;
    /** The accuracy (Percent chance to land an attack). */
    @Getter private int stat_accuracy;
    /** The dodge (Percent chance to dodge an attack). */
    @Getter private int stat_dodge;

    /** Constructs a new ItemBuilder. */
    public ItemBuilder() {
        reset();
    }

    /** Constructs an Item. */
    public Item build() {
        checkState();
        return new Item(this);
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
            name = "Item";
        }

        if (description == null || description.isEmpty()) {
            description = "This is an unnamed Item.";
        }
    }

    /** Resets the builder to it's default state. */
    public void reset() {
        name = "Item";
        description = "This is an unnamed item.";

        stat_health = 0;
        stat_strength = 0;
        stat_defense = 0;
        stat_accuracy = 0;
        stat_dodge = 0;
    }
}