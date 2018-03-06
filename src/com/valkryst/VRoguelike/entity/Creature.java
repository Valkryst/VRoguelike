package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.LineOfSight;
import com.valkryst.VRoguelike.ai.combat.CombatAI;
import com.valkryst.VRoguelike.ai.movement.MovementAI;
import com.valkryst.VRoguelike.entity.builder.CreatureBuilder;
import com.valkryst.VRoguelike.enums.Gender;
import com.valkryst.VRoguelike.enums.Race;
import com.valkryst.VRoguelike.enums.State;
import com.valkryst.VRoguelike.item.equipment.EquipmentInventory;
import com.valkryst.VRoguelike.loot.LootTable;
import com.valkryst.VRoguelike.view.GameView;
import com.valkryst.VRoguelike.stat.BoundedStatistic;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.builder.LabelBuilder;
import com.valkryst.VTerminal.component.Label;
import com.valkryst.VTerminal.component.Layer;
import lombok.*;

@EqualsAndHashCode(callSuper=true)
@ToString
public class Creature extends Entity {
    /** The race. */
    @Getter private Race race;

    /** The gender. */
    @Getter private Gender gender;

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

    /** The AI used to handle movement from one tile to another. */
    @Getter private MovementAI movementAI;

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
        gender = builder.getGender();

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
        movementAI = builder.getMovementAI();

        lootTable = builder.getLootTable();
    }

    @Override
    public void update(final @NonNull Map map) {
        movementAI.move(this);
        super.update(map);
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
    public Layer getInformationPanel(final @NonNull GameView gameView) {
        final Layer layer = super.getInformationPanel(gameView);

        // Add Level label and set it to update on change.
        final Runnable add_level = () -> {
            layer.getComponentsByID(stat_level.getName()).forEach(layer::removeComponent);

            final Label label = stat_level.getLabelComponent();
            label.getTiles().setPosition(4, 1);

            layer.addComponent(label);
        };

        stat_level.getOnChangeFunctions().add(add_level);
        add_level.run();

        // Add XP label and set it to update on change.
        final Runnable add_xp = () -> {
            layer.getComponentsByID(stat_xp.getName()).forEach(layer::removeComponent);

            final Label label = stat_xp.getLabelComponentWithMax();
            label.getTiles().setPosition(7, 2);

            layer.addComponent(label);
        };

        stat_xp.getOnChangeFunctions().add(add_xp);
        add_xp.run();

        // Add Gold label and set it to update on change.
        final Runnable add_gold = () -> {
            layer.getComponentsByID(stat_gold.getName()).forEach(layer::removeComponent);

            final Label label = stat_gold.getLabelComponent();
            label.getTiles().setPosition(5, 3);

            layer.addComponent(label);
        };

        stat_gold.getOnChangeFunctions().add(add_gold);
        add_gold.run();

        // Add Health label and set it to update on change.
        final Runnable add_health = () -> {
            layer.getComponentsByID(stat_health.getName()).forEach(layer::removeComponent);

            final Label label;

            if (stat_health.getValue() > 0) {
                label = stat_health.getLabelComponentWithMax();
                label.getTiles().setPosition(3, 4);
            } else {
                final LabelBuilder builder = new LabelBuilder();
                builder.setText("Health: Deceased");
                builder.setPosition(3, 4);
                label = builder.build();
            }

            layer.addComponent(label);
        };

        stat_health.getOnChangeFunctions().add(add_health);
        add_health.run();

        // Add Strength label and set it to update on change.
        final Runnable add_strength = () -> {
            layer.getComponentsByID(stat_strength.getName()).forEach(layer::removeComponent);

            final Label label = stat_strength.getLabelComponent();
            label.getTiles().setPosition(1, 5);

            layer.addComponent(label);
        };

        stat_strength.getOnChangeFunctions().add(add_strength);
        add_strength.run();

        // Add Defense label and set it to update on change.
        final Runnable add_defense = () -> {
            layer.getComponentsByID(stat_defense.getName()).forEach(layer::removeComponent);

            final Label label = stat_defense.getLabelComponent();
            label.getTiles().setPosition(2, 6);

            layer.addComponent(label);
        };

        stat_defense.getOnChangeFunctions().add(add_defense);
        add_defense.run();

        return layer;
    }
}
