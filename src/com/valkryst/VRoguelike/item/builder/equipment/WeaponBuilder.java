package com.valkryst.VRoguelike.item.builder.equipment;

import com.valkryst.VRoguelike.item.equipment.Weapon;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.json.simple.JSONObject;

import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper=true)
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

    @Override
    public void parseJSON(final @NonNull JSONObject jsonObject) {
        super.parseJSON(jsonObject);

        final BoundedStatistic stat_damage = new BoundedStatistic((JSONObject) jsonObject.get("stat_damage"));

        if (stat_damage != null) {
            this.stat_damage = stat_damage;
        }
    }
}
