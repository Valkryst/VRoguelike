package com.valkryst.VRoguelike.components.stats;

public class AccuracyStat extends StatComponent {
    /**
     * Constructs a new AccuracyStat.
     *
     * @param currValue
     *        The current value.
     */
    public AccuracyStat(final int currValue) {
        super("Accuracy", 100, currValue, 0);
    }
}
