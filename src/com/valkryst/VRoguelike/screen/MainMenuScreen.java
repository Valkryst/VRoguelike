package com.valkryst.VRoguelike.screen;

import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.component.ButtonBuilder;
import com.valkryst.VTerminal.builder.component.ScreenBuilder;
import com.valkryst.VTerminal.component.Button;
import com.valkryst.VTerminal.component.Screen;
import lombok.Getter;

public class MainMenuScreen extends Screen {
    @Getter private Button button_new;
    @Getter private Button button_exit;

    public MainMenuScreen(final Panel panel) {
        super(new ScreenBuilder(panel.getWidthInCharacters(), panel.getHeightInCharacters()));

        // Construct menu options:
        final ButtonBuilder builder = new ButtonBuilder();
        builder.setText("<New>");
        builder.setColumnIndex(50);
        builder.setRowIndex(15);

        button_new = builder.build();

        builder.setText("<Exit>");
        builder.setRowIndex(builder.getRowIndex() + 1);
        button_exit = builder.build();

        this.addComponents(button_new, button_exit);

        // Swap Screen:
        panel.swapScreen(this);
    }
}
