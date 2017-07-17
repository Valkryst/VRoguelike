package com.valkryst.VRoguelike.action;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Tile;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.world.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoveActionTest {
    private MoveAction moveAction;
    private final Map map = new Map(new Tile[10][10]);
    private final Entity entity = new Entity(0, 0, Sprite.PLAYER);

    public MoveActionTest() {
        final Tile[][] tiles = map.getTiles();

        for (int x = 0 ; x < tiles.length ; x++) {
            for (int y = 0 ; y < tiles[x].length ; y++) {
                tiles[x][y] = new Tile(Sprite.DIRT);
            }
        }
    }

    @Before
    public void initalizeAction() {
        moveAction = new MoveAction(1, 2);
    }

    @Test
    public void testConstructor_withValidInput() {
        Assert.assertEquals(1, moveAction.getDx());
        Assert.assertEquals(2, moveAction.getDy());
    }

    /* UNABLE TO RUN TEST - Requires a Panel for the Layer to be tested.
    @Test
    public void testPerform_withValidInput() {
        Assert.assertEquals(0, entity.getLayer().getColumnIndex());
        Assert.assertEquals(0, entity.getLayer().getRowIndex());

        moveAction.perform(map, entity);

        Assert.assertEquals(1, entity.getLayer().getColumnIndex());
        Assert.assertEquals(2, entity.getLayer().getRowIndex());
    }
    */

    @Test(expected=NullPointerException.class)
    public void testPerform_withNullMap() {
        moveAction.perform(null, entity);
    }

    @Test(expected=NullPointerException.class)
    public void testPerform_withNullEntity() {
        moveAction.perform(map, null);
    }
}
