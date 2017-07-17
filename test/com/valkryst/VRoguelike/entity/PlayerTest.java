package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.AsciiCharacter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class PlayerTest {
    private final Player player = new Player(0, 0, Sprite.PLAYER);

    @Test
    public void testConstructor_withValidInput() {
        final Player player = new Player(0, 1, Sprite.PLAYER);
        Assert.assertEquals(0, player.getLayer().getColumnIndex());
        Assert.assertEquals(1, player.getLayer().getRowIndex());

        final Optional<AsciiCharacter> optChar = player.getLayer().getCharacterAt(0, 0);
        Assert.assertTrue(optChar.isPresent());
        Assert.assertEquals(Sprite.PLAYER.getCharacter(), optChar.get().getCharacter());
        Assert.assertEquals(Sprite.PLAYER.getBackgroundColor(), optChar.get().getBackgroundColor());
        Assert.assertEquals(Sprite.PLAYER.getForegroundColor(), optChar.get().getForegroundColor());
    }

    @Test
    public void testShow_withNullPanel() {
        Assert.assertFalse(player.show(null));
    }

    @Test
    public void testHide_withNullPanel() {
        Assert.assertFalse(player.hide(null));
    }
}
