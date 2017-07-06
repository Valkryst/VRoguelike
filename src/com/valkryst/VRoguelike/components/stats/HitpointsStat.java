package com.valkryst.VRoguelike.components.stats;

public class HitpointsStat extends StatComponent {
    /**
     * Constructs a new HitpointsStat.
     *
     * @param currValue
     *        The current value.
     */
    public HitpointsStat(final int currValue) {
        super("Hitpoints", 100, currValue, 0);
    }
}
