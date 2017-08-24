package com.valkryst.VRoguelike.screen;

import com.valkryst.VRadio.Radio;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.component.TextAreaBuilder;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.component.TextArea;
import com.valkryst.VTerminal.printer.RectanglePrinter;
import lombok.Getter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends Screen implements KeyListener {
    @Getter private final Map map = new Map(this, 65, 25);

    @Getter private TextArea messageBox;

    public GameScreen(final Panel panel) {
        super(0, 0, panel.getWidthInCharacters(), panel.getHeightInCharacters());
        panel.addKeyListener(this);

        drawUISections();
        createMessageBox(panel.getRadio());
    }

    private void drawUISections() {
        final RectanglePrinter printer = new RectanglePrinter();
        printer.setWidth(23);
        printer.setHeight(10);

        // Player UI Section:
        printer.setTitle("Player");
        printer.print(this, 66, 0);


        // Target UI Section:
        printer.setTitle("Target");
        printer.print(this, 66, 11);
    }

    private void createMessageBox(final Radio<String> radio) {
        final TextAreaBuilder builder = new TextAreaBuilder();

        builder.setRadio(radio);

        builder.setColumnIndex(0);
        builder.setRowIndex(25);

        builder.setWidth(65);
        builder.setHeight(5);

        builder.setEditable(false);

        messageBox = builder.build();
        this.addComponent(messageBox);
    }

    @Override
    public void keyTyped(final KeyEvent e) {}

    @Override
    public void keyPressed(final KeyEvent e) {}

    @Override
    public void keyReleased(final KeyEvent e) {
        int dx = 0;
        int dy = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W: {
                dy = -1;
                break;
            }
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S: {
                dy = 1;
                break;
            }
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A: {
                dx = -1;
                break;
            }
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D: {
                dx = 1;
                break;
            }
        }

        map.getPlayer().move(dx, dy);
    }
}
