package com.valkryst.VRoguelike.gui.view;

import com.valkryst.VTerminal.Screen;
import com.valkryst.VTerminal.builder.ButtonBuilder;
import com.valkryst.VTerminal.component.Button;
import com.valkryst.VTerminal.component.Layer;
import lombok.Getter;
import lombok.NonNull;

import java.awt.*;

public class MainMenuView extends Layer {
    @Getter private Button button_new;
    @Getter private Button button_exit;

    public MainMenuView(final @NonNull Screen screen) {
        super(new Dimension(screen.getWidth(), screen.getHeight()));

        // Construct menu options:
        final ButtonBuilder buttonBuilder = new ButtonBuilder();
        buttonBuilder.setText("<New>");
        buttonBuilder.setPosition(50, 15);
        button_new = buttonBuilder.build();

        buttonBuilder.setText("<Exit>");
        buttonBuilder.setPosition(50, 16);
        button_exit = buttonBuilder.build();

        this.addComponent(button_new);
        this.addComponent(button_exit);
    }
}
