package com.valkryst.VRoguelike.world;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.printer.RectanglePrinter;

import java.awt.Point;

public class Room {
    private final Point position;
    private int width;
    private int height;

    public Room(final int columnIndex, final int rowIndex, final int width, final int height) {
        this(new Point(columnIndex, rowIndex), width, height);
    }

    public Room(final Point position, final int width, final int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public void carve(final Screen screen) {
        final RectanglePrinter rectanglePrinter = new RectanglePrinter();
        rectanglePrinter.setWidth(width);
        rectanglePrinter.setHeight(height);
        rectanglePrinter.setFillChar(Sprite.DIRT.getCharacter());
        rectanglePrinter.printFilled(screen, position.x, position.y);
    }
}
