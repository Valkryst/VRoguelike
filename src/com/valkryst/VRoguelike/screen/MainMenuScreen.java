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
        builder.setText("New");
        builder.setColumnIndex(panel.getWidthInCharacters() / 3);
        builder.setRowIndex(panel.getHeightInCharacters() / 3);

        button_new = builder.build();

        builder.setText("Exit");
        builder.setRowIndex(builder.getRowIndex() + 1);
        button_exit = builder.build();

        // Add components:
        panel.addComponents(button_new, button_exit);
    }
}
