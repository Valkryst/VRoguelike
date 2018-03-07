package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.EquipAction;
import com.valkryst.VRoguelike.entity.builder.ItemDropBuilder;
import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import com.valkryst.VRoguelike.gui.view.GameView;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.builder.ButtonBuilder;
import com.valkryst.VTerminal.component.Layer;
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
    public Layer getInformationPanel(final @NonNull GameView gameView) {
        final Map map = gameView.getMap();
        final Layer layer = super.getInformationPanel(gameView);

        final ButtonBuilder buttonBuilder = new ButtonBuilder();
        buttonBuilder.setPosition(1, 1);

        // Equip Button
        if (item instanceof EquippableItem) {
            buttonBuilder.setText("▶ Equip");
            buttonBuilder.setOnClickFunction(() -> {
                final EquippableItem equippableItem = (EquippableItem) item;
                final EquipmentSlot slot = equippableItem.getSlot();

                final EquipAction equipAction = new EquipAction(slot, equippableItem);
                equipAction.getOnActionFunctions().add(() -> gameView.setTarget(null));

                map.getPlayer().addAction(equipAction);
                map.removeEntities(this);
            });
            layer.addComponent(buttonBuilder.build());
        }

        // Destroy Button
        buttonBuilder.setText("▶ Destroy");
        buttonBuilder.setPosition(1, 2);
        buttonBuilder.setOnClickFunction(() -> {
            map.removeEntities(this);
            gameView.setTarget(null);
        });
        layer.addComponent(buttonBuilder.build());

        return layer;
    }
}