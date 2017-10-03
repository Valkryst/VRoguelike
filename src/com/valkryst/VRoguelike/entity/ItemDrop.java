package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.entity.builder.ItemDropBuilder;
import com.valkryst.VRoguelike.item.Item;
import lombok.Getter;
import lombok.NonNull;

public class ItemDrop extends Entity {
    /** The item. */
    @Getter private final Item item;

    /**
     * Constructs a new ItemDrop.
     *
     * @param builder
     *        The builder.
     *
     * @throws NullPointerException
     *        If the builder is null.
     */
    public ItemDrop(final @NonNull ItemDropBuilder builder) {
        super(builder);
        this.item = builder.getItem();
    }
}
