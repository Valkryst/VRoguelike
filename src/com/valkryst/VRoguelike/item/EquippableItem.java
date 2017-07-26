package com.valkryst.VRoguelike.item;

import com.valkryst.VRoguelike.stat.Statistic;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

public class EquippableItem extends Item {
    /** The slot. */
    @Getter private final ItemSlot slot;

    /**
     * Constructs a new EquippableItem.
     *
     * @param name
     *        The name.
     *
     * @param description
     *        The description.
     *
     * @param slot
     *        The slot.
     *
     * @throws NullPointerException
     *        If the slot is null.
     */
    public EquippableItem(final String name, final String description, final ItemSlot slot) {
        super(name, description);

        Objects.requireNonNull(slot);
        this.slot = slot;
    }

    /**
     * Constructs a new EquippableItem.
     *
     * @param name
     *        The name.
     *
     * @param description
     *        The description.
     *
     * @param statistics
     *        The statistics.
     *
     * @param slot
     *        The slot.
     *
     * @throws NullPointerException
     *        If the slot is null.
     */
    public EquippableItem(final String name, final String description, final List<Statistic> statistics, final ItemSlot slot) {
        super(name, description, statistics);

        Objects.requireNonNull(slot);
        this.slot = slot;
    }
}
