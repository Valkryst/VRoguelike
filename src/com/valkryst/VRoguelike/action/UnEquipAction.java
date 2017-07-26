package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.item.EquipmentSlot;
import com.valkryst.VRoguelike.world.Map;
import lombok.Getter;

import java.util.Objects;

public class UnEquipAction implements Action {
    /** The slot to un-equip. */
    @Getter private final EquipmentSlot slot;

    /**
     * Constructs a new UnEquipAction.
     *
     * @param slot
     *        The slot to un-equip.
     *
     * @throws NullPointerException
     *        If the slot is null.
     */
    public UnEquipAction(final EquipmentSlot slot) {
        Objects.requireNonNull(slot);

        this.slot = slot;
    }

    @Override
    public void perform(final Map map, final Entity entity) {
        if (entity instanceof Creature == false) {
            return;
        }

        final Creature creature = (Creature) entity;
        creature.getEquipment().setItemInSlot(slot, null);
    }
}
