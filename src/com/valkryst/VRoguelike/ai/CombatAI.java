package com.valkryst.VRoguelike.ai;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.world.Map;

public abstract class CombatAI {
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
     */
    public abstract void decide(final Map map, final Creature self, final Creature target);
}
