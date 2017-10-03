package com.valkryst.VRoguelike.loot;

import com.valkryst.VRoguelike.item.Item;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode
public class LootEntry {
    /** The item. */
    @Getter @NonNull private final Item item;
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
        if (dropChance < 1) {
            throw new IllegalArgumentException("The drop chance cannot be below 1.");
        }

        if (dropChance > 100) {
            throw new IllegalArgumentException("The drop chance cannot be above 100.");
        }

        this.item = item;
        this.dropChance = dropChance;
    }

    @Override
    public String toString() {
        return "LootEntry:\n\tItem:" + item.toString().replace("\n\t", "\n\t\t")
                + "\n\tDrop Chance:" + dropChance;
    }

    /**
     * Determines whether or not the loot should be dropped.
     *
     * @return
     *        Whether or not the loot should be dropped.
     */
    public boolean drop() {
        return ThreadLocalRandom.current().nextInt(101) <= dropChance;
    }
}
