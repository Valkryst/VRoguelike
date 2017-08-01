package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import com.valkryst.VRoguelike.item.equipment.Weapon;
import com.valkryst.VRoguelike.world.Map;

import java.util.Objects;
import java.util.Optional;

public class AttackAction implements Action {
    /** The target. */
    private final Creature target;

    /**
     * Constructs a new AttackAction.
     *
     * @param target
     *        The target.
     */
    public AttackAction(final Creature target) {
        Objects.requireNonNull(target);

        this.target = target;
    }

    @Override
    public void perform(final Map map, final Entity entity) {
        final Creature self = (Creature) entity;

        int damage = 0;
        damage += getWeaponDamage(self, EquipmentSlot.MAIN_HAND);
        damage += getWeaponDamage(self, EquipmentSlot.OFF_HAND);
        damage += self.getStat_strength().getValue();
        damage -= target.getStat_defense().getValue();

        if (damage > 0) {
            final int curHealth = target.getStat_health().getValue();
            target.getStat_health().setValue(curHealth - damage);
        }
    }

    /**
     * Retrieves the attack damage of a weapon item.
     *
     * @param creature
     *        The creature.
     *
     * @param slot
     *        The slot.
     *
     * @return
     *        If there is no item in the specified slot, then
     *        zero is returned.
     *
     *        If the item in the specified slot is not a Weapon,
     *        then zero is returned.
     *
     *        If there is a weapon in the specified slot, then
     *        the weapon's attack damage is returned.
     */
    private static int getWeaponDamage(final Creature creature, final EquipmentSlot slot) {
        final Optional<EquippableItem> optItem = creature.getEquipment().getItemInSlot(slot);

        if (optItem.isPresent() && optItem.get() instanceof Weapon) {
            final Weapon weapon = (Weapon) optItem.get();
            return weapon.attack();
        } else {
            return 0;
        }
    }
}
