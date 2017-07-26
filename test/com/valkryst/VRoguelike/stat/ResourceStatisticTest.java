package com.valkryst.VRoguelike.stat;

import org.junit.Assert;
import org.junit.Test;

public class ResourceStatisticTest {
    @Test
    public void testConstructor_withValidInput() {
        final ResourceStatistic statistic = new ResourceStatistic("TestStat", 1, 2);
        Assert.assertEquals("TestStat", statistic.getName());
        Assert.assertEquals(2, statistic.getValue());
        Assert.assertEquals(2, statistic.getMaximum());
        Assert.assertEquals(1, statistic.getMinimum());
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_withNullName() {
        new ResourceStatistic(null, 1, 2);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyName() {
        new ResourceStatistic("", 1, 2);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withMaximumLessThanMinimum() {
        new ResourceStatistic("", 2, 1);
    }

    @Test
    public void testToString() {
        final ResourceStatistic statistic = new ResourceStatistic("TestStat", 1, 2);
        Assert.assertTrue(statistic.toString().length() > 0);
    }

    @Test
    public void testHashCode_withEqualStatistics() {
        final ResourceStatistic statisticA = new ResourceStatistic("TestStat", 1, 2);
        final ResourceStatistic statisticB = new ResourceStatistic("TestStat", 1, 2);
        Assert.assertEquals(statisticA.hashCode(), statisticB.hashCode());
    }

    @Test
    public void testHashCode_withNonEqualStatistics() {
        final ResourceStatistic statisticA = new ResourceStatistic("TestStat", 1, 2);
        final ResourceStatistic statisticB = new ResourceStatistic("TestStat", 2, 3);
        Assert.assertNotEquals(statisticA.hashCode(), statisticB.hashCode());
    }

    @Test
    public void testEquals_withNonStatisticObject() {
        final ResourceStatistic statistic = new ResourceStatistic("TestStat", 1, 2);
        Assert.assertNotEquals(statistic, 1234);
    }

    @Test
    public void testEquals_withSelf() {
        final ResourceStatistic statistic = new ResourceStatistic("TestStat", 1, 2);
        Assert.assertEquals(statistic, statistic);
    }

    @Test
    public void testEquals_withEqualObject() {
        final ResourceStatistic statisticA = new ResourceStatistic("TestStat", 1, 2);
        final ResourceStatistic statisticB = new ResourceStatistic("TestStat", 1, 2);
        Assert.assertEquals(statisticA, statisticB);
    }

    @Test
    public void testEquals_withNonEqualObject() {
        final ResourceStatistic statisticA = new ResourceStatistic("TestStat", 1, 2);
        final ResourceStatistic statisticB = new ResourceStatistic("TestStat", 2, 3);
        Assert.assertNotEquals(statisticA, statisticB);
    }
}
