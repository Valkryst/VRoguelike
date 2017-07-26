package com.valkryst.VRoguelike.item;

import lombok.Getter;

import java.util.Objects;

public class DropEntry {
    /** The drop set. */
    @Getter private final int set;
    /** The item to drop. */
    @Getter private final Item item;

    /**
     * Constructs a new DropEntry.
     *
     * @param set
     *        The drop set.
     *
     * @param item
     *        The item to drop.
     *
     * @throws NullPointerException
     *        If the item is null.
     */
    public DropEntry(final int set, final Item item) {
        Objects.requireNonNull(item);

        this.set = set;
        this.item = item;
    }
}
