package com.valkryst.VRoguelike.entities;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.AsciiString;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.misc.IntRange;
import lombok.Getter;
import lombok.Setter;

public class Tile {
    /** The sprite. */
    @Getter @Setter private Sprite sprite;

    /** Whether or not the tile is solid. */
    @Getter @Setter private boolean solid = false;
    /** Whether or not the tile is transparent. */
    @Getter @Setter private boolean transparent = true;

    /**
     * Constructs a new Tile.
     *
     * @param sprite
     *        The sprite.
     */
    public Tile(final Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Places the tile on a screen.
     *
     * @param screen
     *        The screen.
     *
     * @param x
     *        The x-axis position of the tile.
     *
     * @param y
     *        The y-axis position of the tile.
     */
    public void  placeOnScreen(final Screen screen, final int x, final int y) {
        final AsciiString string = screen.getString(y);
        string.setCharacter(x, sprite.getCharacter());
        string.setBackgroundColor(sprite.getBackgroundColor(), new IntRange(x, x + 1));
        string.setForegroundColor(sprite.getForegroundColor(), new IntRange(x, x + 1));
    }
}
