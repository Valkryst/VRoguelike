package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.builder.ItemDropBuilder;
import com.valkryst.VRoguelike.loot.LootTable;
import com.valkryst.VRoguelike.world.Map;
import lombok.Data;
import lombok.NonNull;

@Data
public class DeathAction implements Action {
    @Override
    public void perform(final @NonNull Map map, final @NonNull Entity entity) {
        map.removeEntities(entity);
        map.getMessageBox().appendText(entity.getName() + " has died.");


        if (entity instanceof Creature) {
            final Creature creature = (Creature) entity;
            final LootTable lootTable = creature.getLootTable();

            if (lootTable != null) {
                lootTable.loot().forEach(lootEntry -> {
                    final ItemDropBuilder builder = new ItemDropBuilder();
                    builder.setItem(lootEntry);

                    map.addEntities(builder.build());
                });
            }
        }
    }
}
