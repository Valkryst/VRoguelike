package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.component.Layer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Tile {
    /** The sprite. */
    private Sprite sprite = Sprite.WALL;

    /** The cost for an entity to move across the tile. */
    @Getter @Setter private int movementCost = 1;

    /** Whether or not the tile is solid. */
    private boolean solid = true;
    /** Whether or not the tile is transparent. */
    private boolean transparent = false;
    /** Whether or not the tile has been seen before. */
    private boolean visited = false;
    /** Whether or not the tile is visible. */
    private boolean visible = false;

    /** Constructs a new Tile. */
    public Tile() {}

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
     * @param layer
     *        The layer.
     *
     * @param x
     *        The x-axis position of the tile.
     *
     * @param y
     *        The y-axis position of the tile.
     */
    public void placeOnScreen(final Layer layer, final int x, final int y) {
        if (layer == null) {
            throw new NullPointerException("The screen cannot be null.");
        }

        if (visited == false) {
            final com.valkryst.VTerminal.Tile tile = layer.getTileAt(x, y);
            tile.setCharacter(Sprite.DARKNESS.getCharacter());
            tile.setBackgroundColor(Sprite.DARKNESS.getBackgroundColor());
            tile.setForegroundColor(Sprite.DARKNESS.getForegroundColor());
        } else {
            final com.valkryst.VTerminal.Tile tile = layer.getTileAt(x, y);
            tile.setCharacter(sprite.getCharacter());

            if (visible) {
                tile.setBackgroundColor(sprite.getBackgroundColor());
                tile.setForegroundColor(sprite.getForegroundColor());
            } else {
                tile.setBackgroundColor(sprite.getDarkBackgroundColor());
                tile.setForegroundColor(sprite.getDarkForegroundColor());
            }
        }
    }
}
