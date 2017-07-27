package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.Entity;

public class EntityBuilder extends AbstractEntityBuilder<EntityBuilder> {
    public Entity build() {
        super.checkState();
        return new Entity(this);
    }
}
