package com.valkryst.VRoguelike.gui.controller;

import com.valkryst.VRoguelike.gui.view.View;
import com.valkryst.VTerminal.Screen;
import lombok.Getter;
import lombok.NonNull;

public class Controller<V extends View> {
    /** The view. */
    @Getter protected final V view;

    /**
     * Constructs a new Controller.
     *
     * @param view
     *          The view.
     *
     * @throws NullPointerException
     *          If the any of the params are null.
     */
    public Controller(final @NonNull V view) {
        this.view = view;
    }

    /**
     * Removes all components from a screen, then adds the view's layer component to the screen.
     *
     * @param screen
     *          The screen.
     */
    public void addViewToScreen(final Screen screen) {
        if (screen == null) {
            return;
        }

        screen.removeAllComponents();
        screen.addComponent(view.getLayer());
    }
}