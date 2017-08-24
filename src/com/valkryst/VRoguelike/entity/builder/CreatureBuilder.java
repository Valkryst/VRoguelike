package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.ai.CombatAI;
import com.valkryst.VRoguelike.ai.PassiveCombatAI;
import com.valkryst.VRoguelike.entity.Creature;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.enums.State;
import com.valkryst.VRoguelike.item.equipment.EquipmentInventory;
import com.valkryst.VRoguelike.loot.LootTable;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@EqualsAndHashCode(callSuper=true)
@ToString
public class CreatureBuilder extends EntityBuilder {
    /** The race. */
    @Getter @Setter private Race race;

    /** The level. */
    @Getter @Setter private BoundedStatistic stat_level;
    /** The experience towards the next level. */
    @Getter @Setter private BoundedStatistic stat_xp;
    /** The amount of gold carried. */
    @Getter @Setter private BoundedStatistic stat_gold;
    /** The health. */
    @Getter @Setter private BoundedStatistic stat_health;
    /** The strength. */
    @Getter @Setter private BoundedStatistic stat_strength;
    /** The defense. */
    @Getter @Setter private BoundedStatistic stat_defense;

    /** The equipment inventory. */
    @Getter @Setter private EquipmentInventory equipment;

    /** The line of sight radius. */
    @Getter @Setter private int lineOfSightRadius;

    /** The current state. */
    @Getter @Setter private State state;

    /** The decision-making AI used to handle combat. */
    @Getter @Setter private CombatAI combatAI;

    /** The loot table. */
    @Getter @Setter private LootTable lootTable;

    public Creature build() {
        checkState();
        return new Creature(this);
    }

    @Override
    public void reset() {
        super.reset();
        super.setName("Creature");
        super.setDescription("This is an unnamed Creature.");
        race = null;
        equipment = new EquipmentInventory();
        lineOfSightRadius = 4;
        state = State.IDLE;
        combatAI = new PassiveCombatAI();
        lootTable = new LootTable();

        // Set Stats:
        stat_level = new BoundedStatistic("Level", 1, 1, 200);
        stat_xp = new BoundedStatistic("XP", 0, 83);
        stat_gold = new BoundedStatistic("Gold", 0, 0, Integer.MAX_VALUE);
        stat_health = new BoundedStatistic("Health", 0, 100);
        stat_strength = new BoundedStatistic("Strength", 0, 100);
        stat_defense = new BoundedStatistic("Defense", 0, 100);

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
        Objects.requireNonNull(lootTable);
    }
}
