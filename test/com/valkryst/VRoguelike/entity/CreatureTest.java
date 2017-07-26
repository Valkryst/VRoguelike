package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.stat.ResourceStatistic;
import com.valkryst.VRoguelike.stat.Statistic;
import com.valkryst.VTerminal.AsciiCharacter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class CreatureTest {
    private Creature creature;

    @Before
    public void initializeCreature() {
        creature = new Creature(0, 0, Sprite.ENEMY);
    }

    @Test
    public void testConstructor_withValidInput() {
        final Creature creature = new Creature(0, 1, Sprite.ENEMY);
        Assert.assertEquals(0, creature.getX());
        Assert.assertEquals(1, creature.getY());

        Assert.assertEquals("Creature", creature.getName());
        Assert.assertEquals("This is an unnamed creature.", creature.getDescription());

        // Ensure default statistics are present:
        final Optional<Statistic> xp = creature.getStatistic("XP");
        final Optional<Statistic> level = creature.getStatistic("Level");
        final Optional<Statistic> gold = creature.getStatistic("Gold");

        final Optional<Statistic> health = creature.getStatistic("Health");
        final Optional<Statistic> strength = creature.getStatistic("Strength");
        final Optional<Statistic> defense = creature.getStatistic("Defense");

        Assert.assertTrue(xp.isPresent());
        Assert.assertTrue(level.isPresent());
        Assert.assertTrue(gold.isPresent());

        Assert.assertTrue(health.isPresent());
        Assert.assertTrue(strength.isPresent());
        Assert.assertTrue(defense.isPresent());

        // Ensure character sprite was correctly set:
        final Optional<AsciiCharacter> optChar = creature.getLayer().getCharacterAt(0, 0);
        Assert.assertTrue(optChar.isPresent());
        Assert.assertEquals(Sprite.ENEMY.getCharacter(), optChar.get().getCharacter());
        Assert.assertEquals(Sprite.ENEMY.getBackgroundColor(), optChar.get().getBackgroundColor());
        Assert.assertEquals(Sprite.ENEMY.getForegroundColor(), optChar.get().getForegroundColor());
    }

    @Test
    public void testAddStatistic_withValidStatistic() {
        final Statistic statistic = new Statistic("TestStat", 66);
        creature.addStatistic(statistic);

        final Optional<Statistic> optStat = creature.getStatistic("TestStat");
        Assert.assertTrue(optStat.isPresent());
        Assert.assertEquals(statistic, optStat.get());
    }

    @Test
    public void testAddStatistic_withValidResourceStatistic() {
        final ResourceStatistic statistic = new ResourceStatistic("TestStat", 0, 100);
        creature.addStatistic(statistic);

        final Optional<Statistic> optStat = creature.getStatistic("TestStat");
        Assert.assertTrue(optStat.isPresent());
        Assert.assertEquals(statistic, optStat.get());
    }

    @Test(expected=NullPointerException.class)
    public void testAddStatistic_withNullInput() {
        creature.addStatistic(null);
    }

    @Test
    public void testGetStatistic_withExistingName() {
        final Statistic statistic = new Statistic("TestStat", 66);
        creature.addStatistic(statistic);

        final Optional<Statistic> optStat = creature.getStatistic("TestStat");
        Assert.assertTrue(optStat.isPresent());
        Assert.assertEquals(statistic, optStat.get());
    }

    @Test
    public void testGetStatistic_withNonExistingName() {
        final Optional<Statistic> optStat = creature.getStatistic("TestStat");
        Assert.assertFalse(optStat.isPresent());
    }

    @Test(expected=NullPointerException.class)
    public void testGetStatistic_withNullName() {
        creature.getStatistic(null);
    }

    @Test
    public void testGetStatistic_withEmptyName() {
        final Optional<Statistic> optStat = creature.getStatistic("");
        Assert.assertFalse(optStat.isPresent());
    }
}
