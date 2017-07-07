package com.valkryst.VRoguelike.components;

import com.valkryst.VRoguelike.enums.Sprite;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class SpriteComponentTest {
    private SpriteComponent component;

    @Before
    public void initializeComponent() {
        component = new SpriteComponent(Sprite.DIRT, new PositionComponent(10, 10));
    }

    @Test
    public void testConstructor_withValidParams() {
        new SpriteComponent(Sprite.DIRT, new PositionComponent(10, 10));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullSprite() {
        new SpriteComponent(null, new PositionComponent(10, 10));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullPosition() {
        new SpriteComponent(Sprite.DIRT, null);
    }

    @Test
    public void testToString() {
        final String actual = component.toString();

        String expected = "SpriteComponent:";
        expected += "\n\tSprite:\t";
        expected += "\n\t\tCharacter:\t" + Sprite.DIRT.getCharacter();
        expected += "\n\t\tBackground Color:\t" + Sprite.DIRT.getBackgroundColor();
        expected += "\n\t\tForeground Color:\t" + Sprite.DIRT.getForegroundColor();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEquals_withNonSpriteComponentObject() {
        Assert.assertNotEquals(component, new Rectangle());
    }

    @Test
    public void testEquals_withSelf() {
        Assert.assertEquals(component, component);
    }

    @Test
    public void testEquals_withEqualObject() {
        final SpriteComponent componentB = new SpriteComponent(Sprite.DIRT, new PositionComponent(10, 10));
        Assert.assertEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualSprite() {
        final SpriteComponent componentB = new SpriteComponent(Sprite.GRASS, new PositionComponent(10, 10));
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualPosition() {
        final SpriteComponent componentB = new SpriteComponent(Sprite.DIRT, new PositionComponent(11, 10));
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testToJson() {
        final String actual = component.toJson();
        final String expected = "{\"sprite\":DIRT}";
        Assert.assertEquals(expected, actual);
    }
}
