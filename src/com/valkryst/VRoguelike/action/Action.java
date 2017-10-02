package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.component.TextArea;
import lombok.NonNull;

public interface Action {
    /**
     * Performs the action.
     *
     * @param map
     *        The map.
     *
     * @param messageBox
     *        The TextArea to display messages in.
     *
     * @param entity
     *        The entity performing the action.
     *
     * @throws NullPointerException
     *        If the map, message box, or entity is null.
     */
    void perform(final @NonNull Map map, final @NonNull TextArea messageBox, final @NonNull Entity entity);
}
