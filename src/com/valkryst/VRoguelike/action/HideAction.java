package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import lombok.Data;
import lombok.NonNull;

@Data
public class HideAction implements Action {
    @Override
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        map.getScreen().removeComponent(entity.getLayer());
    }
}