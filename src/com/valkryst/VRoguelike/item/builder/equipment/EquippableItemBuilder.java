package com.valkryst.VRoguelike.item.builder.equipment;

import com.valkryst.VRoguelike.item.builder.ItemBuilder;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class EquippableItemBuilder extends ItemBuilder {
    /** The slot. */
    @Getter @Setter private EquipmentSlot slot;

    @Override
    public EquippableItem build() {
        checkState();
        return new EquippableItem(this);
    }

    @Override
    public void checkState() {
        super.checkState();
        Objects.requireNonNull(slot);
    }

    @Override
    public void reset() {
        super.reset();
        slot = null;
    }
}
