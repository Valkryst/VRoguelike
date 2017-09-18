package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.enums.Sprite;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString
public class PlayerBuilder extends CreatureBuilder {
    public Player build() {
        super.checkState();
        return new Player(this);
    }

    @Override
    public void reset() {
        super.reset();
        super.setName("Player");
        super.setDescription("This is you.");
        super.setSprite(Sprite.PLAYER);
    }
}
