package com.valkryst.VRoguelike.item.equipment;

import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.item.builder.equipment.EquippableItemBuilder;
import com.valkryst.VTerminal.builder.component.LabelBuilder;
import com.valkryst.VTerminal.component.Label;
import com.valkryst.VTerminal.misc.IntRange;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.awt.Color;

@EqualsAndHashCode(callSuper=true)
public class EquippableItem extends Item {
    /** The slot. */
    @Getter private final EquipmentSlot slot;

    /**
     * Constructs a new EquippableItem.
     *
     * @param builder
     *        The builder.
     */
    public EquippableItem(final EquippableItemBuilder builder) {
        super(builder);
        slot = builder.getSlot();
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tSlot:\t" + slot.name();
    }

    public Label getInformationLabel() {
        final LabelBuilder labelBuilder = new LabelBuilder();
        labelBuilder.setText(slot.getName() + ": " + super.getName());

        final Label label = labelBuilder.build();

        // Color item name
        final int slotNameLength = slot.getName().length() + 1;
        final int nameLength = super.getName().length();

        final Color color = getSprite().getForegroundColor();
        final IntRange nameRange = new IntRange(slotNameLength, slotNameLength + 1 + nameLength);
        label.getString(0).setForegroundColor(color, nameRange);

        return label;
    }
}
