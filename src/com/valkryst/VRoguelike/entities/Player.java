package com.valkryst.VRoguelike.entities;

import com.valkryst.VRoguelike.actions.MoveAction;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity implements KeyListener {
    /**
     * Constructs a new player.
     *
     * @param x
     *        The x-axis position.
     *
     * @param y
     *        The y-axis position.
     *
     * @param sprite
     *        The sprite.
     */
    public Player(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Adds the player to a panel, effectively 'showing' the player.
     *
     * @param panel
     *        The panel.
     */
    public void show(final Panel panel) {
        super.show(panel);
        panel.addKeyListener(this);
    }

    /**
     * Removes the player from a panel, effectively 'hiding' the player.
     *
     * @param panel
     *        The panel.
     */
    public void hide(final Panel panel) {
        super.show(panel);
        panel.removeKeyListener(this);
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
