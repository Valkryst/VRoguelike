package com.valkryst.VRoguelike.components.stats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class StatComponentTest {
    private StatComponent component;

    @Before
    public void initializeComponent() {
        component = new StatComponent("Test", 3, 2, 1);
    }

    @Test
    public void testConstructor_withValidParams() {
        new StatComponent("Test", 3, 2, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNullName() {
        new StatComponent(null, 3, 2, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyName() {
        new StatComponent("", 3, 2, 1);
    }

    @Test
    public void testConstructor_withCurrentValueLessThanMinimum() {
        final StatComponent component = new StatComponent("Test", 3, 0, 1);
        Assert.assertEquals(1, component.getCurrValue());
    }

    @Test
    public void testConstructor_withCurrentValueGreaterThanMaximum() {
        final StatComponent component = new StatComponent("Test", 3, 4, 1);
        Assert.assertEquals(3, component.getCurrValue());
    }

    @Test
    public void testConstructor_withMaxValueLessThanMinimum() {
        final StatComponent component = new StatComponent("Test", 0, 2, 1);
        Assert.assertEquals(1, component.getMaxValue());
    }

    @Test
    public void testToString() {
        final String expected = "StatComponent:\n\tName:\tTest\n\tMaximum Test:\t3\n\tCurrent Test:\t2\n\tMinimum Test:\t1";
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
        final StatComponent componentB = new StatComponent("Test", 3, 2, 1);
        Assert.assertEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualName() {
        final StatComponent componentB = new StatComponent("Test2", 3, 2, 1);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualMaxValue() {
        final StatComponent componentB = new StatComponent("Test", 4, 2, 1);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualCurrentValue() {
        final StatComponent componentB = new StatComponent("Test", 3, 1, 1);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualMinValue() {
        final StatComponent componentB = new StatComponent("Test", 3, 2, 2);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testToJson() {
        final String expected = "{\"name\":Test,\"maxValue\":3,\"currValue\":2,\"minValue\":1}";
        Assert.assertEquals(expected, component.toJson());
    }

    @Test
    public void testSetCurrValue_withValueGreaterThanMaximum() {
        component.setCurrValue(component.getMaxValue() + 1);
        Assert.assertEquals(component.getMaxValue(), component.getCurrValue());
    }

    @Test
    public void testSetCurrValue_withValueLessThanMinimum() {
        component.setCurrValue(component.getMinValue() - 1);
        Assert.assertEquals(component.getMinValue(), component.getCurrValue());
    }

    @Test
    public void testSetCurrValue_withValueBetweenMaxAndMin() {
        component.setCurrValue(component.getMaxValue());
        Assert.assertEquals(component.getMaxValue(), component.getCurrValue());
    }
}