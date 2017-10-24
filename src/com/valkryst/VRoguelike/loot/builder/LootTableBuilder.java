package com.valkryst.VRoguelike.loot.builder;

import com.valkryst.VJSON.VJSONParser;
import com.valkryst.VRoguelike.loot.LootEntry;
import com.valkryst.VRoguelike.loot.LootTable;
import lombok.Getter;
import lombok.NonNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LootTableBuilder implements VJSONParser {
    /** The set of items that can be dropped. */
    @Getter private List<LootEntry> lootEntries = new ArrayList<>();

    /** Constructs a new LootTableBuilder. */
    public LootTableBuilder() {
        reset();
    }

    /** Constructs a LootTable. */
    public LootTable build() {
        return new LootTable(this);
    }

    public void reset() {
        lootEntries = new ArrayList<>();
    }

    @Override
    public void parse(final @NonNull JSONObject jsonObject) {
        this.checkType(jsonObject, "loot_entry");

        final JSONArray lootEntries = (JSONArray) jsonObject.get("loot");

        final LootEntryBuilder builder = new LootEntryBuilder();

        lootEntries.forEach(entryObj -> {
            builder.parse((JSONObject) entryObj);
            this.lootEntries.add(builder.build());
        });
    }
}
