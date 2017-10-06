package com.valkryst.VRoguelike.ai.combat;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.world.Map;
import lombok.NonNull;

public interface CombatAI {
    /**
     * Decides what a creature should do in it's current
     * combat situation and acts upon the decision.
     *
     * @param map
     *        The map.
     *
     * @param self
     *        The entity acting on the decision.
     *
     * @param target
     *        The target.
     *
     * @throws NullPointerException
     *        If the map, self, or target are null.
     */
    void decide(final @NonNull Map map, final @NonNull Creature self, final @NonNull Creature target);
}
