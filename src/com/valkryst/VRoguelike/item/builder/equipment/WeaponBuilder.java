package com.valkryst.VRoguelike.item.builder.equipment;

import com.valkryst.VRoguelike.item.equipment.Weapon;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import lombok.Data;

import java.util.Objects;

@Data
public class WeaponBuilder extends EquippableItemBuilder {
    /** The damage-range. */
    private BoundedStatistic stat_damage;

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
        stat_damage = new BoundedStatistic("Damage", 1, 10);
    }
}
