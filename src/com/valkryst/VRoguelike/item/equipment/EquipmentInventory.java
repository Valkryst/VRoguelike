package com.valkryst.VRoguelike.item.equipment;

import com.valkryst.VTerminal.builder.LabelBuilder;
import com.valkryst.VTerminal.component.Label;
import com.valkryst.VTerminal.component.Layer;
import com.valkryst.VTerminal.printer.RectanglePrinter;
import lombok.NonNull;

import java.awt.*;
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
            throw new IllegalArgumentException("The slot to equip to, and the item's slot type, do not match.\n\t" + item.toString().replace("\n\t", "\n\t\t"));
        }

        equipment.put(slot, item);
    }

    public Layer getInformationPanel() {
        final Layer layer = new Layer(new Dimension(39, 14));

        // Print border
        final RectanglePrinter rectanglePrinter = new RectanglePrinter();
        rectanglePrinter.setWidth(39);
        rectanglePrinter.setHeight(14);
        rectanglePrinter.setTitle("Equipment");
        rectanglePrinter.print(layer.getTiles(), new Point(0, 0));

        // Add item labels:
        final Label label_head = getEquipmentLabel(EquipmentSlot.HEAD);
        label_head.getTiles().setPosition(6, 1);
        layer.addComponent(label_head);

        final Label label_neck = getEquipmentLabel(EquipmentSlot.NECK);
        label_neck.getTiles().setPosition(6, 2);
        layer.addComponent(label_neck);

        final Label label_back = getEquipmentLabel(EquipmentSlot.BACK);
        label_back.getTiles().setPosition(6, 3);
        layer.addComponent(label_back);

        final Label label_shoulders = getEquipmentLabel(EquipmentSlot.SHOULDERS);
        label_shoulders.getTiles().setPosition(1, 4);
        layer.addComponent(label_shoulders);

        final Label label_chest = getEquipmentLabel(EquipmentSlot.CHEST);
        label_chest.getTiles().setPosition(5, 5);
        layer.addComponent(label_chest);

        final Label label_wrists = getEquipmentLabel(EquipmentSlot.WRISTS);
        label_wrists.getTiles().setPosition(4, 6);
        layer.addComponent(label_wrists);

        final Label label_hands = getEquipmentLabel(EquipmentSlot.HANDS);
        label_hands.getTiles().setPosition(5, 7);
        layer.addComponent(label_hands);

        final Label label_waist = getEquipmentLabel(EquipmentSlot.WAIST);
        label_waist.getTiles().setPosition(5, 8);
        layer.addComponent(label_waist);

        final Label label_legs = getEquipmentLabel(EquipmentSlot.LEGS);
        label_legs.getTiles().setPosition(6, 9);
        layer.addComponent(label_legs);

        final Label label_feet = getEquipmentLabel(EquipmentSlot.FEET);
        label_feet.getTiles().setPosition(6, 10);
        layer.addComponent(label_feet);

        final Label label_mainHand = getEquipmentLabel(EquipmentSlot.MAIN_HAND);
        label_mainHand.getTiles().setPosition(1, 11);
        layer.addComponent(label_mainHand);

        final Label label_offHand = getEquipmentLabel(EquipmentSlot.OFF_HAND);
        label_offHand.getTiles().setPosition(2, 12);
        layer.addComponent(label_offHand);

        return layer;
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
