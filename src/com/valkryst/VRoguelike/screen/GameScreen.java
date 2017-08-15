package com.valkryst.VRoguelike.screen;

import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.component.Screen;
import lombok.Getter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends Screen implements KeyListener {
    @Getter private final Map map = new Map(this, 90, 30);

    public GameScreen(final Panel panel) {
        super(0, 0, panel.getWidthInCharacters(), panel.getHeightInCharacters());
        panel.addKeyListener(this);
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
