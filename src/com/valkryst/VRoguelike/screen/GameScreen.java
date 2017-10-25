package com.valkryst.VRoguelike.screen;

import com.valkryst.VController.ControllerListener;
import com.valkryst.VController.preset.ControllerPreset;
import com.valkryst.VRoguelike.Driver;
import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.component.ScreenBuilder;
import com.valkryst.VTerminal.builder.component.TextAreaBuilder;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.component.TextArea;
import com.valkryst.VTerminal.font.Font;
import com.valkryst.VTerminal.printer.RectanglePrinter;
import lombok.Getter;
import lombok.NonNull;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameScreen extends Screen implements KeyListener, MouseListener {
    @Getter private final Map map;

    @Getter private TextArea messageBox;

    private ControllerPreset controllerPreset;
    private ControllerListener controllerListener;

    /** The currently displayed player information. */
    private Screen playerInfoScreen;

    /** The currently displayed player equipment information. */
    private Screen playerEquipmentScreen;

    /** The currently displayed target information. */
    private Screen targetInfoScreen;

    public GameScreen(final Panel panel) {
        super(new ScreenBuilder(panel.getWidthInCharacters(), panel.getHeightInCharacters()));
        panel.addKeyListener(this);
        panel.addMouseListener(this);

        addController();

        createMessageBox();
        map = new Map(getMessageBox(), 81, 30);
        this.addComponent(map);
        System.out.println(map);
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
        builder.setColumnIndex(0);
        builder.setRowIndex(30);

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
        Screen screen = player.getInformationPanel(this);
        screen.setPosition(new Point(81, 0));

        if (playerInfoScreen != null) {
            this.removeComponent(playerInfoScreen);
        }

        playerInfoScreen = screen;
        this.addComponent(playerInfoScreen);

        // Set the equipment panel.
        screen = player.getEquipment().getInformationPanel();
        screen.setPosition(new Point(81, 16));

        if (playerEquipmentScreen != null) {
            this.removeComponent(playerEquipmentScreen);
        }

        playerEquipmentScreen = screen;
        this.addComponent(playerEquipmentScreen);
    }

    /**
     * Changes the displayed target information to that
     * of the specified Entity.
     *
     * @param entity
     *          The target.
     */
    public void setTarget(final Entity entity) {
        final Screen screen;

        if (entity == null) {
            final ScreenBuilder screenBuilder = new ScreenBuilder();
            screenBuilder.setWidth(39);
            screenBuilder.setHeight(8);

            screen = screenBuilder.build();

            // Print border
            final RectanglePrinter rectanglePrinter = new RectanglePrinter();
            rectanglePrinter.setWidth(39);
            rectanglePrinter.setHeight(8);
            rectanglePrinter.setTitle("No Target");
            rectanglePrinter.print(screen, new Point(0, 0));
        } else {
            screen = entity.getInformationPanel(this);
        }

        screen.setPosition(new Point(81, 8));

        if (targetInfoScreen != null) {
            this.removeComponent(targetInfoScreen);
        }

        targetInfoScreen = screen;
        this.addComponent(targetInfoScreen);
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
        // Retrieve click position and convert it to cell coordinates:
        final Font font = Driver.PANEL.getImageCache().getFont();

        final Point clickPoint = e.getPoint();
        final int x = (int) (clickPoint.getX() / font.getWidth());
        final int y = (int) (clickPoint.getY() / font.getHeight());
        clickPoint.setLocation(x, y);

        // Check if a non-player Entity is being selected:
        map.getEntities().forEach(entity -> {
            if (entity.getLayer().intersects(clickPoint)) {
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
