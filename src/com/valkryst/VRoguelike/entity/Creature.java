package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.LineOfSight;
import com.valkryst.VRoguelike.ai.CombatAI;
import com.valkryst.VRoguelike.entity.builder.AbstractCreatureBuilder;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.item.equipment.EquipmentInventory;
import com.valkryst.VRoguelike.stat.LimitedStatistic;
import lombok.Getter;
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

    /** The equipment inventory. */
    @Getter private final EquipmentInventory equipment;

    /** A collection of all tiles that are currently visible to the creature. */
    @Getter private LineOfSight lineOfSight;

    /** The decision-making AI used to handle combat. */
    @Getter private CombatAI combatAI;

    /**
     * Constructs a new Creature.
     *
     * @param builder
     *        The builder.
     */
    public Creature(final AbstractCreatureBuilder builder) {
        super(builder);
        race = builder.getRace();

        stat_level = builder.getStat_level();
        stat_xp = builder.getStat_xp();
        stat_gold = builder.getStat_gold();
        stat_health = builder.getStat_health();
        stat_strength = builder.getStat_strength();
        stat_defense = builder.getStat_defense();

        equipment = builder.getEquipment();

        lineOfSight = new LineOfSight(this, builder.getLineOfSightRadius());

        combatAI = builder.getCombatAI();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Creature").append(super.toString().substring(6));
        sb.append("\tRace:\t").append(race.name()).append("\n");

        sb.append("\tStatistics:\n");
        sb.append("\t\t").append(stat_level.toString().replace("\n\t", "\n\t\t\t")).append("\n");
        sb.append("\t\t").append(stat_xp.toString().replace("\n\t", "\n\t\t\t")).append("\n");
        sb.append("\t\t").append(stat_gold.toString().replace("\n\t", "\n\t\t\t")).append("\n");
        sb.append("\t\t").append(stat_health.toString().replace("\n\t", "\n\t\t\t")).append("\n");
        sb.append("\t\t").append(stat_strength.toString().replace("\n\t", "\n\t\t\t")).append("\n");
        sb.append("\t\t").append(stat_defense.toString().replace("\n\t", "\n\t\t\t")).append("\n");

        sb.append("\tCombatAI:\t").append(combatAI.getClass().getSimpleName());

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hashCode(race) + Objects.hashCode(stat_level) + Objects.hashCode(stat_xp)
               + Objects.hashCode(stat_gold) + Objects.hashCode(stat_health) + Objects.hashCode(stat_strength)
               + Objects.hashCode(stat_defense) + Objects.hashCode(equipment) + Objects.hashCode(lineOfSight)
               + Objects.hashCode(combatAI);
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
        isEqual &= Objects.equals(equipment, otherCreature.getEquipment());
        isEqual &= Objects.equals(lineOfSight, otherCreature.getLineOfSight());
        isEqual &= Objects.equals(combatAI, otherCreature.getCombatAI());
        return isEqual;
    }

    /**
     * Sets the sight radius.
     *
     * @param sightRadius
     *        The sight radius.
     */
    public void setSightRadius(final int sightRadius) {
        lineOfSight = new LineOfSight(this, sightRadius);
    }
}
