package com.valkryst.VRoguelike.enums;

import com.valkryst.VTerminal.AsciiCharacter;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.component.Layer;
import lombok.Getter;

import java.awt.*;

public enum Sprite {
    DIRT('▒', new Color(0x452F09), new Color(0x372507)),
    GRASS('▒', new Color(0x3D4509), new Color(0x303707)),
    PLAYER('@', new Color(0, 0, 0 ,0), Color.GREEN),
    ENEMY('E', new Color(0, 0, 0, 0), Color.RED);

    @Getter private final char character;
    @Getter private final Color backgroundColor;
    @Getter private final Color foregroundColor;

    Sprite(final char character, final Color backgroundColor, final Color foregroundColor) {
        this.character = character;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public void draw(final Panel panel, final int columnIndex, final int rowIndex) {
        panel.getCurrentScreen().getCharacterAt(columnIndex, rowIndex)
             .ifPresent(c -> {
                 c.setCharacter(character);
                 c.setBackgroundColor(backgroundColor);
                 c.setForegroundColor(foregroundColor);
             });
    }

    public void draw(final Layer layer, final int columnIndex, final int rowIndex) {
        final AsciiCharacter c = layer.getStrings()[rowIndex].getCharacters()[columnIndex];
        c.setCharacter(character);
        c.setBackgroundColor(backgroundColor);
        c.setForegroundColor(foregroundColor);
    }
}
