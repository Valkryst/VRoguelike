package com.valkryst.VRoguelike.item.builder.equipment;

import com.valkryst.VRoguelike.item.builder.ItemBuilder;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper=true)
public class EquippableItemBuilder extends ItemBuilder {
    /** The slot. */
    private EquipmentSlot slot;

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
