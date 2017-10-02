package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.component.TextArea;
import lombok.Data;
import lombok.NonNull;

@Data
public class ShowAction implements Action {
    @Override
    public void perform(final @NonNull Map map, final @NonNull TextArea messageBox, final @NonNull Entity entity) {
        map.getScreen().addComponent(entity.getLayer());
    }
}
