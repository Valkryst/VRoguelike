package com.valkryst.VRoguelike.loot.builder;

import com.valkryst.VJSON.VJSONParser;
import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.item.builder.ItemBuilder;
import com.valkryst.VRoguelike.item.builder.equipment.ArmorBuilder;
import com.valkryst.VRoguelike.item.builder.equipment.WeaponBuilder;
import com.valkryst.VRoguelike.loot.LootEntry;
import lombok.Data;
import lombok.NonNull;
import org.json.simple.JSONObject;

@Data
public class LootEntryBuilder implements VJSONParser {
    /** The item. */
    private Item item;
    /** The drop chance. */
    private int dropChance;

    /** Constructs a new LootEntryBuilder. */
    public LootEntryBuilder() {
        reset();
    }

    /** Constructs a LootEntry. */
    public LootEntry build() {
        checkState();
        return new LootEntry(this);
    }

    /** Ensures the builder is in a valid state. */
    private void checkState() {
        if (item == null) {
            throw new IllegalStateException("You must set an item to be dropped.");
        }

        if (dropChance < 1) {
            dropChance = 1;
        }

        if (dropChance > 100) {
            dropChance = 100;
        }
    }

    public void reset() {
        item = null;
        dropChance = 100;
    }

    @Override
    public void parse(final @NonNull JSONObject jsonObject) {
        final JSONObject itemJSON = (JSONObject) jsonObject.get("item");
        final String type = getString(itemJSON, "type");

        switch (type) {
            case "item_armor": {
                final ItemBuilder itemBuilder = new ArmorBuilder();
                itemBuilder.parse(itemJSON);
                item = itemBuilder.build();
                break;
            }
            case "item_weapon": {
                final ItemBuilder itemBuilder = new WeaponBuilder();
                itemBuilder.parse(itemJSON);
                item = itemBuilder.build();
                break;
            }
            case "item": {
                final ItemBuilder itemBuilder = new ItemBuilder();
                itemBuilder.parse(itemJSON);
                item = itemBuilder.build();
                break;
            }
            default: {
                item = null;
            }
        }

        final Integer dropChance = getInteger(jsonObject, "chance");

        if (dropChance != null) {
            this.dropChance = dropChance;
        }
    }
}
