package com.valkryst.VRoguelike.item;

import com.valkryst.VRoguelike.stat.ResourceStatistic;
import com.valkryst.VRoguelike.stat.Statistic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemTest {
    private Item item;

    @Before
    public void initializeItem() {
        item = new Item("TestName", "TestDescription");
    }

    @Test
    public void testConstructor_twoParams_withValidInput() {
        Assert.assertEquals("TestName", item.getName());
        Assert.assertEquals("TestDescription", item.getDescription());
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_twoParams_withNullName() {
        new Item(null, "TestDescription");
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_twoParams_withNullDescription() {
        new Item("TestName", null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_twoParams_withEmptyName() {
        new Item("", "TestDescription");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_twoParams_withemptyDescription() {
        new Item("TestName", "");
    }

    @Test
    public void testConstructor_threeParams_withValidInput() {
        final List<Statistic> statistics = new ArrayList<>();
        statistics.add(new Statistic("TestStat", 1));

        final Item item = new Item("TestName", "TestDescription", statistics);
        Assert.assertEquals("TestName", item.getName());
        Assert.assertEquals("TestDescription", item.getDescription());

        for (final Statistic listStat : statistics) {
            Assert.assertTrue(item.getStatistics().containsKey(listStat.getName()));
            Assert.assertEquals(listStat, item.getStatistics().get(listStat.getName()));
        }
    }

    @Test(expected=NullPointerException.class)
    public void testConstructor_threeParams_withNullStatistics() {
        new Item("TestName", "TestDescription", null);
    }

    @Test
    public void testToString() {
        Assert.assertTrue(item.toString().length() > 0);
    }

    @Test
    public void testHashCode_withEqualItem() {
        final Item itemB = new Item("TestName", "TestDescription");
        Assert.assertEquals(item.hashCode(), itemB.hashCode());
    }

    @Test
    public void testHashCode_withNonEqualItem() {
        final Item itemB = new Item("TestName", "DXR%FB&^5rg");
        Assert.assertNotEquals(item.hashCode(), itemB.hashCode());
    }

    @Test
    public void testEquals_withDifferentObject() {
        Assert.assertNotEquals(item, 1234);
    }

    @Test
    public void testEquals_withSelf() {
        Assert.assertEquals(item, item);
    }

    @Test
    public void testEquals_withEqualItem() {
        final Item itemB = new Item("TestName", "TestDescription");
        Assert.assertEquals(item, itemB);
    }

    @Test
    public void testEquals_withNonEqualItem() {
        final Item itemB = new Item("TestName", "2vgw45766yh");
        Assert.assertNotEquals(item, itemB);
    }

    @Test
    public void testAddStatistic_withValidStatistic() {
        final Statistic statistic = new Statistic("TestStat", 66);
        item.addStatistic(statistic);

        final Optional<Statistic> optStat = item.getStatistic("TestStat");
        Assert.assertTrue(optStat.isPresent());
        Assert.assertEquals(statistic, optStat.get());
    }

    @Test
    public void testAddStatistic_withValidResourceStatistic() {
        final ResourceStatistic statistic = new ResourceStatistic("TestStat", 0, 100);
        item.addStatistic(statistic);

        final Optional<Statistic> optStat = item.getStatistic("TestStat");
        Assert.assertTrue(optStat.isPresent());
        Assert.assertEquals(statistic, optStat.get());
    }

    @Test(expected=NullPointerException.class)
    public void testAddStatistic_withNullInput() {
        item.addStatistic(null);
    }

    @Test
    public void testGetStatistic_withExistingName() {
        final Statistic statistic = new Statistic("TestStat", 66);
        item.addStatistic(statistic);

        final Optional<Statistic> optStat = item.getStatistic("TestStat");
        Assert.assertTrue(optStat.isPresent());
        Assert.assertEquals(statistic, optStat.get());
    }

    @Test
    public void testGetStatistic_withNonExistingName() {
        final Optional<Statistic> optStat = item.getStatistic("TestStat");
        Assert.assertFalse(optStat.isPresent());
    }

    @Test(expected=NullPointerException.class)
    public void testGetStatistic_withNullName() {
        item.getStatistic(null);
    }

    @Test
    public void testGetStatistic_withEmptyName() {
        final Optional<Statistic> optStat = item.getStatistic("");
        Assert.assertFalse(optStat.isPresent());
    }
}
