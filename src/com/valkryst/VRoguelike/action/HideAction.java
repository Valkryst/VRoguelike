package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;

public class HideAction implements Action {
    @Override
    public void perform(final Map map, final Entity entity) {
        map.getScreen().removeComponent(entity.getLayer());
    }
}
