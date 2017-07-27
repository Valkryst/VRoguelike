package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.MoveAction;
import com.valkryst.VRoguelike.entity.builder.EntityBuilder;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.AsciiCharacter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class EntityTest {
    private final Map map = new Map(new Tile[10][10]);
    private final Entity entity = new EntityBuilder().setX(0).setY(0).setSprite(Sprite.PLAYER).build();

    public EntityTest() {
        final Tile[][] tiles = map.getTiles();

        for (int x = 0 ; x < tiles.length ; x++) {
            for (int y = 0 ; y < tiles[x].length ; y++) {
                tiles[x][y] = new Tile(Sprite.DIRT);
            }
        }
    }

    @Test
    public void testConstructor_withValidInput() {
        Assert.assertEquals(0, entity.getLayer().getColumnIndex());
        Assert.assertEquals(0, entity.getLayer().getRowIndex());

        final Optional<AsciiCharacter> optChar = entity.getLayer().getCharacterAt(0, 0);
        Assert.assertTrue(optChar.isPresent());
        Assert.assertEquals(Sprite.PLAYER.getCharacter(), optChar.get().getCharacter());
        Assert.assertEquals(Sprite.PLAYER.getBackgroundColor(), optChar.get().getBackgroundColor());
        Assert.assertEquals(Sprite.PLAYER.getForegroundColor(), optChar.get().getForegroundColor());
    }

    /* UNABLE TO RUN TEST - Requires a Panel for the Layer to be tested.
    @Test
    public void testUpdate_withValidInput() {
        Assert.assertEquals(0, entity.getLayer().getColumnIndex());
        Assert.assertEquals(0, entity.getLayer().getRowIndex());

        entity.addAction(new MoveAction(1, 2));
        entity.update(map);

        Assert.assertEquals(1, entity.getLayer().getColumnIndex());
        Assert.assertEquals(2, entity.getLayer().getRowIndex());
    }
    */

    @Test(expected=NullPointerException.class)
    public void testUpdate_withNullMap() {
        entity.update(null);
    }

    @Test
    public void testAddAction_withValidInput() {
        Assert.assertTrue(entity.addAction(new MoveAction(1, 2)));
    }

    @Test
    public void testAddAction_withNullAction() {
        Assert.assertFalse(entity.addAction(null));
    }

    @Test
    public void testMove_withValidInput() {
        Assert.assertTrue(entity.move(1, 2));
    }

    @Test
    public void testShow_withNullPanel() {
        Assert.assertFalse(entity.show(null));
    }

    @Test
    public void testHide_withNullPanel() {
        Assert.assertFalse(entity.hide(null));
    }

    @Test
    public void testSetSprite_withValidInput() {
        entity.setSprite(Sprite.GRASS);

        final Optional<AsciiCharacter> optChar = entity.getLayer().getCharacterAt(0, 0);
        Assert.assertTrue(optChar.isPresent());
        Assert.assertEquals(Sprite.GRASS.getCharacter(), optChar.get().getCharacter());
        Assert.assertEquals(Sprite.GRASS.getBackgroundColor(), optChar.get().getBackgroundColor());
        Assert.assertEquals(Sprite.GRASS.getForegroundColor(), optChar.get().getForegroundColor());
    }

    @Test
    public void testSetSprite_withNullSprite() {
        entity.setSprite(null);

        final Optional<AsciiCharacter> optChar = entity.getLayer().getCharacterAt(0, 0);
        Assert.assertTrue(optChar.isPresent());
        Assert.assertNotEquals(Sprite.GRASS.getCharacter(), optChar.get().getCharacter());
        Assert.assertNotEquals(Sprite.GRASS.getBackgroundColor(), optChar.get().getBackgroundColor());
        Assert.assertNotEquals(Sprite.GRASS.getForegroundColor(), optChar.get().getForegroundColor());
    }
}
