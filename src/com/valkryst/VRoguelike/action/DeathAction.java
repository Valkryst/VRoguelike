package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import lombok.Data;
import lombok.NonNull;

@Data
public class DeathAction implements Action {
    @Override
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        map.removeEntities(entity);
        map.getScreen().getMessageBox().appendText(entity.getName() + " has died.");
    }
}
