package com.valkryst.VRoguelike.ai;

import com.valkryst.VRoguelike.action.AttackAction;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.world.Map;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class AggressiveCombatAI implements CombatAI {
    @Override
    public void decide(final @NonNull Map map, final @NonNull Creature self, final @NonNull Creature target) {
        new AttackAction(target).perform(map, self);
    }
}
