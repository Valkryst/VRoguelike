package com.valkryst.VRoguelike;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VTerminal.AsciiCharacter;
import com.valkryst.VTerminal.AsciiString;
import lombok.Getter;
import lombok.NonNull;

import java.awt.Color;

public class Message {
    /** The message. */
    @Getter private AsciiString message;

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
     * Prepares an AsciiString with the default message
     * box colors.
     *
     * @param length
     *           The length of the Asciistring.
     *
     * @return
     *          The AsciiString.
     */
    public static AsciiString prepareString(final int length) {
        final AsciiString asciiString = new AsciiString(length);
        asciiString.setBackgroundColor(new Color(0xFF8E999E, true));
        asciiString.setForegroundColor(new Color(0xFF68D0FF, true));
        return asciiString;
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
    public static AsciiString prepareString(final @NonNull String text) {
        final AsciiString asciiString = new AsciiString(text);
        asciiString.setBackgroundColor(new Color(0xFF8E999E, true));
        asciiString.setForegroundColor(new Color(0xFF68D0FF, true));
        return asciiString;
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
    public Message append(final @NonNull AsciiString text) {
        final AsciiString newMessage = prepareString(message.length() + text.length());

        final AsciiCharacter[] oldChars = message.getCharacters();
        final AsciiCharacter[] textChars = text.getCharacters();
        final AsciiCharacter[] newChars = newMessage.getCharacters();

        for (int x = 0 ; x < message.length() ; x++) {
            newChars[x].copy(oldChars[x]);
        }

        for (int x = message.length() ; x < newMessage.length() ; x++) {
            newChars[x].copy(textChars[x - message.length()]);
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

        final AsciiString selfName = Message.prepareString(name);
        selfName.setForegroundColor(color);
        append(selfName);

        return this;
    }
}
