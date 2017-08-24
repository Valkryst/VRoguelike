package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class AttackMissAction implements Action {
    @Override
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        map.getScreen().getMessageBox().appendText(entity.getName() + " missed it's target.");
    }
}
