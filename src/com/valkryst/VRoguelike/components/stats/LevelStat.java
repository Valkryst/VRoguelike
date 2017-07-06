package com.valkryst.VRoguelike.components.stats;

public class LevelStat extends StatComponent {
    /**
     * Constructs a new LevelStat.
     *
     * @param currValue
     *        The current value.
     */
    public LevelStat(final int currValue) {
        super("Level", 10, currValue, 1);
    }
}
