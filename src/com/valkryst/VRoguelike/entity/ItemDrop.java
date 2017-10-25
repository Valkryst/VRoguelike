package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.EquipAction;
import com.valkryst.VRoguelike.entity.builder.ItemDropBuilder;
import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import com.valkryst.VRoguelike.screen.GameScreen;
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
    public Screen getInformationPanel(final @NonNull GameScreen gameScreen) {
        final Map map = gameScreen.getMap();
        final Screen screen = super.getInformationPanel(gameScreen);

        final ButtonBuilder buttonBuilder = new ButtonBuilder();

        // Equip Button
        if (item instanceof EquippableItem) {
            buttonBuilder.setText("Equip");
            buttonBuilder.setRowIndex(1);
            buttonBuilder.setColumnIndex(1);
            buttonBuilder.setOnClickFunction(() -> {
                final EquippableItem equippableItem = (EquippableItem) item;
                final EquipmentSlot slot = equippableItem.getSlot();

                map.getPlayer().addAction(new EquipAction(slot, equippableItem));
            });
            screen.addComponent(buttonBuilder.build());
        }

        // Destroy Button
        buttonBuilder.setText("Destroy");
        buttonBuilder.setRowIndex(buttonBuilder.getRowIndex() + 1);
        buttonBuilder.setOnClickFunction(() -> map.removeEntities(this));
        screen.addComponent(buttonBuilder.build());

        return screen;
    }
}