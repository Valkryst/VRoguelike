package com.valkryst.VRoguelike.enums;

import lombok.Getter;
import lombok.NonNull;

public enum Gender {
    MALE("he", "him", "his", "himself"),
    FEMALE("she", "her", "hers", "herself"),
    UNKNOWN("it", "it", "its", "itself");

    /** The subject pronoun. (Ex. he) */
    @Getter private final String pronounSubject;
    /** The object pronoun. (Ex. him) */
    @Getter private final String pronounObject;
    /** The possessive pronoun. (Ex. his) */
    @Getter private final String pronounPossessive;
    /** The reflexive pronoun. (Ex. himself) */
    @Getter private final String pronounReflexive;

    /**
     * Constructs a new Gender enum.
     *
     * @param pronounSubject
     *          The subject pronoun. (Ex. he)
     *
     * @param pronounObject
     *          The object pronoun. (Ex. him)
     *
     * @param pronounPossessive
     *          The possessive pronoun. (Ex. his)
     *
     * @param pronounReflexive
     *          The reflexive pronoun. (Ex. himself)
     */
    Gender(final @NonNull String pronounSubject, final @NonNull String pronounObject, final @NonNull String pronounPossessive, final @NonNull String pronounReflexive) {
        if (pronounSubject.isEmpty()) {
            throw new IllegalArgumentException("The subject pronoun cannot be empty.");
        }

        if (pronounObject.isEmpty()) {
            throw new IllegalArgumentException("The object pronoun cannot be empty.");
        }

        if (pronounPossessive.isEmpty()) {
            throw new IllegalArgumentException("The possessive pronoun cannot be empty.");
        }

        if (pronounReflexive.isEmpty()) {
            throw new IllegalArgumentException("The reflexice pronoun cannot be empty.");
        }

        this.pronounSubject = pronounSubject;
        this.pronounObject = pronounObject;
        this.pronounPossessive = pronounPossessive;
        this.pronounReflexive = pronounReflexive;
    }
}
