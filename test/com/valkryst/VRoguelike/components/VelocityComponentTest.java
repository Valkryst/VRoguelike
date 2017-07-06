package com.valkryst.VRoguelike.components;

import com.valkryst.VRoguelike.enums.Direction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class VelocityComponentTest {
    private VelocityComponent component;

    @Before
    public void initializeComponent() {
        component = new VelocityComponent(123, Direction.SOUTH);
    }

    @Test
    public void testConstructor_noParams() {
        new VelocityComponent();
    }

    @Test
    public void testConstructor_twoParams_withValidParams() {
        final VelocityComponent component = new VelocityComponent(123, Direction.SOUTH);
        Assert.assertEquals(123, component.getSpeed());
        Assert.assertEquals(Direction.SOUTH, component.getDirection());
    }

    @Test
    public void testConstructor_twoParams_withNegativeSpeed() {
        final VelocityComponent component = new VelocityComponent(-1, Direction.SOUTH);
        Assert.assertEquals(0, component.getSpeed());
        Assert.assertEquals(Direction.SOUTH, component.getDirection());
    }

    @Test
    public void testToString() {
        final String actual = component.toString();
        final String expected = "VelocityComponent:\n\tSpeed:\t123\n\tDirection:\tSOUTH(2)";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEquals_withNonVelocityComponentObject() {
        Assert.assertNotEquals(component, new Rectangle());
    }

    @Test
    public void testEquals_withSelf() {
        Assert.assertEquals(component, component);
    }

    @Test
    public void testEquals_withEqualObject() {
        final VelocityComponent componentB = new VelocityComponent(123, Direction.SOUTH);
        Assert.assertEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualSpeed() {
        final VelocityComponent componentB = new VelocityComponent(456, Direction.SOUTH);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testEquals_withNonEqualDirection() {
        final VelocityComponent componentB = new VelocityComponent(123, Direction.NORTH);
        Assert.assertNotEquals(component, componentB);
    }

    @Test
    public void testToJson() {
        final String actual = component.toJson();
        final String expected = "{\"speed\":123,\"direction\":2}";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSetSpeed_withPositiveSpeed() {
        component.setSpeed(456);
        Assert.assertEquals(456, component.getSpeed());
    }

    @Test
    public void testSetSpeed_wothNegativeSpeed() {
        component.setSpeed(-1);
        Assert.assertEquals(0, component.getSpeed());
    }
}
