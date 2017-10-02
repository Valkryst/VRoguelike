package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.world.Map;
import lombok.*;

@Data
public class UnEquipAction implements Action {
    /** The slot to un-equip. */
    private final EquipmentSlot slot;

    /**
     * Constructs a new UnEquipAction.
     *
     * @param slot
     *        The slot to un-equip.
     *
     * @throws NullPointerException
     *        If the slot is null.
     */
    public UnEquipAction(final @NonNull EquipmentSlot slot) {
        this.slot = slot;
    }

    @Override
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        if (entity instanceof Creature == false) {
            return;
        }

        final Creature creature = (Creature) entity;
        creature.getEquipment().setItemInSlot(slot, null);
    }
}
