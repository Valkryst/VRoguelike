package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.enums.Sprite;

public class PlayerBuilder extends CreatureBuilder {
    public Player build() {
        super.setSprite(Sprite.PLAYER);
        super.checkState();
        return new Player(this);
    }
}
