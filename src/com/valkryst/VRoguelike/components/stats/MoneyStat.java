package com.valkryst.VRoguelike.components.stats;

public class MoneyStat extends StatComponent {
    /**
     * Constructs a new MoneyStat.
     *
     * @param currValue
     *        The current value.
     */
    public MoneyStat(final int currValue) {
        super("Money", Integer.MAX_VALUE, currValue, 0);
    }
}
