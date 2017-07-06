package com.valkryst.VRoguelike.components.stats;

public class DodgeStat extends StatComponent {
    /**
     * Constructs a new DodgeStat.
     *
     * @param currValue
     *        The current value.
     */
    public DodgeStat(final int currValue) {
        super("Dodge", 100, currValue, 0);
    }
}
