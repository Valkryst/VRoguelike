package com.valkryst.VRoguelike.loot;

import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.loot.builder.LootEntryBuilder;
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
     * @param builder
     *         The builder.
     *
     * @throws NullPointerException
     *         If the builder is null.
     */
    public LootEntry(final @NonNull LootEntryBuilder builder) {
        this.item = builder.getItem();
        this.dropChance = builder.getDropChance();
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
