package com.valkryst.VRoguelike.item.equipment;

import com.valkryst.VRoguelike.item.builder.equipment.WeaponBuilder;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(callSuper=true)
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
