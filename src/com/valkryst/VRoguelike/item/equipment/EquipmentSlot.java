package com.valkryst.VRoguelike.item.equipment;

import org.apache.commons.lang3.text.WordUtils;

public enum EquipmentSlot {
    HEAD,
    NECK,
    BACK,
    SHOULDERS,
    CHEST,
    WRISTS,
    HANDS,
    WAIST,
    LEGS,
    FEET,
    MAIN_HAND,
    OFF_HAND;

    /**
     * Retrieves the name of the slot, but with the first letter
     * uppercased and all other characters lowercased. All
     * underscores are also converted to spaces.
     *
     * @return
     *         The name of the slot.
     */
    public String getName() {
        String name = this.name();
        name = name.toLowerCase();
        name = name.replace('_', ' ');
        name = WordUtils.capitalize(name);
        return name;
    }
}
