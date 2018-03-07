package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VTerminal.Tile;
import lombok.Getter;
import lombok.NonNull;

import java.awt.Color;

public class Message {
    /** The message. */
    @Getter private Tile[] message;

    /** Constructs a new message. */
    public Message() {
        this.message = prepareString(0);
    }

    /**
     * Constructs a new message.
     *
     * @param message
     *           The message.
     */
    public Message(final @NonNull String message) {
        this.message = prepareString(message);
    }


    /**
     * Prepares an array of Tiles with the default message box colors.
     *
     * @param length
     *           The length of the Asciistring.
     *
     * @return
     *          The AsciiString.
     */
    public static Tile[] prepareString(final int length) {
        final Tile[] tiles = new Tile[length];
        final Color backgroundColor = new Color(0xFF8E999E, true);
        final Color foregroundColor = new Color(0xFF68D0FF, true);

        for (int i = 0 ; i < tiles.length ; i++) {
            tiles[i] = new Tile(' ');
            tiles[i].setBackgroundColor(backgroundColor);
            tiles[i].setForegroundColor(foregroundColor);
        }

        return tiles;
    }

    /**
     * Prepares an AsciiString with the default message
     * box colors.
     *
     * @param text
     *           The text of the AsciiString,
     *
     * @return
     *          The AsciiString.
     */
    public static Tile[] prepareString(final @NonNull String text) {
        final Tile[] tiles = new Tile[text.length()];
        final Color backgroundColor = new Color(0xFF8E999E, true);
        final Color foregroundColor = new Color(0xFF68D0FF, true);

        for (int x = 0 ; x < text.length() ; x++) {
            tiles[x] = new Tile(text.charAt(x));
            tiles[x].setCharacter(text.charAt(x));
            tiles[x].setBackgroundColor(backgroundColor);
            tiles[x].setForegroundColor(foregroundColor);
        }

        return tiles;
    }

    /**
     * Appends text to the message.
     *
     * @param text
     *          The text.
     *
     * @return
     *          This.
     */
    public Message append(final @NonNull String text) {
        append(prepareString(text));
        return this;
    }

    /**
     * Appends an AsciiString of text to the message.
     *
     * @param text
     *           The text.
     *
     * @return
     *          This.
     */
    public Message append(final @NonNull Tile[] text) {
        final Tile[] newMessage = new Tile[message.length + text.length];

        for (int x = 0 ; x < message.length ; x++) {
            newMessage[x] = new Tile(' ');
            newMessage[x].copy(message[x]);
        }

        for (int x = message.length ; x < newMessage.length ; x++) {
            newMessage[x] = new Tile(' ');
            newMessage[x].copy(text[x - message.length]);
        }

        message = newMessage;

        return this;
    }

    /**
     * Appends the name of an Entity to the message.
     *
     * The Entity's name uses the foreground color of the
     * Entity's sprite.
     *
     * @param entity
     *           The entity.
     *
     * @return
     *          This.
     */
    public Message appendEntityName(final Entity entity) {
        final String name = entity.getName();
        final Color color = entity.getSprite().getForegroundColor();

        final Tile[] selfName = Message.prepareString(name);

        for (final Tile tile : selfName) {
            tile.setForegroundColor(color);
        }

        append(selfName);

        return this;
    }
}
