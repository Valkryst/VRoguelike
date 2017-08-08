package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.Action;
import com.valkryst.VRoguelike.action.HideAction;
import com.valkryst.VRoguelike.action.MoveAction;
import com.valkryst.VRoguelike.action.ShowAction;
import com.valkryst.VRoguelike.entity.builder.EntityBuilder;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.AsciiCharacter;
import com.valkryst.VTerminal.component.Layer;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
    public Entity(final EntityBuilder builder) {
        Objects.requireNonNull(builder);

        name = builder.getName();
        description = builder.getDescription();
        layer = new Layer(builder.getX(), builder.getY(), 1, 1);
        setSprite(builder.getSprite());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Entity:");
        sb.append("\n\tName:\t").append(name);
        sb.append("\n\tDescription:\t").append(description);
        sb.append("\n\tAction Queue:");

        if (actions.size() == 0) {
            sb.append("\tAction Queue is Empty\n");
        } else {
            for (final Action action : actions) {
                sb.append("\n\t\t").append(action.getClass().getSimpleName());
            }
        }

        sb.append("\t").append(layer.toString().replace("\n\t", "\n\t\t\t")).append("\n");

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name) + Objects.hashCode(description) + Objects.hashCode(actions) + Objects.hashCode(layer);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Entity == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final Entity otherEntity = (Entity) otherObj;

        boolean isEqual = Objects.equals(name, otherEntity.getName());
        isEqual &= Objects.equals(description, otherEntity.getDescription());
        isEqual &= Objects.equals(actions, otherEntity.getActions());
        isEqual &= Objects.equals(layer, otherEntity.getLayer());
        return isEqual;
    }

    /**
     * Updates the entity.
     *
     * @param map
     *        The map that the entity exists on.
     */
    public void update(final Map map) {
        if (map == null) {
            throw new NullPointerException("The map cannot be null.");
        }

        actions.forEach(action -> action.perform(map, this));
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
     */
    public boolean addAction(final Action action) {
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
        actions.add(new MoveAction(getX(), getY(), dx, dy));
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

    /**
     * Sets a new sprite for the entity,
     *
     * @param sprite
     *        The sprite.
     */
    public void setSprite(final Sprite sprite) {
        if (sprite == null) {
            return;
        }

        final Optional<AsciiCharacter> optChar = layer.getCharacterAt(0, 0);

        optChar.ifPresent(character -> {
            character.setCharacter(sprite.getCharacter());
            character.setForegroundColor(sprite.getForegroundColor());
            character.setBackgroundColor(sprite.getBackgroundColor());
        });
    }

    public int getX() {
        return layer.getColumnIndex();
    }

    public int getY() {
        return layer.getRowIndex();
    }
}
