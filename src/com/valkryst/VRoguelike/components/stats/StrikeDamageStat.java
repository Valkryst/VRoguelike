package com.valkryst.VRoguelike.components.stats;

public class StrikeDamageStat extends StatComponent {
    /**
     * Constructs a new StrikeDamageStat.
     *
     * @param currValue
     *        The current value.
     */
    public StrikeDamageStat(final int currValue) {
        super("Strike Damage", 100, currValue, 0);
    }
}
