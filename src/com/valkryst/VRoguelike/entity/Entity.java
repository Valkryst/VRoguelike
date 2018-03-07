package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.Action;
import com.valkryst.VRoguelike.action.HideAction;
import com.valkryst.VRoguelike.action.MoveAction;
import com.valkryst.VRoguelike.action.ShowAction;
import com.valkryst.VRoguelike.entity.builder.EntityBuilder;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.view.GameView;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.Tile;
import com.valkryst.VTerminal.component.Layer;
import com.valkryst.VTerminal.printer.RectanglePrinter;
import lombok.*;

import java.awt.*;
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

        layer = new Layer(new Dimension(1, 1));
        layer.getTiles().setPosition(builder.getPosition());

        setSprite(builder.getSprite());

        if (name.isEmpty()) {
            name = "Unknown";
        }
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

    public Layer getInformationPanel(final @NonNull GameView gameView) {
        final Layer layer = new Layer(new Dimension(39, 8));

        // Print border
        final RectanglePrinter rectanglePrinter = new RectanglePrinter();
        rectanglePrinter.setWidth(39);
        rectanglePrinter.setHeight(8);
        rectanglePrinter.setTitle(name);
        rectanglePrinter.print(layer.getTiles(), new Point(0, 0));

        // Color name on the border
        final Color color = getSprite().getForegroundColor();
        final Tile[] nameTiles = layer.getTiles().getRowSubset(0, 2, name.length());

        for (final Tile tile : nameTiles) {
            tile.setForegroundColor(color);
        }

        return layer;
    }

    /**
     * Retrieves the current sprite character.
     *
     * @return
     *         The sprite character.
     */
    public Tile getSprite() {
        return layer.getTileAt(new Point(0, 0));
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
        final Tile tile = layer.getTileAt(new Point(0, 0));
        tile.setCharacter(sprite.getCharacter());
        tile.setForegroundColor(sprite.getForegroundColor());
        tile.setBackgroundColor(sprite.getBackgroundColor());
    }

    public Point getPosition() {
        return new Point(layer.getTiles().getXPosition(), layer.getTiles().getYPosition());
    }

    public void setPosition(final Point position) {
        if (position.x < 0) {
            throw new IllegalArgumentException("The x value (" + position.x + ") cannot be less than zero.");
        }

        if (position.y < 0) {
            throw new IllegalArgumentException("The y value (" + position.y + ") cannot be less than zero.");
        }

        layer.getTiles().setXPosition(position.x);
        layer.getTiles().setYPosition(position.y);
    }
}
