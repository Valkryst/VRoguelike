package com.valkryst.VRoguelike.components;

import com.valkryst.VRoguelike.components.stats.HealthStat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class HealthStatTest {
    private HealthStat component;

    @Before
    public void initializeComponent() {
        component = new HealthStat(123);
    }
    @Test
    public void testConstructor_withValidMaxHealth() {
        for (int i = 1 ; i < 10 ; i++) {
            final HealthStat component = new HealthStat(i);
            Assert.assertEquals(i, component.getCurrValue().intValue());
            Assert.assertEquals(i, component.getMaxHealth());
        }
    }

    @Test
    public void testConstructor_withNegativeMaxHealth() {
        final HealthStat component = new HealthStat(-1);
        Assert.assertEquals(1, component.getCurrValue().intValue());
        Assert.assertEquals(1, component.getMaxHealth());
    }

    @Test
    public void testToString() {
        final String actual = component.toString();
        final String expected = "HealthStat:\n\tName:\tHealth\n\tCurrent Health:\t123\n\tMaximum Health:\t123";
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
        final HealthStat componentB = new HealthStat(123);
        Assert.assertEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualObjects() {
        final HealthStat componentB = new HealthStat(456);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testToJson() {
        final String actual = component.toJson();
        final String expected = "{\"name\":Health,\"curHealth\":123,\"maxHealth\":123}";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetCurrentHealth_withValidValue() {
        component.setCurrentHealth(65);
        Assert.assertEquals(65, component.getCurrValue().intValue());
    }

    @Test
    public void testSetCurrentHealth_withNegativeValue() {
        component.setCurrentHealth(-456);
        Assert.assertEquals(0, component.getCurrValue().intValue());
    }

    @Test
    public void testSetCurrentHealth_withOverMaxValue() {
        component.setCurrentHealth(456);
        Assert.assertEquals(123, component.getCurrValue().intValue());
    }
}
