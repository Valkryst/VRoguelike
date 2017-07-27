package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.Creature;

public class CreatureBuilder extends AbstractCreatureBuilder<CreatureBuilder> {
    public Creature build() {
        super.checkState();
        return new Creature(this);
    }
}
