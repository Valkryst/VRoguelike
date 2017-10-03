package com.valkryst.VRoguelike.screen;

import com.valkryst.VController.ButtonType;
import com.valkryst.VController.ControllerHelper;
import com.valkryst.VController.ControllerListener;
import com.valkryst.VController.preset.ControllerPreset;
import com.valkryst.VRadio.Radio;
import com.valkryst.VRadio.Receiver;
import com.valkryst.VRoguelike.world.Map;
import com.valkryst.VTerminal.Panel;
import com.valkryst.VTerminal.builder.component.ScreenBuilder;
import com.valkryst.VTerminal.builder.component.TextAreaBuilder;
import com.valkryst.VTerminal.component.Screen;
import com.valkryst.VTerminal.component.TextArea;
import com.valkryst.VTerminal.printer.RectanglePrinter;
import lombok.Getter;
import net.java.games.input.Controller;
import net.java.games.input.Event;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GameScreen extends Screen implements KeyListener, Receiver<Event> {
    @Getter private final Map map;

    @Getter private TextArea messageBox;

    private ControllerPreset controllerPreset;
    private ControllerListener controllerListener;

    public GameScreen(final Panel panel) {
        super(new ScreenBuilder(panel.getWidthInCharacters(), panel.getHeightInCharacters()));
        panel.addKeyListener(this);

        addController();

        createMessageBox(panel.getRadio());
        map = new Map(getMessageBox(), 80, 30);
        drawUISections();
    }

    private void addController() {
        final List<Controller> controllers = ControllerHelper.getSupportedControllers();

        if (controllers.size() > 0) {
            final Controller controller = ControllerHelper.getSupportedControllers().get(0);
            controllerPreset = ControllerHelper.getControllerPreset(controller);
            controllerListener = new ControllerListener(controller, 64);

            controllerListener.getRadio().addReceiver("CONTROLLER", this);
        }
    }

    private void drawUISections() {
        final RectanglePrinter printer = new RectanglePrinter();
        printer.setWidth(39);
        printer.setHeight(10);

        // Map:
        this.addComponent(map.getScreen());

        // Player UI Section:
        printer.setTitle("Player");
        printer.print(this, new Point(81, 0));


        // Target UI Section:
        printer.setTitle("Target");
        printer.print(this, new Point(81, 11));
    }

    private void createMessageBox(final Radio<String> radio) {
        final TextAreaBuilder builder = new TextAreaBuilder();

        builder.setRadio(radio);

        builder.setColumnIndex(0);
        builder.setRowIndex(30);

        builder.setWidth(80);
        builder.setHeight(10);

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

    @Override
    public void receive(final String event, final Event data) {
        if (controllerPreset.getButtonType(data) != ButtonType.DPAD) {
            return;
        }

        switch (controllerPreset.getDPadDirection(data)) {
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
