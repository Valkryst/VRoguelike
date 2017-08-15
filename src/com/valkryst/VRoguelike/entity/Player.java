package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.entity.builder.PlayerBuilder;

public class Player extends Creature {
    /**
     * Constructs a new player.
     *
     * @param builder
     *        The builder.
     */
    public Player(final PlayerBuilder builder) {
        super(builder);
    }

    @Override
    public String toString() {
        return "Player" + super.toString().substring(8);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Player == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        return super.equals(otherObj);
    }
}
