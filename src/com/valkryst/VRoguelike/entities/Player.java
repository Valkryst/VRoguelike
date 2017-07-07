package com.valkryst.VRoguelike.entities;

import com.valkryst.VRoguelike.components.InputComponent;
import com.valkryst.VRoguelike.components.PositionComponent;
import com.valkryst.VRoguelike.components.SpriteComponent;
import com.valkryst.VRoguelike.components.VelocityComponent;
import com.valkryst.VRoguelike.enums.Sprite;
import lombok.Getter;

public class Player {
    @Getter private final InputComponent input = new InputComponent();
    private final PositionComponent position;
    @Getter private final SpriteComponent sprite;
    private final VelocityComponent velocity = new VelocityComponent();

    public Player(final PositionComponent position, final Sprite sprite) {
        this.position = position;
        this.sprite = new SpriteComponent(sprite, position);

        position.getRadio().addReceiver("MOVED", this.sprite);

        input.getRadio().addReceiver("KEY_PRESSED", velocity);
        input.getRadio().addReceiver("KEY_RELEASED", velocity);

        velocity.getRadio().addReceiver("VELOCITY_CHANGED", position);
    }
}
