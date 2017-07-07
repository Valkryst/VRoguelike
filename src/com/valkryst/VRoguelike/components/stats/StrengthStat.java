package com.valkryst.VRoguelike.components.stats;

public class StrengthStat extends StatComponent {
    /**
     * Constructs a new StrengthStat.
     *
     * @param currValue
     *        The current value.
     */
    public StrengthStat(final int currValue) {
        super("Strength", 100, currValue, 0);
    }
}
