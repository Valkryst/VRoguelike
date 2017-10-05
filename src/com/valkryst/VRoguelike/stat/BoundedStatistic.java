package com.valkryst.VRoguelike.stat;

import com.valkryst.VTerminal.builder.component.LabelBuilder;
import com.valkryst.VTerminal.component.Label;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=true)
public class BoundedStatistic extends Statistic {
    /** The maximum value. */
    private int maximum;
    /** The minimum value. */
    private int minimum;

    /**
     * Constructs a new BoundedStatistic.
     *
     * @param name
     *        The name of the statistic.
     *
     * @param maximum
     *        The maximum and initial value.
     *
     * @param minimum
     *        The minimum value.
     *
     * @throws NullPointerException
     *        If the name is null.
     *
     * @throws IllegalArgumentException
     *        If the maximum is less than the minimum.
     */
    public BoundedStatistic(final @NonNull String name, final int minimum, final int maximum) {
        super(name, maximum);

        if (maximum < minimum) {
            throw new IllegalArgumentException("The maximum (" + maximum + ") cannot be less than the minimum("
                                               + minimum + ").");
        }

        this.maximum = maximum;
        this.minimum = minimum;
    }

    /**
     * Constructs a new BoundedStatistic.
     *
     * @param name
     *        The name of the statistic.
     *
     * @param value
     *        The value.
     *
     * @param minimum
     *        The minimum value.
     *
     * @param maximum
     *        The maximum value.
     *
     * @throws NullPointerException
     *        If the name is null.
     *
     * @throws IllegalArgumentException
     *        If the maximum is less than the minimum.
     */
    public BoundedStatistic(final @NonNull String name, final int value, final int minimum, final int maximum) {
        super(name, value);

        if (maximum < minimum) {
            throw new IllegalArgumentException("The maximum (" + maximum + ") cannot be less than the minimum("
                    + minimum + ").");
        }

        this.maximum = maximum;
        this.minimum = minimum;
    }

    @Override
    public String toString() {
        String ret = super.toString();
        ret += "\n\tMaximum Value:\t" + maximum;
        ret += "\n\tMinimum Value:\t" + minimum;
        return ret;
    }

    @Override
    public void setValue(final int value) {
        if (value > maximum) {
            super.setValue(maximum);
        } else if (value < minimum) {
            super.setValue(minimum);
        } else {
            super.setValue(value);
        }
    }

    /**
     * Retrieves a label component where the ID is set
     * to the stat's name and the text is set to contain
     * the name and current value.
     *
     * Unlike the default getLabelComponent function, this
     * one returns a label with the maximum value listed.
     *
     * @return
     *         The label component that represents the statistic.
     */
    public Label getLabelComponentWithMax() {
        final LabelBuilder labelBuilder = new LabelBuilder();
        labelBuilder.setId(super.getName());
        labelBuilder.setText(super.getName() + ": " + super.getValue() + "/" + maximum);
        return labelBuilder.build();
    }
}
