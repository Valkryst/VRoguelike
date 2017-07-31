package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import com.valkryst.VRoguelike.item.equipment.Weapon;
import com.valkryst.VRoguelike.stat.Statistic;
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
    }

    public int getMeleeAttackDamage(final Creature creature) {
        final Optional<EquippableItem> optMainHand = creature.getEquipment().getItemInSlot(EquipmentSlot.MAIN_HAND);
        final Optional<EquippableItem> optOffHand = creature.getEquipment().getItemInSlot(EquipmentSlot.OFF_HAND);

        final Optional<Statistic> optStrength = creature.getStatistic("Strength");

        int damage = 0;

        if (optMainHand.isPresent() && optMainHand.get() instanceof Weapon) {
            final Weapon weapon = (Weapon) optMainHand.get();
            damage += weapon.attack();
        }

        if (optOffHand.isPresent() && optOffHand.get() instanceof Weapon) {
            final Weapon weapon = (Weapon) optOffHand.get();
            damage += weapon.attack();
        }

        if (optStrength.isPresent()) {
            damage += optStrength.get().getValue();
        }

        return damage;
    }
}
