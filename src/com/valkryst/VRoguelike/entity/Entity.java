package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.Action;
import com.valkryst.VRoguelike.action.HideAction;
import com.valkryst.VRoguelike.action.MoveAction;
import com.valkryst.VRoguelike.action.ShowAction;
import com.valkryst.VRoguelike.entity.builder.EntityBuilder;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.AsciiCharacter;
import com.valkryst.VTerminal.AsciiString;
import com.valkryst.VTerminal.builder.component.LabelBuilder;
import com.valkryst.VTerminal.builder.component.LayerBuilder;
import com.valkryst.VTerminal.builder.component.ScreenBuilder;
import com.valkryst.VTerminal.component.Layer;
import com.valkryst.VTerminal.component.Screen;
import lombok.*;

import java.awt.Point;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@EqualsAndHashCode
@ToString
public class Entity {
    /** The name of the entity. */
    @Getter @Setter private String name;

    /** A description of the entity. */
    @Getter @Setter private String description;

    /** The action to perform. */
    @Getter private final Queue<Action> actions = new ConcurrentLinkedQueue<>();

    /** The layer-component on which the entity is drawn. */
    @Getter private Layer layer;

    /**
     * Constructs a new entity.
     *
     * @param builder
     *        The builder.
     *
     * @throws NullPointerException
     *        If the builder is null.
     */
    public Entity(final @NonNull EntityBuilder builder) {
        name = builder.getName();
        description = builder.getDescription();

        final LayerBuilder layerBuilder = new LayerBuilder();
        layerBuilder.setColumnIndex(builder.getPosition().x);
        layerBuilder.setRowIndex(builder.getPosition().y);
        layerBuilder.setWidth(1);
        layerBuilder.setHeight(1);
        layer = layerBuilder.build();

        setSprite(builder.getSprite());
    }

    /**
     * Updates the entity.
     *
     * @param map
     *        The map that the entity exists on.
     *
     * @throws NullPointerException
     *        If the map or message box is null.
     */
    public void update(final @NonNull Map map) {
        actions.forEach(action -> action.perform(map,this));
        actions.clear();
    }

    /**
     * Adds an action to the entity.
     *
     * @param action
     *        The action.
     *
     * @return
     *        If the action was added.
     *
     * @throws NullPointerException
     *        If the action is null.
     */
    public boolean addAction(final @NonNull Action action) {
        if (action != null) {
            actions.add(action);
            return true;
        }

        return false;
    }

    /**
     * Adds a move action to the entity.
     *
     * @param dx
     *        The change in x-axis position.
     *
     * @param dy
     *        The change in y-axis position.
     *
     * @return
     *        If the action was created and added.
     */
    public boolean move(final int dx, final int dy) {
        actions.add(new MoveAction(getPosition(), dx, dy));
        return true;
    }

    /**
     * Adds a show action to the entity.
     *
     * @return
     *        If the action was created and added.
     */
    public boolean show() {
        actions.add(new ShowAction());
        return true;
    }

    /**
     * Adds a hide action to the entity.
     *
     * @return
     *        If the action was created and added.
     */
    public boolean hide() {
        actions.add(new HideAction());
        return true;
    }

    public Screen getInformationPanel() {
        final ScreenBuilder screenBuilder = new ScreenBuilder();
        screenBuilder.setWidth(37);
        screenBuilder.setHeight(8);

        final LabelBuilder labelBuilder = new LabelBuilder();
        labelBuilder.setRowIndex(0);
        labelBuilder.setColumnIndex(0);
        labelBuilder.setText("Name: " + name);

        final Screen screen = screenBuilder.build();
        screen.addComponent(labelBuilder.build());

        return screen;
    }

    /**
     * Retrieves an AsciiString containing the Entity's name
     * with the same foreground color as it's sprite.
     *
     * @return
     *         The Entity's name with the same foreground
     *         color as it's sprite.
     */
    public AsciiString getColoredName() {
        final AsciiString asciiString = new AsciiString(name);
        asciiString.setForegroundColor(getSprite().getForegroundColor());
        return asciiString;
    }

    /**
     * Retrieves the current sprite character.
     *
     * @return
     *         The sprite character.
     */
    public AsciiCharacter getSprite() {
        return layer.getCharacterAt(new Point(0, 0));
    }

    /**
     * Sets a new sprite for the entity,
     *
     * @param sprite
     *        The sprite.
     *
     * @throws NullPointerException
     *        If the sprite is null.
     */
    public void setSprite(final @NonNull Sprite sprite) {
        final AsciiCharacter character = layer.getCharacterAt(new Point(0, 0));
        character.setCharacter(sprite.getCharacter());
        character.setForegroundColor(sprite.getForegroundColor());
        character.setBackgroundColor(sprite.getBackgroundColor());
    }

    public Point getPosition() {
        return layer.getPosition();
    }

    public void setPosition(final Point position) {
        if (position.x < 0) {
            throw new IllegalArgumentException("The x value (" + position.x + ") cannot be less than zero.");
        }

        if (position.y < 0) {
            throw new IllegalArgumentException("The y value (" + position.y + ") cannot be less than zero.");
        }

        layer.setPosition(position);
    }
}
