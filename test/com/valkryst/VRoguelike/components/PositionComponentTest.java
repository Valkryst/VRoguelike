package com.valkryst.VRoguelike.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class PositionComponentTest {
    private PositionComponent component;

    @Before
    public void initializeComponent() {
        component = new PositionComponent(123, 456);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals(123, component.getX());
        Assert.assertEquals(456, component.getY());
    }

    @Test
    public void testToString() {
        final String actual = component.toString();
        final String expected = "PositionComponent:\n\tx:\t123\n\ty:\t456";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEquals_withNonHealthComponentObject() {
        Assert.assertNotEquals(component, new Rectangle());
    }

    @Test
    public void testEquals_withSelf() {
        Assert.assertEquals(component, component);
    }

    @Test
    public void testEquals_withEqualObject() {
        final PositionComponent componentB = new PositionComponent(123, 456);
        Assert.assertEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualObjects() {
        final PositionComponent componentB = new PositionComponent(456, 123);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testToJson() {
        final String actual = component.toJson();
        final String expected = "{\"x\":123,\"y\":456}";
        Assert.assertEquals(expected, actual);
    }
}
