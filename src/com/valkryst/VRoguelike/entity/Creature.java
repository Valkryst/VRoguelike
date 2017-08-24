package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.LineOfSight;
import com.valkryst.VRoguelike.ai.CombatAI;
import com.valkryst.VRoguelike.entity.builder.CreatureBuilder;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.enums.State;
import com.valkryst.VRoguelike.item.equipment.EquipmentInventory;
import com.valkryst.VRoguelike.loot.LootTable;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import lombok.*;

@EqualsAndHashCode(callSuper=true)
@ToString
public class Creature extends Entity {
    /** The race. */
    @Getter @Setter private Race race;

    /** The level. */
    @Getter private final BoundedStatistic stat_level;
    /** The experience towards the next level. */
    @Getter private final BoundedStatistic stat_xp;
    /** The amount of gold carried. */
    @Getter private final BoundedStatistic stat_gold;
    /** The health. */
    @Getter private final BoundedStatistic stat_health;
    /** The strength. */
    @Getter private final BoundedStatistic stat_strength;
    /** The defense. */
    @Getter private final BoundedStatistic stat_defense;

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
     *
     * @throws NullPointerException
     *        If the builder is null.
     */
    public Creature(final @NonNull CreatureBuilder builder) {
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

        state = builder.getState();

        combatAI = builder.getCombatAI();

        lootTable = builder.getLootTable();
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
