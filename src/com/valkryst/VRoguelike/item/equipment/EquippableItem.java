package com.valkryst.VRoguelike.item.equipment;

import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.item.builder.equipment.EquippableItemBuilder;
import lombok.Getter;

import java.util.Objects;

public class EquippableItem extends Item {
    /** The slot. */
    @Getter private final EquipmentSlot slot;

    /**
     * Constructs a new EquippableItem.
     *
     * @param builder
     *        The builder.
     */
    public EquippableItem(final EquippableItemBuilder builder) {
        super(builder);
        slot = builder.getSlot();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tSlot:\t" + slot.name();
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
