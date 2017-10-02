package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.component.TextArea;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
public class EquipAction implements Action {
    /** The slot to equip the item to. */
    @Getter private final EquipmentSlot slot;
    /** The item being equipped. */
    @Getter private final EquippableItem item;

    /**
     * Constructs a new EquipAction.
     *
     * @param slot
     *        The slot to equip the item to.
     *
     * @param item
     *        The item being equipped.
     *
     * @throws NullPointerException
     *        If the slot or item are null.
     */
    public EquipAction(final @NonNull EquipmentSlot slot, final @NonNull EquippableItem item) {
        this.slot = slot;
        this.item = item;
    }

    @Override
    public void perform(final @NonNull Map map, final @NonNull TextArea messageBox, final @NonNull Entity entity) {
        if (entity instanceof Creature == false) {
            return;
        }

        final Creature creature = (Creature) entity;
        creature.getEquipment().setItemInSlot(slot, item);
    }
}
