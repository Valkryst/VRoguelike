package com.valkryst.VRoguelike.item;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.item.builder.ItemBuilder;
import com.valkryst.VRoguelike.stat.Statistic;
import lombok.Data;

@Data
public class Item {
    /** The sprite. */
    private Sprite sprite;

    /** The name. */
    private String name = "Item";

    /** The description.. */
    private String description = "This is an unnamed Item.";

    /** The health. */
    private final Statistic stat_health;
    /** The strength. */
    private final Statistic stat_strength;
    /** The defense. */
    private final Statistic stat_defense;
    /** The accuracy (Percent chance to land an attack). */
    private final Statistic stat_accuracy;
    /** The dodge (Percent chance to dodge an attack). */
    private final Statistic stat_dodge;

    /**
     * Constructs a new Item.
     *
     * @param builder
     *        The builder.
     */
    public Item(final ItemBuilder builder) {
        sprite = builder.getSprite();

        name = builder.getName();
        description = builder.getDescription();

        stat_health = new Statistic("Health", builder.getStat_health());
        stat_strength = new Statistic("Strength", builder.getStat_strength());
        stat_defense = new Statistic("Defense", builder.getStat_defense());
        stat_accuracy = new Statistic("Accuracy", builder.getStat_accuracy());
        stat_dodge = new Statistic("Dodge", builder.getStat_dodge());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" +
                "\n\tSprite:\t" + sprite +
                "\n\tName:\t" + name +
                "\n\tDescription:\t" + description +
                "\n\tStatistics:" +
                "\n\t" + stat_health.toString().replace("\n\t", "\n\t\t") +
                "\n\t" + stat_strength.toString().replace("\n\t", "\n\t\t") +
                "\n\t" + stat_defense.toString().replace("\n\t", "\n\t\t") +
                "\n\t" + stat_accuracy.toString().replace("\n\t", "\n\t\t") +
                "\n\t" + stat_dodge.toString().replace("\n\t", "\n\t\t");
    }
}
