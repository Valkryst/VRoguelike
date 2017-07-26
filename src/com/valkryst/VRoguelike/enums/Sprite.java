package com.valkryst.VRoguelike.enums;

import com.valkryst.VTerminal.misc.ColorFunctions;
import lombok.Getter;

import java.awt.Color;

public enum Sprite {
    DIRT('▒', new Color(0x452F09), new Color(0x372507)),
    GRASS('▒', new Color(0x3D4509), new Color(0x303707)),

    DARK_WALL('#', new Color(0, 0, 100), new Color(0, 0, 100)),

    PLAYER('@', new Color(0, 0, 0 ,0), Color.GREEN),
    ENEMY('E', new Color(0, 0, 0, 0), Color.RED);

    /** The character. */
    @Getter private final char character;
    /** The background color. */
    @Getter private final Color backgroundColor;
    /** The foreground color. */
    @Getter private final Color foregroundColor;
    /** The dark background color. */
    @Getter private final Color darkBackgroundColor;
    /** The dark foreground color. */
    @Getter private final Color darkForegroundColor;

    /**
     * Constructs a new Sprite.
     *
     * @param character
     *        The character.
     *
     * @param backgroundColor
     *        The background color.
     *
     * @param foregroundColor
     *        The foreground color.
     */
    Sprite(final char character, final Color backgroundColor, final Color foregroundColor) {
        this.character = character;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        darkBackgroundColor = ColorFunctions.shade(backgroundColor, 0.1);
        darkForegroundColor = ColorFunctions.shade(foregroundColor, 0.1);
    }
}
