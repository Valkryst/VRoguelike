package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.entity.builder.PlayerBuilder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString
public class Player extends Creature {
    /**
     * Constructs a new player.
     *
     * @param builder
     *        The builder.
     *
     * @throws NullPointerException
     *        If the builder is null.
     */
    public Player(final @NonNull PlayerBuilder builder) {
        super(builder);
    }
}
