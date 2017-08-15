package com.valkryst.VRoguelike.loot;

import com.valkryst.VRoguelike.item.Item;
import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class LootEntry {
    /** The item. */
    @Getter private final Item item;
    /** The drop chance. */
    @Getter private final int dropChance;

    /**
     * Constructs a new LootEntry.
     *
     * @param item
     *        The item.
     *
     * @param dropChance
     *        The drop chance.
     */
    public LootEntry(final Item item, final int dropChance) {
        Objects.requireNonNull(item);

        if (dropChance < 1) {
            throw new IllegalArgumentException("The drop chance cannot be below 1.");
        }

        if (dropChance > 99) {
            throw new IllegalArgumentException("The drop chance cannot be above 99.");
        }

        this.item = item;
        this.dropChance = dropChance;
    }

    @Override
    public String toString() {
        return "LootEntry:\n\tItem:" + item.toString().replace("\n\t", "\n\t\t") + "\n\tDrop Chance:"
               + dropChance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, dropChance);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof LootEntry == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final LootEntry otherEntry = (LootEntry) otherObj;

        boolean isEqual = super.equals(otherObj);
        isEqual &= Objects.equals(item, otherEntry.getItem());
        isEqual &= Objects.equals(dropChance, otherEntry.getDropChance());
        return isEqual;
    }

    /**
     * Determines whether or not the loot should be
     * dropped.
     *
     * @return
     *        Whether or not the loot should be dropped.
     */
    public boolean drop() {
        return ThreadLocalRandom.current().nextInt(101) < dropChance;
    }
}
