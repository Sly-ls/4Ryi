package fr.game.panel.menu;

import fr.game.constants.core.DirectionsEnum;
import fr.game.constants.panel.PanelEnum;
import fr.game.constants.world.WorldEnum;
import fr.game.main.utils.NumberUtils;
import fr.game.mechanics.AbstractController;
import fr.game.mechanics.core.Inventory;
import fr.game.mechanics.core.Message;
import fr.game.panel.AbstractPanel;
import fr.game.panel.menu.core.MenuSlot;
import fr.game.panel.menu.core.MenuSlotActionEnum;
import fr.game.panel.menu.creation.EntityCreationPanel;
import fr.game.rendererd.TileManager;
import javafx.util.Pair;

import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuController extends AbstractController {

    //SINGLETON
    static MenuController instance;
    private MenuController(AbstractPanel panel){
        super(panel);
        this.menuSlotInPanel = new ConcurrentHashMap<>();
        this.cursor = new Pair<>(0,0);
        this.drawingCursor = new Pair<>(0,0);
        this.pointPools = new Inventory<>();
    }
    public static MenuController createInstance(AbstractPanel panelWithCursor) {
        instance = new MenuController(panelWithCursor);
        return instance;
    }
    public static MenuController getInstance() {
        if(instance==null){
            //lévera une NullPointerException
        }
        return instance;
    }

    private Pair<Integer,Integer> cursor;
    private Pair<Integer,Integer> drawingCursor;
    Map<Pair<Integer,Integer>, MenuSlot> menuSlotInPanel;
    Inventory<MenuPointEnum> pointPools;

    //METHOD CLASS
    public void resetBeforeDraw(){
        this.menuSlotInPanel = new ConcurrentHashMap<>();
        this.drawingCursor = new Pair<>(0,0);
    }
    public MenuSlot getSelectedSlot() {
        return getSelectedSlot(cursor.getKey(), cursor.getValue());
    }
    public MenuSlot getSelectedSlot(int x, int y) {
        return menuSlotInPanel.get(new Pair<>(x, y));
    }
    public void setCursor(DirectionsEnum direction) {

        //FIXME
        // Keyboard vwon't,be working for menu until this is fixed


//        Set<Pair<Integer, Integer>> listOfCoordinate = this.menuSlotInPanel.keySet();
//        int newX =this.cursor.getKey();
//        int newY =this.cursor.getValue();
//        //UP by default
//        int max = NumberUtils.getCoordinate(listOfCoordinate,
//                newX, false, true, false);
//
//
//        switch (direction){
//            case UP:
//                newY = NumberUtils.changeValue(-1,
//                        this.cursor.getValue(),
//                        max, NumberUtils.NumberLoopType.LOOP_PLUS);
//                break;
//            case DOWN:
//                max = NumberUtils.getCoordinate(listOfCoordinate,
//                        newX, false, true, false);
//                newY =NumberUtils.changeValue(+1,
//                        this.cursor.getValue(),
//                        max, NumberUtils.NumberLoopType.LOOP_PLUS);
//                break;
//            case RIGHT:
//                max = NumberUtils.getCoordinate(listOfCoordinate,
//                        newY, true, true, false);
//                newX =NumberUtils.changeValue(+1,
//                        this.cursor.getKey(),
//                        max, NumberUtils.NumberLoopType.LOOP_PLUS);
//                break;
//            case LEFT:
//                max = NumberUtils.getCoordinate(listOfCoordinate,
//                        newY, true, true, false);
//                newX =NumberUtils.changeValue(-1,
//                        this.cursor.getKey(),
//                        max, NumberUtils.NumberLoopType.LOOP_PLUS);
//                break;
//        }
//        if(null == getSelectedSlot(newX, newY)){
//            if (newY != this.cursor.getValue()){
//                this.cursor = new Pair<>(0,newY);
//            }
//            if (newX != this.cursor.getKey()){
//                this.cursor = new Pair<>(newX,0);
//            }
//            if(getSelectedSlot() != null){
//                this.cursor = new Pair<>(0,0);
//            }
//        }else{
//            this.setCursor(new Pair<>(newX,newY));
//        }
    }
    public void useSelectedMenuSlot() {
        MenuSlot slotUsed = getSelectedSlot();
        if(slotUsed != null){
            if(slotUsed.getSlotActions().contains(MenuSlotActionEnum.QUIT)){
                System.exit(0);
            }
            if(slotUsed.getSlotActions() != null){
                String[] parameters = slotUsed.getParameters();
                if(slotUsed.getSlotActions().contains(MenuSlotActionEnum.VALIDATE_CREATION)){
                    ((EntityCreationPanel)this.panel).validateCreation();
                }
                if(slotUsed.getSlotActions().contains(MenuSlotActionEnum.CHANGE_WORLD)){
                    TileManager.getInstance().setWorldSelected(WorldEnum.valueOf(parameters[1]));
                }
                if(slotUsed.getSlotActions().contains(MenuSlotActionEnum.CHANGE_PANEL)){
                    this.panel.getApplicationManager().displayNewPanel(PanelEnum.valueOf(parameters[0]));;
                }
                if(slotUsed.getSlotActions().contains(MenuSlotActionEnum.CHANGE_VALUE)){
                    if(slotUsed.getGameValue() != null) {
                        int valueToAdd = Integer.parseInt(parameters[1]);
                        if (parameters != null && parameters[0] != null &&
                                !String.valueOf(parameters[0]).equalsIgnoreCase("")) {
                            MenuPointEnum menuPointType = MenuPointEnum.valueOf(parameters[0]);
                            if( this.pointPools.getInventory(menuPointType) - valueToAdd >= 0
                                    && valueToAdd != 0
                                    && slotUsed.getGameValue().getValue() + valueToAdd <= slotUsed.getGameValue().getMaxValue()
                                    && slotUsed.getGameValue().getValue() + valueToAdd >= slotUsed.getGameValue().getMinValue()){
                                this.pointPools.changeQuantity(menuPointType, -valueToAdd );
                                slotUsed.getGameValue().changeValue(valueToAdd);
                            }
                        }else{
                            slotUsed.getGameValue().changeValue(valueToAdd);
                        }
                    }
                }
            }
        }
    }
    public MenuSlot addMenuSlot(String parameters, Message message, DirectionsEnum direction) {
        MenuSlot newMenuSlot = new MenuSlot(parameters, message);
        initMenuCoordinate(newMenuSlot, direction);
        if(message.selected) {
            Pair<Integer, Integer> cursor = MenuController.getInstance().getCursor();
            message.selected = cursor.getKey() == newMenuSlot.getMenuX() && cursor.getValue() == newMenuSlot.getMenuY();
            if(message.selected){
                newMenuSlot.setX(newMenuSlot.getX() - message.padding);
                newMenuSlot.setY(newMenuSlot.getY() - message.padding);
                newMenuSlot.setWidth(message.textBound.getKey() + message.padding*2);
                newMenuSlot.setHeight(message.textBound.getValue() + message.padding*2);
            }
        }
        menuSlotInPanel.put(new Pair<>(newMenuSlot.getMenuX(),newMenuSlot.getMenuY()),newMenuSlot);
        return newMenuSlot;
    }
    private void initMenuCoordinate(MenuSlot newMenuSlot, DirectionsEnum direction){
        int slotX = drawingCursor.getKey();
        int slotY = drawingCursor.getValue();

//      as I should only extend the menu, no need to go back... i believe... so no need to go up or left
        switch (direction){
            case DOWN:
                slotY++;
                break;
            case RIGHT:
                slotX++;
                slotY=0;
                break;
            default:
                break;
        }

        this.drawingCursor = new Pair<>(slotX,slotY);

        newMenuSlot.setMenuX(slotX);
        newMenuSlot.setMenuY(slotY);
    }

    //DEBUG METHOD

    //GETTER && SETTER
    public Pair<Integer, Integer> getCursor() {
        return cursor;
    }
    public void setCursor(Pair<Integer, Integer> cursor) {
        this.cursor = cursor;
    }
    public Inventory<MenuPointEnum> getPointPools() {
        return pointPools;
    }
    public void setPointPools(Inventory<MenuPointEnum> pointPools) {
        this.pointPools = pointPools;
    }
    public Map<Pair<Integer, Integer>, MenuSlot> getMenuSlotInPanel() {
        return menuSlotInPanel;
    }
    public Pair<Integer, Integer> getDrawingCursor() {
        return drawingCursor;
    }
    public void setDrawingCursor(Pair<Integer, Integer> drawingCursor) {
        this.drawingCursor = drawingCursor;
    }

    public void drawDebug(Graphics2D graphics2D) {
        for(MenuSlot menuSlot : this.menuSlotInPanel.values()){
            menuSlot.drawDebug(graphics2D);
        }
    }

    //ENUM FOR POOL
    public enum MenuPointEnum {
        POOL_CREATION
    }
}
