package com.valkryst.VRoguelike.stat;

import org.junit.Assert;
import org.junit.Test;

public class ResourceStatisticTest {
    @Test
    public void testConstructor_withValidInput() {
        final LimitedStatistic statistic = new LimitedStatistic("TestStat", 1, 2);
        Assert.assertEquals("TestStat", statistic.getName());
        Assert.assertEquals(2, statistic.getValue());
        Assert.assertEquals(2, statistic.getMaximum());
        Assert.assertEquals(1, statistic.getMinimum());
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_withNullName() {
        new LimitedStatistic(null, 1, 2);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyName() {
        new LimitedStatistic("", 1, 2);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withMaximumLessThanMinimum() {
        new LimitedStatistic("", 2, 1);
    }

    @Test
    public void testToString() {
        final LimitedStatistic statistic = new LimitedStatistic("TestStat", 1, 2);
        Assert.assertTrue(statistic.toString().length() > 0);
    }

    @Test
    public void testHashCode_withEqualStatistics() {
        final LimitedStatistic statisticA = new LimitedStatistic("TestStat", 1, 2);
        final LimitedStatistic statisticB = new LimitedStatistic("TestStat", 1, 2);
        Assert.assertEquals(statisticA.hashCode(), statisticB.hashCode());
    }

    @Test
    public void testHashCode_withNonEqualStatistics() {
        final LimitedStatistic statisticA = new LimitedStatistic("TestStat", 1, 2);
        final LimitedStatistic statisticB = new LimitedStatistic("TestStat", 2, 3);
        Assert.assertNotEquals(statisticA.hashCode(), statisticB.hashCode());
    }

    @Test
    public void testEquals_withNonStatisticObject() {
        final LimitedStatistic statistic = new LimitedStatistic("TestStat", 1, 2);
        Assert.assertNotEquals(statistic, 1234);
    }

    @Test
    public void testEquals_withSelf() {
        final LimitedStatistic statistic = new LimitedStatistic("TestStat", 1, 2);
        Assert.assertEquals(statistic, statistic);
    }

    @Test
    public void testEquals_withEqualObject() {
        final LimitedStatistic statisticA = new LimitedStatistic("TestStat", 1, 2);
        final LimitedStatistic statisticB = new LimitedStatistic("TestStat", 1, 2);
        Assert.assertEquals(statisticA, statisticB);
    }

    @Test
    public void testEquals_withNonEqualObject() {
        final LimitedStatistic statisticA = new LimitedStatistic("TestStat", 1, 2);
        final LimitedStatistic statisticB = new LimitedStatistic("TestStat", 2, 3);
        Assert.assertNotEquals(statisticA, statisticB);
    }
}
