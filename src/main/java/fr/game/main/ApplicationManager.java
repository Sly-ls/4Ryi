package fr.game.main;

import fr.game.constants.panel.PanelEnum;
import fr.game.main.utils.BufferedObjects;
import fr.game.mechanics.controller.KeyboardController;
import fr.game.mechanics.controller.MouseController;
import fr.game.mechanics.core.MessageBox;
import fr.game.panel.AbstractPanel;
import fr.game.panel.game.GamePanel;
import fr.game.panel.menu.creation.EntityCreationPanel;
import fr.game.panel.menu.title.TitlePanel;
import fr.game.panel.menu.worldSelection.WorldSelectionPanel;

import javax.swing.*;
import java.awt.*;

public class ApplicationManager implements Runnable{

    private Thread gameThread;

    private final JFrame window;
    private AbstractPanel currentPanel;
    private PanelEnum displayType = PanelEnum.NONE;
    private PanelEnum requiredChanged;
    protected KeyboardController keyHandler;
    protected MouseController mouseHandler;
    protected Graphics2D graphics2D;

    public ApplicationManager(JFrame window, PanelEnum requiredChanged) {
        this.window = window;
        this.requiredChanged=requiredChanged;
        this.keyHandler = new KeyboardController(this);
        this.mouseHandler = new MouseController(this);
        MessageBox.createInstance(this);
        BufferedObjects.getInstance().resetIconTotileSize();
        startThread();
    }

    public void displayNewPanel(PanelEnum panelType){
        requiredChanged = panelType;
    }
    private void displayRequiredPanelPanel(){

        this.window.removeKeyListener(this.keyHandler);
        this.window.removeMouseListener(this.mouseHandler);
        this.window.getContentPane().removeAll();
        MessageBox.getInstance().emptyMessageBox();
        if (this.currentPanel != null) {
            this.currentPanel.quitPanel();
        }
        switch (requiredChanged){
            case WORLD_SELECTION:
                this.currentPanel = new WorldSelectionPanel(this);
                break;
            case GAME:
                this.currentPanel = new GamePanel(this);
                break;
            case TITLE_SCREEN:
                this.currentPanel = new TitlePanel(this);
                break;
            case ENTITY_CREATION:
                this.currentPanel = new EntityCreationPanel(this);
                break;
            default:
                break;
        }
        if(currentPanel != null) {
            this.currentPanel.setupPanel();
            this.currentPanel.setPreferredSize(new Dimension(AppVariables.SCREEN_WIDTH, AppVariables.SCREEN_HEIGHT));
            this.currentPanel.addKeyListener(this.keyHandler);
            this.currentPanel.addMouseListener(this.mouseHandler);
            window.addKeyListener(this.keyHandler);
            window.addMouseListener(this.mouseHandler);
            window.add(currentPanel);
        }

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.pack();

        startThread();
    }

    public void startThread(){
        if(this.gameThread == null) {
            this.gameThread = new Thread(this);
        }
        if(!gameThread.isAlive()) {
            gameThread.start();
        }
    }

    @Override
    public  void run() {
        if(requiredChanged != displayType){
            displayRequiredPanelPanel();
        }
        if(this.currentPanel != null) {
            double drawInterval = AppConstants.NANO_SECONDS_IN_1_SECOND / AppVariables.FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            while (this.gameThread != null && this.gameThread.isAlive()) {
                if(requiredChanged != displayType){
                    displayRequiredPanelPanel();
                    this.displayType = requiredChanged;
                }
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                lastTime = currentTime;

                if (delta >= 1) {
                    this.currentPanel.update();
                    this.currentPanel.repaint();
                    delta--;
                }
            }
        }
    }

    //GETTER && SETTER
    public KeyboardController getKeyHandler() {
        return keyHandler;
    }
    public PanelEnum getDisplayType() {
        return displayType;
    }
    public AbstractPanel  getCurrentPanel() {
        return currentPanel;
    }
    public JFrame getWindow() {
        return window;
    }
    public MouseController getMouseHandler() {
        return mouseHandler;
    }
    public Graphics2D getGraphics2D() {
        return graphics2D;
    }
    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }
}
