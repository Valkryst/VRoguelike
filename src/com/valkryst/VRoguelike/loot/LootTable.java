package com.valkryst.VRoguelike.loot;

import com.valkryst.VRoguelike.item.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LootTable {
    /** The set of items that can be dropped. */
    @Getter private final List<LootEntry> lootTable = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Loot Table:");

        for (final LootEntry entry : lootTable) {
            sb.append(entry.toString().replace("\n\t", "\n\t\t"));
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(lootTable);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof LootTable == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final LootTable otherTable = (LootTable) otherObj;
        return Objects.equals(lootTable, otherTable.getLootTable());
    }

    /**
     * Adds a new entry to the loot table.
     *
     * @param item
     *        The item.
     *
     * @param dropChance
     *        The drop chance.
     */
    public void add(final Item item, final int dropChance) {
        lootTable.add(new LootEntry(item, dropChance));
    }

    /**
     * Retrieves a set of loot from the loot table.
     *
     * @return
     *        The loot.
     */
    public List<Item> loot() {
        final List<Item> loot = new ArrayList<>();

        lootTable.forEach(lootEntry -> {
            if (lootEntry.drop()) {
                loot.add(lootEntry.getItem());
            }
        });

        return loot;
    }
}
