package com.valkryst.VRoguelike.components.stats;

import lombok.Getter;

public class HealthStat extends StatComponent<Integer> {
    /** The maximum health. */
    @Getter public int maxHealth;

    /**
     * Constructs a new HealthComponent.
     *
     * @param currValue
     *        The current and maximum health value.
     */
    public HealthStat(final int currValue) {
        super("Health", currValue);

        if (super.getCurrValue() < 1) {
            super.setCurrValue(1);
        }

        this.maxHealth = super.getCurrValue();
    }

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tName:\t" + super.getName();
        res += "\n\tCurrent Health:\t" + super.getCurrValue();
        res += "\n\tMaximum Health:\t" + maxHealth;
        return res;
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof HealthStat == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final HealthStat otherComp = (HealthStat) otherObj;

        boolean isEqual = super.equals(otherObj);
        isEqual &= maxHealth == otherComp.getMaxHealth();
        return isEqual;
    }

    @Override
    public String toJson() {
        return "{\"name\":" + super.getName() + ",\"curHealth\":" + super.getCurrValue() + ",\"maxHealth\":" + maxHealth + "}";
    }

    /**
     * Sets the new current health.
     *
     * If the value exceeds the maxHealth, then the current health is set
     * to the maxHealth.
     *
     * If the value is below 0, then the current health is set to 0.
     *
     * @param curHealth
     *        The new current health.
     */
    public void setCurrentHealth(final int curHealth) {
        if (curHealth > maxHealth) {
            super.setCurrValue(maxHealth);
        } else if (curHealth < 0) {
            super.setCurrValue(0);
        } else {
            super.setCurrValue(curHealth);
        }
    }
}
