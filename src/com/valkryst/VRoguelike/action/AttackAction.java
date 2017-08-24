package com.valkryst.VRoguelike.action;

import com.valkryst.VDice.Die;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import com.valkryst.VRoguelike.item.equipment.Weapon;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.component.TextArea;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Optional;

@EqualsAndHashCode
@ToString
public class AttackAction implements Action {
    /** The target. */
    private final Creature target;

    /**
     * Constructs a new AttackAction.
     *
     * @param target
     *        The target.
     *
     * @throws NullPointerException
     *        If the target is null.
     */
    public AttackAction(final @NonNull Creature target) {
        this.target = target;
    }

    @Override
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        final TextArea messageBox = map.getScreen().getMessageBox();
        messageBox.appendText("");

        final Creature self = (Creature) entity;
        final BoundedStatistic health = target.getStat_health();

        if (doesAttackHitTarget(self)) {
            new DodgeAction().perform(map, target);
            new AttackMissAction().perform(map, self);
            messageBox.appendText(self.getName() + " missed " + target.getName());
            return;
        }



        final int damage = getDamageDealt(self, target);

        if (damage > 0) {
            health.setValue(health.getValue() - damage);
            messageBox.appendText(self.getName() + " attacked " + target.getName() + " for " + damage + " damage.");
            messageBox.appendText(target.getName() + "'s health is now " + health.getValue() + "/" + health.getMaximum());
        } else {
            messageBox.appendText(self.getName() + " dealt no damage to " + target.getName() + ".");
        }

        if (health.getValue() == health.getMinimum()) {
            new DeathAction().perform(map, target);
        } else {
            target.getCombatAI().decide(map, target, self);
        }
    }

    /**
     * Determines if an attack will hit the target.
     *
     * @param self
     *        The attacking creature.
     *
     * @return
     *        If the attack lands.
     *
     * @throws NullPointerException
     *        If the creature is null.
     */
    private boolean doesAttackHitTarget(final @NonNull Creature self) {
        final int randomVal = new Die(100).roll();
        final int hitVal = self.getStat_accuracy().getValue() - target.getStat_dodge().getValue();

        return randomVal < hitVal;
    }

    /**
     * Calculates the damage to deal.
     *
     * @param self
     *        The attacking creature.
     *
     * @param target
     *        The creature being attacked.
     *
     * @return
     *        The damage dealt.
     */
    private static int getDamageDealt(final Creature self, final Creature target) {
        int damage = 0;
        damage += getWeaponDamage(self, EquipmentSlot.MAIN_HAND);
        damage += getWeaponDamage(self, EquipmentSlot.OFF_HAND);
        damage += self.getStat_strength().getValue();
        damage -= target.getStat_defense().getValue();

        return damage;
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
     *
     * @throws NullPointerException
     *        If the creature or slot is null.
     */
    private static int getWeaponDamage(final @NonNull Creature creature, final @NonNull EquipmentSlot slot) {
        final Optional<EquippableItem> optItem = creature.getEquipment().getItemInSlot(slot);

        if (optItem.isPresent() && optItem.get() instanceof Weapon) {
            final Weapon weapon = (Weapon) optItem.get();
            return weapon.attack();
        } else {
            return 0;
        }
    }
}
