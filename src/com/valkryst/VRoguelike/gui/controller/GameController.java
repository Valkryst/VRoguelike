package com.valkryst.VRoguelike.gui.controller;

import com.valkryst.VRoguelike.gui.view.GameView;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.Screen;
import com.valkryst.VTerminal.font.Font;
import lombok.NonNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameController extends Controller<GameView> {
    /**
     * Constructs a new GameController.
     *
     * @param screen
     *          The screen.
     *
     * @throws NullPointerException
     *          If the screen is null.
     */
    public GameController(final @NonNull Screen screen) {
        super(new GameView(screen));
        createEventListeners(screen);
    }

    /**
     * Creates the event listeners.
     *
     * @param screen
     *          The screen.
     */
    private void createEventListeners(final Screen screen) {
        if (screen == null) {
            return;
        }

        final Font font = screen.getImageCache().getFont();
        final double fontHeight = font.getHeight();
        final double fontWidth = font.getWidth();

        final Map map = super.view.getMap();

        final KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(final @NonNull KeyEvent e) { }

            @Override
            public void keyPressed(final @NonNull KeyEvent e) { }

            @Override
            public void keyReleased(final @NonNull KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W: {
                        map.getPlayer().move(0, -1);
                        break;
                    }
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S: {
                        map.getPlayer().move(0, 1);
                        break;
                    }
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A: {
                        map.getPlayer().move(-1, 0);
                        break;
                    }
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D: {
                        map.getPlayer().move(1, 0);
                        break;
                    }
                }
            }
        };

        final MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(final @NonNull MouseEvent e) { }

            @Override
            public void mousePressed(final @NonNull MouseEvent e) {
                // Check if a non-player Entity is being selected:
                map.getEntities().forEach(entity -> {
                    final Point point = e.getPoint();
                    point.x /= fontWidth;
                    point.y /= fontHeight;

                    if (entity.getLayer().intersects(point)) {
                        view.setTarget(entity);
                        return;
                    }
                });
            }

            @Override
            public void mouseReleased(final @NonNull MouseEvent e) { }

            @Override
            public void mouseEntered(final @NonNull MouseEvent e) { }

            @Override
            public void mouseExited(final @NonNull MouseEvent e) { }
        };

        super.eventListeners.add(keyListener);
        super.eventListeners.add(mouseListener);
    }
}
