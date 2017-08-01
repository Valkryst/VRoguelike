package com.valkryst.VRoguelike.entity;

import com.valkryst.VRoguelike.action.MoveAction;
import com.valkryst.VRoguelike.action.UpdateLOSPosition;
import com.valkryst.VRoguelike.entity.builder.AbstractPlayerBuilder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Creature implements KeyListener {
    /**
     * Constructs a new player.
     *
     * @param builder
     *        The builder.
     */
    public Player(final AbstractPlayerBuilder builder) {
        super(builder);
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
        super.addAction(new UpdateLOSPosition(dx, dy));
    }
}
