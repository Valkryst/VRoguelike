package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.Action;
import com.valkryst.VRoguelike.action.MoveAction;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.AsciiCharacter;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.component.Layer;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Entity {
    /** The name of the entity. */
    @Getter @Setter private String name = "Entity";

    /** A description of the entity. */
    @Getter @Setter private String description = "This is an unnamed Entity.";

    /** The action to perform. */
    @Getter private final List<Action> actions = new LinkedList<>();

    /** The layer-component on which the entity is drawn. */
    @Getter private Layer layer;

    /**
     * Constructs a new entity.
     *
     * @param x
     *        The x-axis position.
     *
     * @param y
     *        The y-axis position.
     *
     * @param sprite
     *        The sprite.
     */
    public Entity(final int x, final int y, final Sprite sprite) {
        if (sprite == null) {
            throw new NullPointerException("The sprite cannot be null.");
        }

        layer = new Layer(x, y, 1, 1);
        setSprite(sprite);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Entity:");
        sb.append("\n\tName:\t").append(name);
        sb.append("\n\tDescription:\t").append(description);
        sb.append("\n\tAction Queue:");

        for (final Action action : actions) {
            sb.append("\n\t\t").append(action.getClass().getSimpleName());
        }

        String tmp = "\t" + layer.toString();
        sb.append(tmp.replace("\n\t", "\n\t\t"));

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
        actions.add(new MoveAction(dx, dy));
        return true;
    }

    /**
     * Adds an entity to a panel, effectively 'showing' the entity.
     *
     * @param panel
     *        The panel.
     *
     * @return
     *        If the entity was shown.
     */
    public boolean show(final Panel panel) {
        if (panel != null) {
            panel.addComponent(layer);
            return true;
        }

        return false;
    }

    /**
     * Removes an entity from a panel, effectively 'hiding' the entity.
     *
     * @param panel
     *        The panel.
     *
     * @return
     *        If the entity was hidden.
     */
    public boolean hide(final Panel panel) {
        if (panel != null) {
            panel.removeComponent(layer);
            return true;
        }

        return false;
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
