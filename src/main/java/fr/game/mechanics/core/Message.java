package fr.game.mechanics.core;


import fr.game.main.AppVariables;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.main.utils.Graphics2DUtils;
import fr.game.mechanics.controller.Camera;
import fr.game.panel.game.GameController;
import javafx.util.Pair;

import java.awt.*;
import java.util.Objects;

public class Message{

    public String uuid;
    public MessageTypeEnum type;
    public String text = null;
    public FontEnum font = FontEnum.ARIAL;
    public int fontSize = 30;
    public Color fontColor = Color.WHITE;
    public boolean selected;
    public int padding = 20;
    public Color subWindowColor  = new Color(46, 194, 165, 50);
    public Color subWindowFontColor  = new Color(255,255,255, 255);
    public Color subWindowBorderColor  = new Color(255,255,255, 255);
    //FIXME instance by default here to mask an NPE somewhere
    public Pair<Integer, Integer> textBound = new Pair<>(1,1);
    public Pair<Integer, Integer> screenCoordinate;
    public Integer fpsCounter = 0;
    public Integer fpsCounterMax = AppVariables.messageStayFor;

    //CONSTRUCTOR
    public Message(String uuid, MessageTypeEnum messageType,
                   String text,
                   FontEnum font, int fontSize, Color fontColor,
                   boolean selected, int padding,
                   int screenX, int screenY ){
        this.uuid = uuid;
        this.type = messageType;
        this.font = font;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.selected = selected;
        this.padding = Math.max(padding, 0);
        setText(text);
        setScreenCoordinate(screenX, screenY);
    }
    public Message(String uuid, MessageTypeEnum messageType,
                   String text, int screenX, int screenY, boolean selected) {
        this.uuid = uuid;
        this.type = messageType;
        this.selected = selected;
        setText(text);
        setScreenCoordinate(screenX, screenY);
        setDuration();
    }

    //CLASS METHOD
    public void refreshMessage(Graphics2D graphics2D){
        Graphics2DUtils.setUIFont(graphics2D, font,fontSize, fontColor);
        refreshMessage(graphics2D, screenCoordinate.getKey(), screenCoordinate.getValue());
        setDuration();
    }
    public void refreshMessage(Graphics2D graphics2D, int screenX, int screenY){
        setTextBound(graphics2D,text);
        setScreenCoordinate(screenX, screenY);
        setDuration();
    }

    private void setText(String text){
        if(text == null || text.equalsIgnoreCase("")){
            this.text = uuid;
        }else{
            this.text = text;
        }
    }
    private void setTextBound(Graphics2D graphics2D, String text){

        textBound= new Pair<> ((int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth()
        ,(int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getHeight());
    }
    private void setScreenCoordinate(int screenX, int screenY){

        switch (type){
            case MESSAGE_TO_PLAYER:
                //we get screenX_Y fixed for message type, centered on message
                screenX = Camera.getInstance().getScreenX(GameController.getInstance().getPlayer().getWorldX());
                screenY = Camera.getInstance().getScreenY(GameController.getInstance().getPlayer().getWorldY());
                break;
            case DIALOG:
                //we get screenX_Y fixed for message type
                screenX = AppVariables.tileSize;
                screenY = AppVariables.tileSize*2;
                break;
            case WORLD_EVENT:
                /*
                /relative to world coordinate, so to camera positon also, screenX and Y will be caculated during draw phase
                 */
            case MENU_TEXT:
            case NANO_TEXT:
                /*
                don't do anysthing, the coordinate are already the one you need to display
                 */
                break;
            default:
                break;

        }
        this.screenCoordinate = new Pair<>(screenX,screenY);
    }
    private void setDuration(){

        switch (type){
            case MESSAGE_TO_PLAYER:
            case WORLD_EVENT:
                this.fpsCounterMax= AppVariables.messageStayFor*2;
                break;
            case MENU_TEXT:
            case NANO_TEXT:
            case DIALOG:
                this.fpsCounterMax= 1;
                break;
            default:
                this.fpsCounterMax= 0;
                break;
        }
    }

}

