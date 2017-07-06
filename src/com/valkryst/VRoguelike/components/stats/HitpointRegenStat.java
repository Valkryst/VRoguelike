package com.valkryst.VRoguelike.components.stats;

public class HitpointRegenStat extends StatComponent {
    /**
     * Constructs a new HitpointRegenStat.
     *
     * @param currValue
     *        The current value.
     */
    public HitpointRegenStat(final int currValue) {
        super("Hitpoint Regen", 10, currValue, 0);
    }
}
