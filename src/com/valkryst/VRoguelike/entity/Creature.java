package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.LineOfSight;
import com.valkryst.VRoguelike.ai.CombatAI;
import com.valkryst.VRoguelike.entity.builder.CreatureBuilder;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.enums.State;
import com.valkryst.VRoguelike.item.equipment.EquipmentInventory;
import com.valkryst.VRoguelike.loot.LootTable;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import com.valkryst.VTerminal.component.Label;
import com.valkryst.VTerminal.component.Screen;
import lombok.*;

import java.awt.Point;

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

    @Override
    public Screen getInformationPanel() {
        final Screen screen = super.getInformationPanel();

        // Add Level label and set it to update on change.
        final Runnable add_level = () -> {
            screen.removeComponent(screen.getComponentByID(stat_level.getName()));

            final Label label = stat_level.getLabelComponent();
            label.setPosition(new Point(0, 1));

            screen.addComponent(label);
        };

        stat_level.getOnChangeFunctions().add(add_level);

        // Add XP label and set it to update on change.
        final Runnable add_xp = () -> {
            screen.removeComponent(screen.getComponentByID(stat_xp.getName()));

            final Label label = stat_xp.getLabelComponent();
            label.setPosition(new Point(0, 2));

            screen.addComponent(label);
        };

        stat_xp.getOnChangeFunctions().add(add_xp);

        // Add Gold label and set it to update on change.
        final Runnable add_gold = () -> {
            screen.removeComponent(screen.getComponentByID(stat_gold.getName()));

            final Label label = stat_gold.getLabelComponent();
            label.setPosition(new Point(0, 3));

            screen.addComponent(label);
        };

        stat_gold.getOnChangeFunctions().add(add_gold);

        // Add Health label and set it to update on change.
        final Runnable add_health = () -> {
            screen.removeComponent(screen.getComponentByID(stat_health.getName()));

            final Label label = stat_health.getLabelComponent();
            label.setPosition(new Point(0, 4));

            screen.addComponent(label);
        };

        stat_health.getOnChangeFunctions().add(add_health);

        // Add Strength label and set it to update on change.
        final Runnable add_strength = () -> {
            screen.removeComponent(screen.getComponentByID(stat_strength.getName()));

            final Label label = stat_strength.getLabelComponent();
            label.setPosition(new Point(0, 5));

            screen.addComponent(label);
        };

        stat_strength.getOnChangeFunctions().add(add_strength);

        // Add Strength label and set it to update on change.
        final Runnable add_defense = () -> {
            screen.removeComponent(screen.getComponentByID(stat_defense.getName()));

            final Label label = stat_defense.getLabelComponent();
            label.setPosition(new Point(0, 5));

            screen.addComponent(label);
        };

        stat_defense.getOnChangeFunctions().add(add_defense);

        return screen;
    }
}
