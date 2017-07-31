package com.valkryst.VRoguelike.item.equipment;

import com.valkryst.VRoguelike.stat.Statistic;

import java.util.List;

public class Armor extends EquippableItem {
    /**
     * Constructs a new Armor.
     *
     * @param name
     *        The name.
     *
     * @param description
     *        The description.
     *
     * @param slot
     *        The slot.
     */
    public Armor(final String name, final String description, final EquipmentSlot slot) {
        super(name, description, slot);
    }

    /**
     * Constructs a new Armor.
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
     */
    public Armor(final String name, final String description, final List<Statistic> statistics, final EquipmentSlot slot) {
        super(name, description, statistics, slot);
    }
}
