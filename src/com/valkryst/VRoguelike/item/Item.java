package com.valkryst.VRoguelike.item;

import com.valkryst.VRoguelike.item.builder.ItemBuilder;
import com.valkryst.VRoguelike.stat.Statistic;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Item {
    /** The name. */
    @Getter @Setter private String name = "Item";

    /** The description.. */
    @Getter @Setter private String description = "This is an unnamed Item.";

    /** The health. */
    @Getter private final Statistic stat_health;
    /** The strength. */
    @Getter private final Statistic stat_strength;
    /** The defense. */
    @Getter private final Statistic stat_defense;
    /** The accuracy (Percent chance to land an attack). */
    @Getter private final Statistic stat_accuracy;
    /** The dodge (Percent chance to dodge an attack). */
    @Getter private final Statistic stat_dodge;

    /**
     * Constructs a new Item.
     *
     * @param builder
     *        The builder.
     */
    public Item(final ItemBuilder builder) {
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
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(":");
        sb.append("\n\tName:\t").append(name);
        sb.append("\n\tDescription:\t").append(description);
        sb.append("\n\tStatistics:");
        sb.append(stat_health.toString().replace("\n\t", "\n\t\t"));
        sb.append(stat_strength.toString().replace("\n\t", "\n\t\t"));
        sb.append(stat_defense.toString().replace("\n\t", "\n\t\t"));
        sb.append(stat_accuracy.toString().replace("\n\t", "\n\t\t"));
        sb.append(stat_dodge.toString().replace("\n\t", "\n\t\t"));

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, stat_health, stat_strength, stat_defense, stat_accuracy, stat_dodge);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Item == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final Item otherItm = (Item) otherObj;

        boolean isEqual = Objects.equals(name, otherItm.getName());
        isEqual &= Objects.equals(description, otherItm.getDescription());
        isEqual &= Objects.equals(stat_health, otherItm.getStat_health());
        isEqual &= Objects.equals(stat_strength, otherItm.getStat_strength());
        isEqual &= Objects.equals(stat_defense, otherItm.getStat_defense());
        isEqual &= Objects.equals(stat_accuracy, otherItm.getStat_accuracy());
        isEqual &= Objects.equals(stat_dodge, otherItm.getStat_dodge());
        return isEqual;
    }
}
