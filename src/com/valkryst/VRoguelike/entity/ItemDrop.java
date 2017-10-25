package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.entity.builder.ItemDropBuilder;
import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.builder.component.ButtonBuilder;
import com.valkryst.VTerminal.component.Screen;
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

    @Override
    public Screen getInformationPanel(final @NonNull Map map) {
        final Screen screen = super.getInformationPanel(map);

        // Equip Button
        final ButtonBuilder buttonBuilder = new ButtonBuilder();
        buttonBuilder.setText("Equip");
        buttonBuilder.setRowIndex(1);
        buttonBuilder.setColumnIndex(1);
        screen.addComponent(buttonBuilder.build());

        // Destroy Button
        buttonBuilder.setText("Destroy");
        buttonBuilder.setRowIndex(2);
        screen.addComponent(buttonBuilder.build());

        return screen;
    }
}