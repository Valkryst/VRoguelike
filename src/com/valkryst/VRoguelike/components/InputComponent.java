package com.valkryst.VRoguelike.components;

import com.valkryst.VECS.Component;
import com.valkryst.VRadio.Radio;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputComponent extends Component implements KeyListener {
    /** The radio. */
    @Getter @Setter private Radio<KeyEvent> radio;

    /** Constructs a new InputComponent. */
    public InputComponent() {
        radio = new Radio<>();
    }

    @Override
    public String toString() {
        String res = getClass().getSimpleName() + ":";
        res += "\n\tRadio:\t" + radio;
        return res;
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof InputComponent == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        final InputComponent otherComp = (InputComponent) otherObj;
        return radio.equals(otherComp.getRadio());
    }

    @Override
    public String toJson() {
        // todo Implement.
        throw new UnsupportedOperationException("Function not implemented.");
    }

    /**
     * Constructs a new InputComponent.
     *
     * @param radio
     *        The radio.
     */
    public InputComponent(final Radio<KeyEvent> radio) {
        if (radio == null) {
            throw new IllegalArgumentException();
        }

        this.radio = radio;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        radio.transmit("KEY_TYPED", e);
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        radio.transmit("KEY_PRESSED", e);
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        radio.transmit("KEY_RELEASED", e);
    }
}
