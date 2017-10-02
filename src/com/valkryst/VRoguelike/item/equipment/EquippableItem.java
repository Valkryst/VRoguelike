package com.valkryst.VRoguelike.item.equipment;

import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.item.builder.equipment.EquippableItemBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper=true)
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
}
