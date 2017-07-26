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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("EquippableItem:");
        sb.append("\n\tName:\t").append(super.getName());
        sb.append("\n\tDescription:\t").append(super.getDescription());
        sb.append("\n\tSlot:\t").append(slot.name());

        for (final Statistic statistic : super.getStatistics().values()) {
            final String temp = "\t" + statistic.toString();
            sb.append(temp.replace("\n\t", "\n\t\t"));
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hashCode(slot);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof EquippableItem == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final EquippableItem otherItm = (EquippableItem) otherObj;

        boolean isEqual = super.equals(otherObj);
        isEqual &= Objects.equals(slot, otherItm.getSlot());
        return isEqual;
    }
}
