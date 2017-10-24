package com.valkryst.VRoguelike.item.equipment;

import com.valkryst.VTerminal.builder.component.LabelBuilder;
import com.valkryst.VTerminal.builder.component.ScreenBuilder;
import com.valkryst.VTerminal.component.Label;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.printer.RectanglePrinter;
import lombok.NonNull;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EquipmentInventory {
    /** The equipped equipment. */
    private final Map<EquipmentSlot, EquippableItem> equipment = new HashMap<>();

    /**
     * Retrieves the item in a specific slot.
     *
     * @param slot
     *        The slot.
     *
     * @return
     *        The item in the slot, or nothing if no item was found.
     */
    public Optional<EquippableItem> getItemInSlot(final EquipmentSlot slot) {
        Objects.requireNonNull(slot);
        return Optional.ofNullable(equipment.get(slot));
    }

    /**
     * Sets the item in a specific slot.
     *
     * @param slot
     *        The slot.
     *
     * @param item
     *        The item.
     *
     * @throws IllegalArgumentException
     *        If the slot to equip to, and the item's slot type, do
     *        not match.
     */
    public void setItemInSlot(final EquipmentSlot slot, final EquippableItem item) {
        Objects.requireNonNull(slot);

        if (item.getSlot() != slot) {
            throw new IllegalArgumentException("The slot to equip to, and the item's slot type, do not match.\n\t"
                                               + item.toString().replace("\n\t", "\n\t\t"));
        }

        equipment.put(slot, item);
    }

    public Screen getInformationPanel() {
        final ScreenBuilder screenBuilder = new ScreenBuilder();
        screenBuilder.setWidth(39);
        screenBuilder.setHeight(14);

        final Screen screen = screenBuilder.build();

        // Print border
        final RectanglePrinter rectanglePrinter = new RectanglePrinter();
        rectanglePrinter.setWidth(39);
        rectanglePrinter.setHeight(14);
        rectanglePrinter.setTitle("Equipment");
        rectanglePrinter.print(screen, new Point(0, 0));

        // Add item labels:
        final Label label_head = getEquipmentLabel(EquipmentSlot.HEAD);
        label_head.setPosition(new Point(6, 1));

        final Label label_neck = getEquipmentLabel(EquipmentSlot.NECK);
        label_neck.setPosition(new Point(6, 2));

        final Label label_back = getEquipmentLabel(EquipmentSlot.BACK);
        label_back.setPosition(new Point(6, 3));

        final Label label_shoulders = getEquipmentLabel(EquipmentSlot.SHOULDERS);
        label_shoulders.setPosition(new Point(1, 4));

        final Label label_chest = getEquipmentLabel(EquipmentSlot.CHEST);
        label_chest.setPosition(new Point(5, 5));

        final Label label_wrists = getEquipmentLabel(EquipmentSlot.WRISTS);
        label_wrists.setPosition(new Point(4, 6));

        final Label label_hands = getEquipmentLabel(EquipmentSlot.HANDS);
        label_hands.setPosition(new Point(5, 7));

        final Label label_waist = getEquipmentLabel(EquipmentSlot.WAIST);
        label_waist.setPosition(new Point(5, 8));

        final Label label_legs = getEquipmentLabel(EquipmentSlot.LEGS);
        label_legs.setPosition(new Point(6, 9));

        final Label label_feet = getEquipmentLabel(EquipmentSlot.FEET);
        label_feet.setPosition(new Point(6, 10));

        final Label label_mainHand = getEquipmentLabel(EquipmentSlot.MAIN_HAND);
        label_mainHand.setPosition(new Point(1, 11));

        final Label label_offHand = getEquipmentLabel(EquipmentSlot.OFF_HAND);
        label_offHand.setPosition(new Point(2, 12));

        screen.addComponents(label_head, label_neck, label_back, label_shoulders,
                                label_chest, label_wrists, label_hands, label_waist,
                                label_legs, label_feet, label_mainHand, label_offHand);

        return screen;
    }

    /**
     * Retrieves the label of an equipment slot.
     *
     * @param slot
     *          The slot.
     *
     * @return
     *          The label.
     */
    private Label getEquipmentLabel(final @NonNull EquipmentSlot slot) {
        final Optional<EquippableItem> optional = getItemInSlot(slot);

        if (optional.isPresent()) {
            return optional.get().getInformationLabel();
        } else {
            final LabelBuilder labelBuilder = new LabelBuilder();
            labelBuilder.setText(slot.getName() + ":");
            return labelBuilder.build();
        }
    }
}
