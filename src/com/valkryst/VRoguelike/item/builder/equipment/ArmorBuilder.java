package com.valkryst.VRoguelike.item.builder.equipment;

import com.valkryst.VRoguelike.item.equipment.Armor;

public class ArmorBuilder extends EquippableItemBuilder {
    @Override
    public Armor build() {
        super.checkState();
        return new Armor(this);
    }
}
