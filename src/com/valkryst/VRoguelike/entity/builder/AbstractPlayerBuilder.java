package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.enums.Sprite;

public abstract class AbstractPlayerBuilder<B extends AbstractCreatureBuilder<B>> extends AbstractCreatureBuilder<B> {
    @Override
    public void reset() {
        super.reset();
        super.setName("Player");
        super.setDescription("This is you.");
        super.setSprite(Sprite.PLAYER);
    }
}
