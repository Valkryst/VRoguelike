package com.valkryst.VRoguelike.components;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class HealthComponentTest {
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
        final HealthComponent component = new HealthComponent(123);
        final String actual = component.toString();
        final String expected = "HealthComponent:\n\tCurrent Health:\t123\n\tMaximum Health:\t123";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEquals_withNonHealthComponentObject() {
        final HealthComponent component = new HealthComponent(123);
        Assert.assertNotEquals(component, new Rectangle());
    }

    @Test
    public void testEquals_withSelf() {
        final HealthComponent component = new HealthComponent(123);
        Assert.assertEquals(component, component);
    }

    @Test
    public void testEquals_withEqualObject() {
        final HealthComponent componentA = new HealthComponent(123);
        final HealthComponent componentB = new HealthComponent(123);
        Assert.assertEquals(componentA, componentB);
    }

    @Test
    public void testEquals_withNonEqualObjects() {
        final HealthComponent componentA = new HealthComponent(123);
        final HealthComponent componentB = new HealthComponent(456);
        Assert.assertNotEquals(componentA, componentB);
    }

    @Test
    public void testToJson() {
        final HealthComponent component = new HealthComponent(123);
        final String actual = component.toJson();
        final String expected = "{\"curHealth\":123,\"maxHealth\":123}";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetCurrentHealth_withValidValue() {
        final HealthComponent component = new HealthComponent(123);
        Assert.assertEquals(123, component.getCurHealth());

        component.setCurrentHealth(65);
        Assert.assertEquals(65, component.getCurHealth());
    }

    @Test
    public void testSetCurrentHealth_withNegativeValue() {
        final HealthComponent component = new HealthComponent(123);
        Assert.assertEquals(123, component.getCurHealth());

        component.setCurrentHealth(-456);
        Assert.assertEquals(0, component.getCurHealth());
    }

    @Test
    public void testSetCurrentHealth_withOverMaxValue() {
        final HealthComponent component = new HealthComponent(123);
        Assert.assertEquals(123, component.getCurHealth());

        component.setCurrentHealth(456);
        Assert.assertEquals(123, component.getCurHealth());
    }
}
