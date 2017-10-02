package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import lombok.NonNull;

public interface Action {
    /**
     * Performs the action.
     *
     * @param map
     *        The map.
     *
     * @param entity
     *        The entity performing the action.
     *
     * @throws NullPointerException
     *        If the map or entity is null.
     */
    void perform(final @NonNull Map map, final @NonNull Entity entity);
}
