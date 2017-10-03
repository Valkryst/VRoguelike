package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.ItemDrop;
import com.valkryst.VRoguelike.item.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper=true)
public class ItemDropBuilder extends EntityBuilder {
    /** The item. */
    private Item item;

    /** Constructs an ItemDrop. */
    public ItemDrop build() {
        checkState();
        return new ItemDrop(this);
    }

    @Override
    public void checkState() {
        super.checkState();
        Objects.requireNonNull(item);
    }

    /**
     * Sets the item, name, description, and sprite using the
     * item and it's data.
     *
     * @param item
     *          The item.
     */
    public void setItem(final @NonNull Item item) {
        super.setName(item.getName());
        super.setDescription(item.getDescription());
        super.setSprite(item.getSprite());
        this.item = item;
    }
}
