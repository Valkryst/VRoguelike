package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.enums.Sprite;
import org.junit.Assert;
import org.junit.Test;


public class TileTest {
    @Test
    public void testConstructor_withValidInput() {
        final Tile tile = new Tile(Sprite.PLAYER);

        Assert.assertFalse(tile.isSolid());
        Assert.assertTrue(tile.isTransparent());
        Assert.assertEquals(Sprite.PLAYER.getCharacter(), tile.getSprite().getCharacter());
        Assert.assertEquals(Sprite.PLAYER.getBackgroundColor(), tile.getSprite().getBackgroundColor());
        Assert.assertEquals(Sprite.PLAYER.getForegroundColor(), tile.getSprite().getForegroundColor());
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_withNullSprite() {
        new Tile(null);
    }

    @Test(expected=NullPointerException.class)
    public void testPlaceOnScreen_withNullSprite() {
        final Tile tile = new Tile(Sprite.PLAYER);
        tile.placeOnScreen(null, 0, 0);
    }
}
