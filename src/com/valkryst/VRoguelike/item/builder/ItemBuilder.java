package com.valkryst.VRoguelike.item.builder;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.item.Item;
import lombok.Data;

@Data
public class ItemBuilder {
    /** The sprite. */
    private Sprite sprite;

    /** The name. */
    private String name;
    /** The description.. */
    private String description;

    /** The health. */
    private int stat_health;
    /** The strength. */
    private int stat_strength;
    /** The defense. */
    private int stat_defense;
    /** The accuracy (Percent chance to land an attack). */
    private int stat_accuracy;
    /** The dodge (Percent chance to dodge an attack). */
    private int stat_dodge;

    /** Constructs a new ItemBuilder. */
    public ItemBuilder() {
        reset();
    }

    /** Constructs an Item. */
    public Item build() {
        checkState();
        return new Item(this);
    }

    /** Ensures the builder is in a valid state. */
    protected void checkState() {
        if (sprite == null) {
            sprite = Sprite.UNKNOWN;
        }

        if (name == null || name.isEmpty()) {
            name = "Item";
        }

        if (description == null || description.isEmpty()) {
            description = "This is an unnamed Item.";
        }
    }

    /** Resets the builder to it's default state. */
    public void reset() {
        sprite = Sprite.UNKNOWN;

        name = "Item";
        description = "This is an unnamed item.";

        stat_health = 0;
        stat_strength = 0;
        stat_defense = 0;
        stat_accuracy = 0;
        stat_dodge = 0;
    }
}