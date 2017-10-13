package com.valkryst.VRoguelike.item.builder;

import com.valkryst.VJSON.VJSONParser;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.item.Item;
import lombok.Data;
import lombok.NonNull;
import org.json.simple.JSONObject;

@Data
public class ItemBuilder implements VJSONParser {
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

    @Override
    public void parse(final @NonNull JSONObject jsonObject) {
        reset();

        final Sprite sprite = Sprite.valueOf((String) jsonObject.get("sprite"));

        final String name = (String) jsonObject.get("name");
        final String description = (String) jsonObject.get("description");

        final Integer stat_health = getInteger(jsonObject, "stat_health");
        final Integer stat_strength = getInteger(jsonObject, "stat_strength");
        final Integer stat_defense = getInteger(jsonObject, "stat_defense");
        final Integer stat_accuracy = getInteger(jsonObject, "stat_accuracy");
        final Integer stat_dodge = getInteger(jsonObject, "stat_dodge");

        if (sprite != null) {
            this.sprite = sprite;
        }


        if (name != null && name.isEmpty() == false) {
            this.name = name;
        }

        if (description != null && description.isEmpty() == false) {
            this.description = description;
        }


        if (stat_health != null) {
            this.stat_health = stat_health;
        }

        if (stat_strength != null) {
            this.stat_strength = stat_strength;
        }

        if (stat_defense != null) {
            this.stat_defense = stat_defense;
        }

        if (stat_accuracy != null) {
            this.stat_accuracy = stat_accuracy;
        }

        if (stat_dodge != null) {
            this.stat_dodge = stat_dodge;
        }
    }
}
