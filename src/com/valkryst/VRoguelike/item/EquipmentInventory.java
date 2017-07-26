package com.valkryst.VRoguelike.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EquipmentInventory {
    /** The equipped equipment. */
    private final Map<EquipmentSlot, Item> equipment = new HashMap<>();

    /**
     * Retrieves the item in a specific slot.
     *
     * @param slot
     *        The slot.
     *
     * @return
     *        The item in the slot, or nothing if no item was found.
     */
    public Optional<Item> getItemInSlot(final EquipmentSlot slot) {
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
     */
    public void setItemInSlot(final EquipmentSlot slot, final Item item) {
        Objects.requireNonNull(slot);
        equipment.put(slot, item);
    }
}
