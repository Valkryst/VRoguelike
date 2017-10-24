package com.valkryst.VRoguelike.loot;

import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.loot.builder.LootTableBuilder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class LootTable {
    /** The set of items that can be dropped. */
    private final List<LootEntry> lootEntries;

    /** Constructs a new empty LootTable. */
    public LootTable() {
        lootEntries = new ArrayList<>();
    }

    /**
     * Constructs a new LootTable.
     *
     * @param builder
     *          The builder.
     */
    public LootTable(final @NonNull LootTableBuilder builder) {
        lootEntries = builder.getLootEntries();
    }

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
