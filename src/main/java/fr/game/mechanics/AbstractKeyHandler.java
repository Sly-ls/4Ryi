package fr.game.mechanics;

import fr.game.main.AppConstants;
import fr.game.main.ApplicationManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class AbstractKeyHandler implements KeyListener {
    protected int code = 0;
    protected boolean isKeyReleasd;
    protected boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, pPressed;
    protected ApplicationManager appManager;

    public AbstractKeyHandler(ApplicationManager appManager) {
        this.appManager = appManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        code =  e.getKeyCode();
        isKeyReleasd =false;
            switch (code) {
                case KeyEvent.VK_Z:
                case KeyEvent.VK_UP:
                    upPressed = true;
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    downPressed = true;
                    break;
                case KeyEvent.VK_Q:
                case KeyEvent.VK_LEFT:
                    leftPressed = true;
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    rightPressed = true;
                    break;
                case KeyEvent.VK_P:
                    pPressed = true;
                    break;
                case KeyEvent.VK_SPACE:
                    spacePressed = true;
                    break;
                default:
                    break;
        }
        printDebugUpadte(false);
    }

    private void printDebugUpadte(boolean released){
        if(AppConstants.DEBUG_KEYBOARD) {

            StringBuffer sbDebug = new StringBuffer(this.getClass().toString());
            if(released){
                sbDebug.append(" released ");
            }else{
                sbDebug.append(" pressed ");
            }
                    sbDebug.append(this.code);
            System.out.println(sbDebug);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

        code =  e.getKeyCode();
        isKeyReleasd = true;
        switch (code){
            case KeyEvent.VK_Z :
            case KeyEvent.VK_UP:
                upPressed=false;
                break;
            case KeyEvent.VK_Q :
            case KeyEvent.VK_LEFT :
                leftPressed=false;
                break;
            case KeyEvent.VK_S :
            case KeyEvent.VK_DOWN :
                downPressed=false;
                break;
            case KeyEvent.VK_D :
            case KeyEvent.VK_RIGHT :
                rightPressed=false;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = false;
                break;
            case KeyEvent.VK_P:
                pPressed = false;
                break;
            default:
                break;
        }
        printDebugUpadte(true);
    }


    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }

    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
    }

    public boolean ispPressed() {
        return pPressed;
    }

    public boolean isKeyReleasd() {
        return isKeyReleasd;
    }

    public int getCode() {
        return code;
    }

}
