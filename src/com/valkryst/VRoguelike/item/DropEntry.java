package com.valkryst.VRoguelike.item;

import lombok.Getter;

import java.util.Objects;

public class DropEntry {
    /** The drop chance. */
    @Getter private final double chance;
    /** The item to drop. */
    @Getter private final Item item;

    /**
     * Constructs a new DropEntry.
     *
     * @param chance
     *        The drop chance.
     *
     * @param item
     *        The item to drop.
     *
     * @throws NullPointerException
     *        If the item is null.
     *
     * @throws IllegalArgumentException
     *        If the drop chance is below 0.
     *        If the drop chance is above 100.
     */
    public DropEntry(final double chance, final Item item) {
        Objects.requireNonNull(item);

        if (chance < 0.0) {
            throw new IllegalArgumentException("The drop chance cannot be less than 0.");
        }

        if (chance > 100.0) {
            throw new IllegalArgumentException("The drop chance cannot be greater than 100.");
        }

        this.chance = chance;
        this.item = item;
    }
}
