package com.valkryst.VRoguelike.item.equipment;

import com.valkryst.VRoguelike.stat.LimitedStatistic;
import com.valkryst.VRoguelike.stat.Statistic;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Weapon extends EquippableItem {
    /**
     * Constructs a new Armor.
     *
     * @param name
     *        The name.
     *
     * @param description
     *        The description.
     *
     * @param slot
     *        The slot.
     */
    public Weapon(final String name, final String description, final EquipmentSlot slot) {
        super(name, description, slot);

        super.addStatistic(new LimitedStatistic("Damage", 1, 10));
    }

    /**
     * Constructs a new Armor.
     *
     * @param name
     *        The name.
     *
     * @param description
     *        The description.
     *
     * @param statistics
     *        The statistics.
     *
     * @param slot
     *        The slot.
     *
     * @throws IllegalStateException
     *        If any of the required statistics are not present
     *        or are not of the correct type.
     */
    public Weapon(final String name, final String description, final List<Statistic> statistics, final EquipmentSlot slot) {
        super(name, description, statistics, slot);
        validateStatistic("Damage", true);
    }

    /**
     * Ensures that a statistic exists and that it's of the
     * correct type.
     *
     * @param name
     *        The name of the statistic.
     *
     * @param isLimitedStatistic
     *        Whether or not the statistic should be of the
     *        LimitedStatistic type.
     */
    private void validateStatistic(final String name, final boolean isLimitedStatistic) {
        final Optional<Statistic> optStat = super.getStatistic(name);

        if (optStat.isPresent() == false) {
            throw new IllegalStateException("A weapon must have a 'Damage' LimitedStatistic.");
        }

        if (optStat.get() instanceof LimitedStatistic != isLimitedStatistic) {
            throw new IllegalStateException("The damage statistic must be of the LimitedStatistic type.");
        }
    }

    /**
     * Calculates a damage value for an attack.
     *
     * @return
     *        A damage value for an attack.
     */
    public int attack() {
        final Optional<Statistic> optDamage = super.getStatistic("Damage");
        final LimitedStatistic damage = (LimitedStatistic) optDamage.get();

        return ThreadLocalRandom.current().nextInt(damage.getMaximum()) + damage.getMinimum();
    }
}
