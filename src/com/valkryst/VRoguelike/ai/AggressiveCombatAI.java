package com.valkryst.VRoguelike.ai;

import com.valkryst.VRoguelike.action.AttackAction;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.world.Map;

public class AggressiveCombatAI extends CombatAI {
    @Override
    public void decide(final Map map, final Creature self, final Creature target) {
        new AttackAction(target).perform(map, self);
    }
}
