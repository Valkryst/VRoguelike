package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.Player;

public class PlayerBuilder extends AbstractPlayerBuilder<PlayerBuilder> {
    public Player build() {
        super.checkState();
        return new Player(this);
    }
}
