package com.valkryst.VRoguelike.entity;

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
    /** Whether or not the tile has been seen before. */
    @Getter @Setter private boolean visited = false;
    /** Whether or not the tile is visible. */
    @Getter @Setter private boolean visible = true;

    /**
     * Constructs a new Tile.
     *
     * @param sprite
     *        The sprite.
     */
    public Tile(final Sprite sprite) {
        if (sprite == null) {
            throw new NullPointerException("The sprite cannot be null.");
        }

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
    public void placeOnScreen(final Screen screen, final int x, final int y) {
        if (screen == null) {
            throw new NullPointerException("The screen cannot be null.");
        }

        if (visited == false) {
            sprite = Sprite.DARKNESS;
            final AsciiString string = screen.getString(y);
            string.setCharacter(x, Sprite.DARKNESS.getCharacter());
            string.setBackgroundColor(Sprite.DARKNESS.getBackgroundColor(), new IntRange(x, x + 1));
            string.setForegroundColor(Sprite.DARKNESS.getForegroundColor(), new IntRange(x, x + 1));
        } else {
            final AsciiString string = screen.getString(y);
            string.setCharacter(x, sprite.getCharacter());

            if (visible) {
                string.setBackgroundColor(sprite.getBackgroundColor(), new IntRange(x, x + 1));
                string.setForegroundColor(sprite.getForegroundColor(), new IntRange(x, x + 1));
            } else {
                string.setBackgroundColor(sprite.getDarkBackgroundColor(), new IntRange(x, x + 1));
                string.setForegroundColor(sprite.getDarkForegroundColor(), new IntRange(x, x + 1));
            }
        }
    }
}
