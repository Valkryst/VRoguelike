package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.MoveAction;
import com.valkryst.VRoguelike.action.UpdateLOS;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.component.Screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class Player extends Creature implements KeyListener {
    private final Screen screen;

    /**
     * Constructs a new player.
     *
     * @param x
     *        The x-axis position.
     *
     * @param y
     *        The y-axis position.
     *
     * @param screen
     *        The screen.
     */
    public Player(final int x, final int y, final Screen screen) {
        super(x, y, Sprite.PLAYER);

        Objects.requireNonNull(screen);

        super.setName("Player");
        super.setDescription("This is you.");
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Player" + super.toString().substring(8);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (otherObj instanceof Player == false) {
            return false;
        }

        if (otherObj == this) {
            return true;
        }

        return super.equals(otherObj);
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
        super.addAction(new UpdateLOS(screen, 4));
    }
}
