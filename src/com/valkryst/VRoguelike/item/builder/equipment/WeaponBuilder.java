package com.valkryst.VRoguelike.item.builder.equipment;

import com.valkryst.VRoguelike.item.equipment.Weapon;
import com.valkryst.VRoguelike.stat.LimitedStatistic;
import lombok.Getter;

import java.util.Objects;

public class WeaponBuilder extends EquippableItemBuilder {
    /** The damage-range. */
    @Getter private LimitedStatistic stat_damage;

    @Override
    public Weapon build() {
        super.checkState();
        return new Weapon(this);
    }

    @Override
    public void checkState() {
        super.checkState();
        Objects.requireNonNull(stat_damage);
    }

    @Override
    public void reset() {
        super.reset();
        stat_damage = new LimitedStatistic("Damage", 1, 10);
    }
}
