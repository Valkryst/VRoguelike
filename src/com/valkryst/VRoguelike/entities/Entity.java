package com.valkryst.VRoguelike.entities;

import com.valkryst.VRoguelike.actions.Action;
import com.valkryst.VRoguelike.actions.MoveAction;
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
    /** The actions to perform. */
    private final List<Action> actions = new LinkedList<>();

    /** The layer-component on which the Entity is drawn. */
    @Getter private Layer layer;

    /**
     * Creates a new Entity.
     *
     * @param x
     *        The x-axis position.
     *
     * @param y
     *        The y-axis position.
     *
     * @param sprite
     *        The Sprite.
     */
    public Entity(final int x, final int y, final Sprite sprite) {
        layer = new Layer(x, y, 1, 1);
        setSprite(sprite);
    }

    /**
     * Updates the Entity.
     *
     * @param map
     *        The map that the Entity exists on.
     */
    public void update(final Map map) {
        actions.forEach(action -> action.perform(map, this));
        actions.clear();
    }

    /**
     * Adds an action to the Entity.
     *
     * @param action
     *        The action.
     */
    public void addAction(final Action action) {
        actions.add(action);
    }

    /**
     * Adds a move action to the Entity.
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
     * Adds an Entity to a panel, effectively 'showing' the Entity.
     *
     * @param panel
     *        The panel.
     */
    public void show(final Panel panel) {
        panel.addComponent(layer);
    }

    /**
     * Removes an Entity from a panel, effectively 'hiding' the Entity.
     *
     * @param panel
     *        The panel.
     */
    public void hide(final Panel panel) {
        panel.removeComponent(layer);
    }

    /**
     * Sets a new Sprite for the Entity,
     *
     * @param sprite
     *        The Sprite.
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
