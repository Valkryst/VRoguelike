package com.valkryst.VRoguelike.view;

import com.valkryst.VController.ControllerListener;
import com.valkryst.VController.preset.ControllerPreset;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.Screen;
import com.valkryst.VTerminal.builder.TextAreaBuilder;
import com.valkryst.VTerminal.component.Layer;
import com.valkryst.VTerminal.component.TextArea;
import com.valkryst.VTerminal.printer.RectanglePrinter;
import lombok.Getter;
import lombok.NonNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameView extends Layer implements KeyListener, MouseListener {
    @Getter private final Map map;

    @Getter private TextArea messageBox;

    private ControllerPreset controllerPreset;
    private ControllerListener controllerListener;

    /** The currently displayed player information. */
    private Layer playerInfoView;

    /** The currently displayed player equipment information. */
    private Layer playerEquipmentView;

    /** The currently displayed target information. */
    private Layer targetInfoView;

    public GameView(final Screen screen) {
        super(new Dimension(screen.getWidth(), screen.getHeight()));
        screen.addListener((KeyListener) this);
        screen.addListener((MouseListener) this);

        addController();

        createMessageBox();
        map = new Map(getMessageBox(), 81, 30);
        this.addComponent(map);
    }

    private void addController() {
        /*
        final List<Controller> controllers = ControllerHelper.getSupportedControllers();

        if (controllers.size() > 0) {
            final Controller controller = ControllerHelper.getSupportedControllers().get(0);
            controllerPreset = ControllerHelper.getControllerPreset(controller);
            controllerListener = new ControllerListener(controller);

            controllerListener.getRadio().addReceiver("CONTROLLER", this);
        }
        */
    }

    private void createMessageBox() {
        final TextAreaBuilder builder = new TextAreaBuilder();
        builder.setPosition(0, 30);
        builder.setWidth(81);
        builder.setHeight(10);

        builder.setEditable(false);

        messageBox = builder.build();
        this.addComponent(messageBox);
    }

    /**
     * Changes the displayed player information to that
     * of the specified Player.
     *
     * @param player
     *          The player.
     */
    public void setPlayer(final @NonNull Player player) {
        // Set the information panel.
        Layer layer = player.getInformationPanel(this);
        layer.getTiles().setPosition(81, 0);

        if (playerInfoView != null) {
            this.removeComponent(playerInfoView);
        }

        playerInfoView = layer;
        this.addComponent(playerInfoView);

        // Set the equipment panel.
        layer = player.getEquipment().getInformationPanel();
        layer.getTiles().setPosition(81, 16);

        if (playerEquipmentView != null) {
            this.removeComponent(playerEquipmentView);
        }

        playerEquipmentView = layer;
        this.addComponent(playerEquipmentView);
    }

    /**
     * Changes the displayed target information to that
     * of the specified Entity.
     *
     * @param entity
     *          The target.
     */
    public void setTarget(final Entity entity) {
        final Layer layer;

        if (entity == null) {
            layer = new Layer(new Dimension(39, 8));

            // Print border
            final RectanglePrinter rectanglePrinter = new RectanglePrinter();
            rectanglePrinter.setWidth(39);
            rectanglePrinter.setHeight(8);
            rectanglePrinter.setTitle("No Target");
            rectanglePrinter.print(layer.getTiles(), new Point(0, 0));
        } else {
            layer = entity.getInformationPanel(this);
        }

        layer.getTiles().setPosition(81, 8);

        if (targetInfoView != null) {
            this.removeComponent(targetInfoView);
        }

        targetInfoView = layer;
        this.addComponent(targetInfoView);
    }

    /*
    @Override
    public void receive(final String event, final Event data) {
        if (controllerPreset.getButtonType(data) == ButtonType.DPAD) {switch (controllerPreset.getDPadDirection(data)) {
            case UP: {
                map.getPlayer().move(0, -1);
                break;
            }
            case DOWN: {
                map.getPlayer().move(0, 1);
                break;
            }
            case LEFT: {
                map.getPlayer().move(-1, 0);
                break;
            }
            case RIGHT: {
                map.getPlayer().move(1, 0);
                break;
            }
        }
        }
    }
    */

    @Override
    public void mouseClicked(final @NonNull MouseEvent e) {}

    @Override
    public void mousePressed(final @NonNull MouseEvent e) {
        // Check if a non-player Entity is being selected:
        map.getEntities().forEach(entity -> {
            if (entity.getLayer().intersects(e.getPoint())) {
                setTarget(entity);
                return;
            }
        });
    }

    @Override
    public void mouseReleased(final @NonNull MouseEvent e) {}

    @Override
    public void mouseEntered(final @NonNull MouseEvent e) {}

    @Override
    public void mouseExited(final @NonNull MouseEvent e) {}

    @Override
    public void keyTyped(final @NonNull KeyEvent e) {}

    @Override
    public void keyPressed(final @NonNull KeyEvent e) {}

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
}
