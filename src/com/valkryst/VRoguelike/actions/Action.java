package com.valkryst.VRoguelike.actions;

import com.valkryst.VRoguelike.entities.Entity;
import com.valkryst.VRoguelike.world.Map;

public interface Action {
    /**
     * Performs the action.
     *
     * @param map
     *        The map.
     *
     * @param entity
     *        The entity performing the action.
     */
    void perform(final Map map, final Entity entity);
}
