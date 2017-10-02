package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.world.Map;
import lombok.Data;
import lombok.NonNull;

import java.awt.TextArea;

@Data
public class DeathAction implements Action {
    @Override
    public void perform(final @NonNull Map map, final @NonNull TextArea messageBox, final @NonNull Entity entity) {
        map.removeEntities(entity);
        messageBox.appendText(entity.getName() + " has died.");
    }
}
