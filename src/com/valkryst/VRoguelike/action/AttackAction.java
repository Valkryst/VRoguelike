package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import com.valkryst.VRoguelike.item.equipment.Weapon;
import com.valkryst.VRoguelike.stat.LimitedStatistic;
import com.valkryst.VRoguelike.world.Map;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

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
        final LimitedStatistic health = target.getStat_health();

        if (doesAttackHitTarget(self)) {
            new DodgeAction().perform(map, target);
            new AttackMissAction().perform(map, self);
            System.out.println(self.getName() + " missed " + target.getName() + "\n");
            return;
        }

        int damage = 0;
        damage += getWeaponDamage(self, EquipmentSlot.MAIN_HAND);
        damage += getWeaponDamage(self, EquipmentSlot.OFF_HAND);
        damage += self.getStat_strength().getValue();
        damage -= target.getStat_defense().getValue();

        if (damage > 0) {
            health.setValue(health.getValue() - damage);
            System.out.println(self.getName() + " attacked " + target.getName() + " for " + damage + " damage.");
            System.out.println(target.getName() + "'s health is now " + health.getValue() + "/" + health.getMaximum());
        } else {
            System.out.println(self.getName() + " dealt no damage to " + target.getName() + ".");
        }

        if (health.getValue() == health.getMinimum()) {
            new DeathAction().perform(map, target);
            System.out.println(target.getName() + " has died.");
        } else {
            target.getCombatAI().decide(map, target, self);
        }

        System.out.println();
    }

    /**
     * Determines if an attack will hit the target.
     *
     * @param self
     *        The attacking creature.
     *
     * @return
     *        If the attack lands.
     */
    private boolean doesAttackHitTarget(final Creature self) {
        final int randomVal = ThreadLocalRandom.current().nextInt(0, 100);
        final int hitVal = self.getStat_accuracy().getValue() - target.getStat_dodge().getValue();

        return randomVal < hitVal;
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
