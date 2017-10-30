package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Action {
    /** The functions to run when the action is run. */
    @Getter private final List<Runnable> onActionFunctions = new ArrayList<>();

    /**
     * Performs the action and runs the on action functions.
     *
     * Each implementation may run the functions at different times or only under a
     * specific set of circumstances.
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
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        for (final Runnable runnable : onActionFunctions) {
            runnable.run();
        }
    }
}
