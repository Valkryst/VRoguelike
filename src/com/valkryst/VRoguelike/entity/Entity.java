package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.Action;
import com.valkryst.VRoguelike.action.MoveAction;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.AsciiCharacter;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.component.Layer;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Entity {
    /** The action to perform. */
    private final List<Action> actions = new LinkedList<>();

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
        layer = new Layer(x, y, 1, 1);
        setSprite(sprite);
    }

    /**
     * Updates the entity.
     *
     * @param map
     *        The map that the entity exists on.
     */
    public void update(final Map map) {
        actions.forEach(action -> action.perform(map, this));
        actions.clear();
    }

    /**
     * Adds an action to the entity.
     *
     * @param action
     *        The action.
     */
    public void addAction(final Action action) {
        actions.add(action);
    }

    /**
     * Adds a move action to the entity.
     *
     * @param dx
     *        The change in x-axis position.
     *
     * @param dy
     *        The change in y-axis position.
     */
    public void move(final int dx, final int dy) {
        actions.add(new MoveAction(dx, dy));
    }

    /**
     * Adds an entity to a panel, effectively 'showing' the entity.
     *
     * @param panel
     *        The panel.
     */
    public void show(final Panel panel) {
        panel.addComponent(layer);
    }

    /**
     * Removes an entity from a panel, effectively 'hiding' the entity.
     *
     * @param panel
     *        The panel.
     */
    public void hide(final Panel panel) {
        panel.removeComponent(layer);
    }

    /**
     * Sets a new sprite for the entity,
     *
     * @param sprite
     *        The sprite.
     */
    public void setSprite(final Sprite sprite) {
        final Optional<AsciiCharacter> optChar = layer.getCharacterAt(0, 0);

        optChar.ifPresent(character -> {
            character.setCharacter(sprite.getCharacter());
            character.setForegroundColor(sprite.getForegroundColor());
            character.setBackgroundColor(sprite.getBackgroundColor());
        });
    }
}
