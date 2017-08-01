package com.valkryst.VRoguelike.screen;

import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.component.ButtonBuilder;
import com.valkryst.VTerminal.component.Button;
import com.valkryst.VTerminal.component.Screen;
import lombok.Getter;

public class MainMenuScreen extends Screen {
    @Getter private Button button_new;
    @Getter private Button button_exit;

    public MainMenuScreen(final Panel panel) {
        super(0, 0, panel.getWidthInCharacters(), panel.getHeightInCharacters());

        // Construct menu options:
        final ButtonBuilder builder = new ButtonBuilder();

        button_new = builder.setText("New")
                            .setPanel(panel)
                            .setColumnIndex(panel.getWidthInCharacters() / 3)
                            .setRowIndex(panel.getHeightInCharacters() / 3)
                            .build();

        button_exit = builder.setText("Exit")
                             .setPanel(panel)
                             .setRowIndex(builder.getRowIndex() + 1)
                             .build();

        // Add components to self:
        super.addComponent(button_new);
        super.addComponent(button_exit);
    }
}
