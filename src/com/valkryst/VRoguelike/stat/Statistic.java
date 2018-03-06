package com.valkryst.VRoguelike.stat;

import com.valkryst.VJSON.VJSONParser;
import com.valkryst.VTerminal.builder.LabelBuilder;
import com.valkryst.VTerminal.component.Label;
import lombok.Getter;
import lombok.NonNull;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Statistic {
    protected final static VJSONParser JSON_PARSER = jo -> {};

    /** The name of the statistic. */
    @Getter private final String name;

    /** The value. */
    @Getter private int value;

    /** The functions to run whenever the value is changed. */
    @Getter private final List<Runnable> onChangeFunctions = new ArrayList<>();

    /**
     * Constructs a new Statistic.
     *
     * @param name
     *        The name of the statistic.
     *
     * @param value
     *        The value.
     *
     * @throws NullPointerException
     *        If the name is null.
     *
     * @throws IllegalArgumentException
     *        If the name is empty.
     */
    public Statistic(final @NonNull String name, final int value) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty.");
        }

        this.name = name;
        this.value = value;
    }

    /**
     * Constructs a new Statistic.
     *
     * @param jsonObject
     *          The JSON to load the statistic from.
     *
     * @throws NullPointerException
     *        If the object is null.
     */
    public Statistic(final @NonNull JSONObject jsonObject) {
        this((String) jsonObject.get("name"),
                JSON_PARSER.getInteger(jsonObject, "val"));
    }

    @Override
    public String toString() {
        return name + ":\n\tValue:\t" + value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name) + Objects.hashCode(value);
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Statistic == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final Statistic otherSta = (Statistic) otherObj;

        boolean isEqual = Objects.equals(name, otherSta.getName());
        isEqual &= Objects.equals(value, otherSta.getValue());
        return isEqual;
    }

    /**
     * Retrieves a label component where the ID is set
     * to the stat's name and the text is set to contain
     * the name and current value.
     *
     * @return
     *         The label component that represents the statistic.
     */
    public Label getLabelComponent() {
        final LabelBuilder labelBuilder = new LabelBuilder();
        labelBuilder.setText(name + ": " + value);

        final Label label = labelBuilder.build();
        label.setId(name);

        return label;
    }

    /**
     * Sets the new value, then runs the onChangeFunctions functions.
     *
     * @param value
     *        The new value.
     */
    public void setValue(final int value) {
        this.value = value;

        for (final Runnable runnable : onChangeFunctions) {
            runnable.run();
        }
    }
}
