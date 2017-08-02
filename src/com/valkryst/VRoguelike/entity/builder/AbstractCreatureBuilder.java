package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.ai.CombatAI;
import com.valkryst.VRoguelike.ai.PassiveCombatAI;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.item.equipment.EquipmentInventory;
import com.valkryst.VRoguelike.item.equipment.EquipmentSlot;
import com.valkryst.VRoguelike.item.equipment.EquippableItem;
import com.valkryst.VRoguelike.stat.LimitedStatistic;
import lombok.Getter;

import java.util.Objects;

public abstract class AbstractCreatureBuilder<B extends AbstractEntityBuilder<B>> extends AbstractEntityBuilder<B> {
    /** The race. */
    @Getter private Race race;

    /** The level. */
    @Getter private LimitedStatistic stat_level;
    /** The experience towards the next level. */
    @Getter private LimitedStatistic stat_xp;
    /** The amount of gold carried. */
    @Getter private LimitedStatistic stat_gold;
    /** The health. */
    @Getter private LimitedStatistic stat_health;
    /** The strength. */
    @Getter private LimitedStatistic stat_strength;
    /** The defense. */
    @Getter private LimitedStatistic stat_defense;

    /** The equipment inventory. */
    @Getter private EquipmentInventory equipment;

    /** The line of sight radius. */
    @Getter private int lineOfSightRadius;

    /** The decision-making AI used to handle combat. */
    @Getter private CombatAI combatAI;

    @Override
    public void reset() {
        super.reset();
        super.setName("Creature");
        super.setDescription("This is an unnamed Creature.");
        race = null;
        equipment = new EquipmentInventory();
        lineOfSightRadius = 4;
        combatAI = new PassiveCombatAI();

        // Set Stats:
        stat_level = new LimitedStatistic("Level", 1, 1, 200);
        stat_xp = new LimitedStatistic("XP", 0, 83);
        stat_gold = new LimitedStatistic("Gold", 0, 0, Integer.MAX_VALUE);
        stat_health = new LimitedStatistic("Health", 0, 100);
        stat_strength = new LimitedStatistic("Strength", 0, 100);
        stat_defense = new LimitedStatistic("Defense", 0, 100);

        // Level When XP Full:
        stat_xp.setOnChange(() -> {
            if (stat_xp.getValue() == stat_xp.getMaximum()) {
                stat_level.setValue(stat_level.getValue() + 1);
            }
        });

        // Set New XP Goal on Levelup:
        stat_level.setOnChange(() -> {
            double newXp = 100;

            for (int i = 1 ; i < stat_level.getValue() ; i++) {
                newXp += newXp * 0.089;
            }

            stat_xp.setMinimum(0);
            stat_xp.setValue(0);
            stat_xp.setMaximum((int) newXp);
        });
    }

    @Override
    protected void checkState() {
        super.checkState();
        Objects.requireNonNull(race);
        Objects.requireNonNull(combatAI);
    }

    /**
     * Sets the race.
     *
     * @param race
     *        The race.
     *
     * @return
     *        This.
     */
    public B setRace(final Race race) {
        this.race = race;
        return (B) this;
    }

    /**
     * Sets the level stat.
     *
     * @param stat_level
     *        The level stat.
     *
     * @return
     *        This.
     */
    public B setStat_level(final LimitedStatistic stat_level) {
        this.stat_level = stat_level;
        return (B) this;
    }

    /**
     * Sets the xp stat.
     *
     * @param stat_xp
     *        The xp stat.
     *
     * @return
     *        This.
     */
    public B setStat_xp(final LimitedStatistic stat_xp) {
        this.stat_xp = stat_xp;
        return (B) this;
    }

    /**
     * Sets the gold stat.
     *
     * @param stat_gold
     *        The gold stat.
     *
     * @return
     *        This.
     */
    public B setStat_gold(final LimitedStatistic stat_gold) {
        this.stat_gold = stat_gold;
        return (B) this;
    }

    /**
     * Sets the health stat.
     *
     * @param stat_health
     *        The health stat.
     *
     * @return
     *        This.
     */
    public B setStat_health(final LimitedStatistic stat_health) {
        this.stat_health = stat_health;
        return (B) this;
    }

    /**
     * Sets the strength stat.
     *
     * @param stat_strength
     *        The strength stat.
     *
     * @return
     *        This.
     */
    public B setStat_strength(final LimitedStatistic stat_strength) {
        this.stat_strength = stat_strength;
        return (B) this;
    }

    /**
     * Sets the defense stat.
     *
     * @param stat_defense
     *        The defense stat.
     *
     * @return
     *        This.
     */
    public B setStat_defense(final LimitedStatistic stat_defense) {
        this.stat_defense = stat_defense;
        return (B) this;
    }

    /**
     * Adds the item in a specific slot.
     *
     * This will override any equipment already in the slot.
     *
     * @param slot
     *        The slot.
     *
     * @param item
     *        The item.
     *
     * @return
     *        This.
     */
    public B addEquipment(final EquipmentSlot slot, final EquippableItem item) {
        equipment.setItemInSlot(slot, item);
        return (B) this;
    }

    /**
     * Sets the line of sight radius.
     *
     * @param lineOfSightRadius
     *        The line of sight radius.
     *
     * @return
     *        This.
     */
    public B setLineOfSightRadius(final int lineOfSightRadius) {
        this.lineOfSightRadius = lineOfSightRadius;
        return (B) this;
    }

    /**
     * Sets the combat AI.
     *
     * @param combatAI
     *        The combat AI.
     *
     * @return
     *        This.
     */
    public B setCombatAI(final CombatAI combatAI) {
        this.combatAI = combatAI;
        return (B) this;
    }
}
