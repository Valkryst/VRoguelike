package com.valkryst.VRoguelike.gui.controller;

import com.valkryst.VRoguelike.gui.view.View;
import com.valkryst.VTerminal.Screen;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Controller<V extends View> {
    /** The view. */
    @Getter protected final V view;

    /** The listeners of the view. */
    protected final List<EventListener> eventListeners = new ArrayList<>(0);

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

    /**
     * Adds all of the event listeners to a screen.
     *
     * @param screen
     *          The screen.
     */
    public void addEventListenersTo(final Screen screen) {
        if (screen == null) {
            return;
        }

        for (final EventListener listener : eventListeners) {
            screen.addListener(listener);
        }
    }

    /**
     * Removes all of the event listeners from a screen.
     *
     * @param screen
     *          The screen.
     */
    public void removeEventListenersFrom(final Screen screen) {
        if (screen == null) {
            return;
        }

        for (final EventListener listener : eventListeners) {
            screen.removeListener(listener);
        }
    }
}