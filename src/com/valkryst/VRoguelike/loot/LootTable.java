package com.valkryst.VRoguelike.loot;

import com.valkryst.VRoguelike.item.Item;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class LootTable {
    /** The set of items that can be dropped. */
    private final List<LootEntry> lootEntries = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Loot Table:");

        for (final LootEntry entry : lootEntries) {
            sb.append(entry.toString().replace("\n\t", "\n\t\t"));
        }

        return sb.toString();
    }

    /**
     * Adds a new entry to the loot table.
     *
     * @param entry
     *          The entry.
     *
     * @throws NullPointerException
     *          If the entry is null.
     */
    public void add(final @NonNull LootEntry entry) {
        lootEntries.add(entry);
    }

    /**
     * Retrieves a set of loot from the loot table.
     *
     * @return
     *        The loot.
     */
    public List<Item> loot() {
        final List<Item> loot = new ArrayList<>();

        lootEntries.forEach(lootEntry -> {
            if (lootEntry.drop()) {
                loot.add(lootEntry.getItem());
            }
        });

        return loot;
    }
}
