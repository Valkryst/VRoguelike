package com.valkryst.VRoguelike.gui.view;

import com.valkryst.VTerminal.component.Layer;
import lombok.Getter;

import java.awt.*;

public class View {
    /** The layer of the view. */
    @Getter protected final Layer layer;

    /**
     * Constructs a new View.
     *
     * @param width
     *          The width of the view's layer.
     *
     * @param height
     *          The height of the view's layer.
     */
    public View(final int width, final int height) {
        layer = new Layer(new Dimension(width, height));
    }
}
