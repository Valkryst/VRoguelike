package com.valkryst.VRoguelike.components.stats;

public class ExperienceStat extends StatComponent {
    /**
     * Constructs a new ExperienceStat.
     *
     * @param currValue
     *        The current value.
     */
    public ExperienceStat(final int currValue) {
        super("Experience", 100, currValue, 0);
    }
}
