package com.valkryst.VRoguelike.stat;

import org.junit.Assert;
import org.junit.Test;

public class StatisticTest {
    @Test
    public void testConstructor_withValidInput() {
        final Statistic statistic = new Statistic("TestStat", 123);
        Assert.assertEquals("TestStat", statistic.getName());
        Assert.assertEquals(123, statistic.getValue());
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_withNullName() {
        new Statistic(null, 123);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyName() {
        new Statistic("", 123);
    }

    @Test
    public void testToString() {
        final Statistic statistic = new Statistic("TestStat", 123);
        Assert.assertTrue(statistic.toString().length() > 0);
    }

    @Test
    public void testHashCode_withEqualStatistics() {
        final Statistic statisticA = new Statistic("TestStat", 123);
        final Statistic statisticB = new Statistic("TestStat", 123);
        Assert.assertEquals(statisticA.hashCode(), statisticB.hashCode());
    }

    @Test
    public void testHashCode_withNonEqualStatistics() {
        final Statistic statisticA = new Statistic("TestStat", 123);
        final Statistic statisticB = new Statistic("TestStat", 321);
        Assert.assertNotEquals(statisticA.hashCode(), statisticB.hashCode());
    }

    @Test
    public void testEquals_withNonStatisticObject() {
        final Statistic statistic = new Statistic("TestStat", 123);
        Assert.assertNotEquals(statistic, 1234);
    }

    @Test
    public void testEquals_withSelf() {
        final Statistic statistic = new Statistic("TestStat", 123);
        Assert.assertEquals(statistic, statistic);
    }

    @Test
    public void testEquals_withEqualObject() {
        final Statistic statisticA = new Statistic("TestStat", 123);
        final Statistic statisticB = new Statistic("TestStat", 123);
        Assert.assertEquals(statisticA, statisticB);
    }

    @Test
    public void testEquals_withNonEqualObject() {
        final Statistic statisticA = new Statistic("TestStat", 123);
        final Statistic statisticB = new Statistic("TestStat", 321);
        Assert.assertNotEquals(statisticA, statisticB);
    }
}
