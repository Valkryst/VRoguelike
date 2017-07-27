package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.component.Screen;
import lombok.Getter;

import java.util.Objects;

public abstract class AbstractPlayerBuilder<B extends AbstractCreatureBuilder<B>> extends AbstractCreatureBuilder<B> {
    /** The screen. */
    @Getter private Screen screen;

    @Override
    public void checkState() {
        super.checkState();
        Objects.requireNonNull(screen);
    }

    @Override
    public void reset() {
        super.reset();
        super.setName("Player");
        super.setDescription("This is you.");
        super.setSprite(Sprite.PLAYER);
        screen = null;
    }

    /**
     * Sets the screen.
     *
     * @param screen
     *        The screen.
     *
     * @return
     *        This.
     */
    public B setScreen(final Screen screen) {
        this.screen = screen;
        return (B) this;
    }
}
