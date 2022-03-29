package fr.game.mechanics.core;

import fr.game.main.AppVariables;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.panel.PanelStateEnum;
import fr.game.main.ApplicationManager;
import fr.game.mechanics.controller.Camera;

import java.awt.*;
import java.util.List;
import java.util.*;

public class MessageBox {
    ApplicationManager applicationManager;
    final int max_Object = 99;
    protected HashMap<MessageTypeEnum, List<Message>> messages = new HashMap<>();

    static MessageBox instance;

    private MessageBox(ApplicationManager applicationManager){
        this.applicationManager=applicationManager;
    }
    public static MessageBox createInstance(ApplicationManager applicationManager) {
        if(instance==null){
            instance = new MessageBox(applicationManager);
        }
        return instance;
    }
    public static MessageBox getInstance() {
        if(instance==null){
            //will throw NPE
        }
        return instance;
    }

    public List<Message> getMessageToDraw() {
        List<Message> messagesToReturn = new ArrayList<>();
        for (Map.Entry<MessageTypeEnum, List<Message>> entryMessages : this.messages.entrySet()){
            messagesToReturn.addAll(entryMessages.getValue());
        }
        return messagesToReturn;
    }

    public void addOrRemove(Message newObject, boolean add){
        List toUpdate = messages.getOrDefault(newObject.type, new ArrayList<>());
        if(add){
            toUpdate.add(newObject);
        }else{
            toUpdate.remove(newObject);
        }
        messages.put(newObject.type, toUpdate);
    }


    public Message sendMessage(MessageTypeEnum messageType,
                            String text,
                            FontEnum font, Color fontColor, int fontSize,
                            boolean selected, int padding,
                            int screenX, int screenY){

        System.out.println("Message added : " + messageType.name() +","+text);
        Message message = new Message(UUID.randomUUID().toString(), messageType,
                text,
                font, fontSize, fontColor,
                selected, padding,
                screenX, screenY);
        MessageBox.getInstance().addOrRemove(message, true);
        if(message.type == MessageTypeEnum.DIALOG){
            this.applicationManager.getCurrentPanel().setPanelState(PanelStateEnum.DIALOG);
        }
        message.refreshMessage(this.applicationManager.getGraphics2D());
        return message;
    }


    public Message sendMessage(MessageTypeEnum messageType,
                               String text, int screenX, int screenY, boolean selected){
        System.out.println("Message added : " + messageType.name() +","+text);
        Message message = new Message(UUID.randomUUID().toString(), messageType,
                text, screenX, screenY,selected);
        MessageBox.getInstance().addOrRemove(message, true);
        if(message.type == MessageTypeEnum.DIALOG){
            this.applicationManager.getCurrentPanel().setPanelState(PanelStateEnum.DIALOG);
        }
        message.refreshMessage(this.applicationManager.getGraphics2D());
        return message;
    }
    public void drawMessage(){
        List<Message> messages = getMessageToDraw();
        Set<Message> messagesToRemove = new HashSet();
        System.out.println("messages : " + messages.size());
        for (Message message : messages){
            if(message == null || message.fpsCounter == null){
                System.out.println("WTF !!! ");
                continue;
            }
            message.fpsCounter++;

            if ( (message.type != MessageTypeEnum.DIALOG
                    && message.fpsCounter > message.fpsCounterMax)
                    || (message.type == MessageTypeEnum.DIALOG
                    && this.applicationManager.getCurrentPanel().getPanelState() != PanelStateEnum.DIALOG)
            ) {
                messagesToRemove.add(message);
            }else if (message.type != MessageTypeEnum.DIALOG
                    || (message.type == MessageTypeEnum.DIALOG
                    && this.applicationManager.getCurrentPanel().getPanelState() == PanelStateEnum.DIALOG)
            ){
                drawMessage(message);
            }
        }
        for (Message message : messagesToRemove){
            MessageBox.getInstance().addOrRemove(message,false);
        }

    }
    private void drawMessage(Message message){
        message.refreshMessage( this.applicationManager.getCurrentPanel().getGraphics2D());
        int x = message.screenCoordinate.getKey();
        int y = message.screenCoordinate.getValue();
        switch (message.type){
            case WORLD_EVENT:
                int textLenght = (int) this.applicationManager.getGraphics2D().getFontMetrics().getStringBounds(message.text, this.applicationManager.getCurrentPanel().getGraphics2D()).getWidth();
                x = Camera.getInstance().getScreenX(message.screenCoordinate.getKey());
                x = x - textLenght/2;
                y = Camera.getInstance().getScreenY(message.screenCoordinate.getValue());
                y -= AppVariables.tileSize/2;
                break;
        }

        this.applicationManager.getCurrentPanel().getUiPanel().drawTextSelection(message,
                x,
                y
        );
        if(message.selected){
            this.applicationManager.getGraphics2D().setColor(message.subWindowFontColor);
        }else{
            this.applicationManager.getGraphics2D().setColor(message.fontColor);
        }
        this.applicationManager.getGraphics2D().drawString(message.text, x, y);
    }

    //SPECIAL GETTER
    public void emptyMessageBox() {
        messages = new HashMap<>();
    }
}
