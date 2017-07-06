package com.valkryst.VRoguelike.components.stats;

public class DamageAbsorptionStat extends StatComponent {
    /**
     * Constructs a new DamageAbsorptionStat.
     *
     * @param currValue
     *        The current value.
     */
    public DamageAbsorptionStat(final int currValue) {
        super("Damage Absorption", 100, currValue, 0);
    }
}
