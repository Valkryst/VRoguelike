package com.valkryst.VRoguelike.components;

import com.valkryst.VECS.Component;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.AsciiCharacter;
import com.valkryst.VTerminal.component.Layer;
import lombok.Getter;

public class SpriteComponent extends Component {
    /** The sprite. */
    @Getter private final Sprite sprite;

    /** The layer on which the sprite is drawn. */
    @Getter private final Layer layer;

    /**
     * Constructs a new SpriteComponent.
     *
     * @param sprite
     *        The sprite.
     *
     * @param position
     *        The position.
     */
    public SpriteComponent(final Sprite sprite, final PositionComponent position) {
        if (sprite == null) {
            throw new IllegalArgumentException("A SpriteComponent cannot have a null Sprite.");
        }

        this.sprite = sprite;
        layer = new Layer(position.getX(), position.getY(), 1, 1);

        final AsciiCharacter character = layer.getStrings()[0].getCharacters()[1];
        character.setCharacter(sprite.getCharacter());
        character.setBackgroundColor(sprite.getBackgroundColor());
        character.setForegroundColor(sprite.getForegroundColor());
    }

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tSprite:\t";
        res += "\n\t\tCharacter:\t" + sprite.getCharacter();
        res += "\n\t\tBackground Color:\t" + sprite.getBackgroundColor();
        res += "\n\t\tForeground Color:\t" + sprite.getForegroundColor();
        return res;
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof SpriteComponent == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final SpriteComponent otherComp = (SpriteComponent) otherObj;
        boolean isEqual = sprite == otherComp.getSprite();
        isEqual &= layer.equals(otherComp.getLayer());

        return isEqual;
    }

    @Override
    public String toJson() {
        return "{\"sprite\":" + sprite.name() + "}";
    }
}
