package com.valkryst.VRoguelike.item.equipment;

import com.valkryst.VRoguelike.item.builder.equipment.WeaponBuilder;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Weapon extends EquippableItem {
    /** The damage-range. */
    @Getter private final BoundedStatistic stat_damage;

    /**
     * Constructs a new Weapon.
     *
     * @param builder
     *        The builder.
     */
    public Weapon(final WeaponBuilder builder) {
        super(builder);
        stat_damage = builder.getStat_damage();
    }

    @Override
    public String toString() {
        return super.toString() + stat_damage.toString().replace("\n\t", "\n\t\t");
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hashCode(stat_damage);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Weapon == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final Weapon otherWep = (Weapon) otherObj;

        boolean isEqual = super.equals(otherObj);
        isEqual &= Objects.equals(stat_damage, otherWep.getStat_damage());
        return isEqual;
    }

    /**
     * Calculates a damage value for an attack.
     *
     * @return
     *        A damage value for an attack.
     */
    public int attack() {
        return ThreadLocalRandom.current().nextInt(stat_damage.getMaximum()) + stat_damage.getMinimum();
    }
}
