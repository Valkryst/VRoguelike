package com.valkryst.VRoguelike.components;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class HealthComponentTest {
    private HealthComponent component;

    @Before
    public void initializeComponent() {
        component = new HealthComponent(123);
    }
    @Test
    public void testConstructor_withValidMaxHealth() {
        for (int i = 1 ; i < 10 ; i++) {
            final HealthComponent component = new HealthComponent(i);
            Assert.assertEquals(i, component.getCurHealth());
            Assert.assertEquals(i, component.getMaxHealth());
        }
    }

    @Test
    public void testConstructor_withNegativeMaxHealth() {
        final HealthComponent component = new HealthComponent(-1);
        Assert.assertEquals(1, component.getCurHealth());
        Assert.assertEquals(1, component.getMaxHealth());
    }

    @Test
    public void testToString() {
        final String actual = component.toString();
        final String expected = "HealthComponent:\n\tCurrent Health:\t123\n\tMaximum Health:\t123";
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
        final HealthComponent componentB = new HealthComponent(123);
        Assert.assertEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualObjects() {
        final HealthComponent componentB = new HealthComponent(456);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testToJson() {
        final String actual = component.toJson();
        final String expected = "{\"curHealth\":123,\"maxHealth\":123}";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetCurrentHealth_withValidValue() {
        component.setCurrentHealth(65);
        Assert.assertEquals(65, component.getCurHealth());
    }

    @Test
    public void testSetCurrentHealth_withNegativeValue() {
        component.setCurrentHealth(-456);
        Assert.assertEquals(0, component.getCurHealth());
    }

    @Test
    public void testSetCurrentHealth_withOverMaxValue() {
        component.setCurrentHealth(456);
        Assert.assertEquals(123, component.getCurHealth());
    }
}
