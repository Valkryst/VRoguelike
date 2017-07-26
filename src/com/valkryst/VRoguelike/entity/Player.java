package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.MoveAction;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Creature implements KeyListener {
    /**
     * Constructs a new player.
     *
     * @param x
     *        The x-axis position.
     *
     * @param y
     *        The y-axis position.
     */
    public Player(final int x, final int y) {
        super(x, y, Sprite.PLAYER);
        super.setName("Player");
        super.setDescription("This is you.");
    }

    @Override
    public boolean show(final Panel panel) {
        if (panel != null) {
            if (super.show(panel)) {
                panel.addKeyListener(this);
            }
        }

        return false;
    }

    @Override
    public boolean hide(final Panel panel) {
        if (panel != null) {
            if (super.show(panel)) {
                panel.removeKeyListener(this);
                return true;
            }
        }

        return false;
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

        super.addAction(new MoveAction(dx, dy));
    }
}
