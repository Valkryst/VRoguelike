package com.valkryst.VRoguelike.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EquipmentInventory {
    /** The equipped equipment. */
    private final Map<EquipmentSlot, EquippableItem> equipment = new HashMap<>();

    /**
     * Retrieves the item in a specific slot.
     *
     * @param slot
     *        The slot.
     *
     * @return
     *        The item in the slot, or nothing if no item was found.
     */
    public Optional<EquippableItem> getItemInSlot(final EquipmentSlot slot) {
        Objects.requireNonNull(slot);
        return Optional.ofNullable(equipment.get(slot));
    }

    /**
     * Sets the item in a specific slot.
     *
     * @param slot
     *        The slot.
     *
     * @param item
     *        The item.
     *
     * @throws IllegalArgumentException
     *        If the slot to equip to, and the item's slot type, do
     *        not match.
     */
    public void setItemInSlot(final EquipmentSlot slot, final EquippableItem item) {
        Objects.requireNonNull(slot);

        if (item.getSlot() != slot) {
            throw new IllegalArgumentException("The slot to equip to, and the item's slot type, do not match.\n\t"
                                               + item.toString().replace("\n\t", "\n\t\t"));
        }

        equipment.put(slot, item);
    }
}
