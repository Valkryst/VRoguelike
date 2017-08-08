package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.LineOfSight;
import com.valkryst.VRoguelike.ai.CombatAI;
import com.valkryst.VRoguelike.entity.builder.CreatureBuilder;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.enums.State;
import com.valkryst.VRoguelike.item.equipment.EquipmentInventory;
import com.valkryst.VRoguelike.loot.LootTable;
import com.valkryst.VRoguelike.stat.LimitedStatistic;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

public class Creature extends Entity {
    /** The race. */
    @Getter @Setter private Race race;

    /** The level. */
    @Getter private final LimitedStatistic stat_level;
    /** The experience towards the next level. */
    @Getter private final LimitedStatistic stat_xp;
    /** The amount of gold carried. */
    @Getter private final LimitedStatistic stat_gold;
    /** The health. */
    @Getter private final LimitedStatistic stat_health;
    /** The strength. */
    @Getter private final LimitedStatistic stat_strength;
    /** The defense. */
    @Getter private final LimitedStatistic stat_defense;
    /** The accuracy (Percent chance to land an attack). */
    @Getter private final LimitedStatistic stat_accuracy;
    /** The dodge (Percent chance to dodge an attack). */
    @Getter private final LimitedStatistic stat_dodge;

    /** The equipment inventory. */
    @Getter private final EquipmentInventory equipment;

    /** A collection of all tiles that are currently visible to the creature. */
    @Getter private LineOfSight lineOfSight;

    /** The current state. */
    @Getter @Setter @NonNull private State state;

    /** The decision-making AI used to handle combat. */
    @Getter private CombatAI combatAI;

    /** The loot table. */
    @Getter private final LootTable lootTable;

    /**
     * Constructs a new Creature.
     *
     * @param builder
     *        The builder.
     */
    public Creature(final CreatureBuilder builder) {
        super(builder);
        race = builder.getRace();

        stat_level = builder.getStat_level();
        stat_xp = builder.getStat_xp();
        stat_gold = builder.getStat_gold();
        stat_health = builder.getStat_health();
        stat_strength = builder.getStat_strength();
        stat_defense = builder.getStat_defense();
        stat_accuracy = builder.getStat_accuracy();
        stat_dodge = builder.getStat_dodge();

        equipment = builder.getEquipment();

        lineOfSight = new LineOfSight(this, builder.getLineOfSightRadius());

        state = builder.getState();

        combatAI = builder.getCombatAI();

        lootTable = builder.getLootTable();
    }

    @Override
    public String toString() {
        return "Creature" + super.toString().substring(6) +
                "\tRace:\t" + race.name() + "\n" +
                "\tStatistics:\n" +
                "\t\t" + stat_level.toString().replace("\n\t", "\n\t\t\t") + "\n" +
                "\t\t" + stat_xp.toString().replace("\n\t", "\n\t\t\t") + "\n" +
                "\t\t" + stat_gold.toString().replace("\n\t", "\n\t\t\t") + "\n" +
                "\t\t" + stat_health.toString().replace("\n\t", "\n\t\t\t") + "\n" +
                "\t\t" + stat_strength.toString().replace("\n\t", "\n\t\t\t") + "\n" +
                "\t\t" + stat_defense.toString().replace("\n\t", "\n\t\t\t") + "\n" +
                "\tCombatAI:\t" + combatAI.getClass().getSimpleName() +
                lootTable.toString().replace("\n\t", "\n\t\t");
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(race, stat_level, stat_xp, stat_gold, stat_health, stat_strength,
                stat_defense, stat_accuracy, stat_dodge, equipment, lineOfSight, combatAI, lootTable);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Entity == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final Creature otherCreature = (Creature) otherObj;

        boolean isEqual = super.equals(otherObj);
        isEqual &= Objects.equals(race, otherCreature.getRace());
        isEqual &= Objects.equals(stat_level, otherCreature.getStat_level());
        isEqual &= Objects.equals(stat_xp, otherCreature.getStat_xp());
        isEqual &= Objects.equals(stat_gold, otherCreature.getStat_gold());
        isEqual &= Objects.equals(stat_health, otherCreature.getStat_health());
        isEqual &= Objects.equals(stat_strength, otherCreature.getStat_strength());
        isEqual &= Objects.equals(stat_defense, otherCreature.getStat_defense());
        isEqual &= Objects.equals(stat_accuracy, otherCreature.getStat_accuracy());
        isEqual &= Objects.equals(stat_dodge, otherCreature.getStat_dodge());
        isEqual &= Objects.equals(equipment, otherCreature.getEquipment());
        isEqual &= Objects.equals(lineOfSight, otherCreature.getLineOfSight());
        isEqual &= Objects.equals(combatAI, otherCreature.getCombatAI());
        isEqual &= Objects.equals(lootTable, otherCreature.getLootTable());
        return isEqual;
    }

    /**
     * Sets the sight radius.
     *
     * @param sightRadius
     *        The sight radius.
     */
    public void setLineOfSight(final int sightRadius) {
        lineOfSight = new LineOfSight(this, sightRadius);
    }
}
