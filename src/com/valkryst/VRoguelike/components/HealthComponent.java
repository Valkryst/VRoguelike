package com.valkryst.VRoguelike.components;

import com.valkryst.VECS.Component;
import lombok.Getter;

public class HealthComponent extends Component {
    /** The current health. */
    @Getter public int currentHealth;
    /** The maximum health. */
    @Getter public int maxHealth;

    /**
     * Constructs a new HealthComponent.
     *
     * @param maxHealth
     *        The maximum health.
     */
    public HealthComponent(final int maxHealth) {
        this.currentHealth = maxHealth;
        this.maxHealth = maxHealth;
    }

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tCurrent Health:\t" + currentHealth;
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
        boolean isEqual = currentHealth == otherComp.getCurrentHealth();
        isEqual &= maxHealth == otherComp.getMaxHealth();

        return isEqual;
    }

    @Override
    public String toJson() {
        return "{\"currentHealth\":" + currentHealth + ",\"maxHealth\":" + maxHealth + "}";
    }

    /**
     * Sets the new current health.
     *
     * If the value exceeds the maxHealth, then the current health is set
     * to the maxHealth.
     *
     * If the value is below 0, then the current health is set to 0.
     *
     * @param currentHealth
     *        The new current health.
     */
    public void setCurrentHealth(final int currentHealth) {
        if (currentHealth > maxHealth) {
            this.currentHealth = maxHealth;
        } else if (currentHealth < 0) {
            this.currentHealth = 0;
        } else {
            this.currentHealth = currentHealth;
        }
    }
}
