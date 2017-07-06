package com.valkryst.VRoguelike.components.stats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class StatComponentTest {
    private StatComponent<Integer> component;

    @Before
    public void initializeComponent() {
        component = new StatComponent<>("Test", 123);
    }

    @Test
    public void testConstructor_withValidParams() {
        new StatComponent<>("Test", 123);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullName() {
        new StatComponent<>(null, 123);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyName() {
        new StatComponent<>("", 123);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullCurrentValue() {
        new StatComponent<>("Test", null);
    }

    @Test
    public void testToString() {
        final String expected = "StatComponent:\n\tName:\tTest\n\tCurrent Value:\t123";
        Assert.assertEquals(expected, component.toString());
    }

    @Test
    public void testEquals_withNonStatComponentObject() {
        Assert.assertFalse(component.equals(new Rectangle()));
    }

    @Test
    public void testEquals_withSelf() {
        Assert.assertTrue(component.equals(component));
    }

    @Test
    public void testEquals_withEqualObject() {
        final StatComponent<Integer> componentB = new StatComponent<>("Test", 123);
        Assert.assertEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualName() {
        final StatComponent<Integer> componentB = new StatComponent<>("Test2", 123);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualCurrentValue() {
        final StatComponent<Integer> componentB = new StatComponent<>("Test", 456);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testToJson() {
        final String expected = "{\"name\":Test,\"currValue\":123}";
        Assert.assertEquals(expected, component.toJson());
    }
}
