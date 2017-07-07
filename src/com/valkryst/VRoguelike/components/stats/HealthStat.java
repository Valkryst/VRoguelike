package com.valkryst.VRoguelike.components.stats;

public class HealthStat extends StatComponent {
    /**
     * Constructs a new HealthStat.
     *
     * @param currValue
     *        The current and maximum health value.
     */
    public HealthStat(final int currValue) {
        super("Health", currValue, currValue, 0);
    }
}
