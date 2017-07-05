package com.valkryst.VRoguelike.components;

import com.valkryst.VECS.Component;
import lombok.Getter;

public class HealthComponent extends Component {
    /** The current health. */
    @Getter public int curHealth;
    /** The maximum health. */
    @Getter public int maxHealth;

    /**
     * Constructs a new HealthComponent.
     *
     * @param maxHealth
     *        The maximum health.
     */
    public HealthComponent(int maxHealth) {
        if (maxHealth < 1) {
            maxHealth = 1;
        }

        this.curHealth = maxHealth;
        this.maxHealth = maxHealth;
    }

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tCurrent Health:\t" + curHealth;
        res += "\n\tMaximum Health:\t" + maxHealth;
        return res;
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof HealthComponent == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final HealthComponent otherComp = (HealthComponent) otherObj;
        boolean isEqual = curHealth == otherComp.getCurHealth();
        isEqual &= maxHealth == otherComp.getMaxHealth();

        return isEqual;
    }

    @Override
    public String toJson() {
        return "{\"curHealth\":" + curHealth + ",\"maxHealth\":" + maxHealth + "}";
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
            this.curHealth = maxHealth;
        } else if (curHealth < 0) {
            this.curHealth = 0;
        } else {
            this.curHealth = curHealth;
        }
    }
}
