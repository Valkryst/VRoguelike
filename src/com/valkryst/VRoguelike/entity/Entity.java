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
import lombok.*;

import java.util.Optional;
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
        layer = new Layer(builder.getX(), builder.getY(), 1, 1);
        setSprite(builder.getSprite());
    }

    /**
     * Updates the entity.
     *
     * @param map
     *        The map that the entity exists on.
     *
     * @throws NullPointerException
     *        If the map is null.
     */
    public void update(final @NonNull Map map) {
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
     *
     * @throws NullPointerException
     *        If the sprite is null.
     */
    public void setSprite(final @NonNull Sprite sprite) {
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
