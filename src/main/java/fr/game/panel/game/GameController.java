package fr.game.panel.game;

import fr.game.main.AppVariables;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.SoundDescriptionEnum;
import fr.game.constants.rendered.entity.EntityEnum;
import fr.game.mechanics.AbstractController;
import fr.game.mechanics.controller.Camera;
import fr.game.mechanics.controller.SoundController;
import fr.game.rendererd.AbstractRendered;
import fr.game.rendererd.AssetSetter;
import fr.game.rendererd.TileManager;
import fr.game.rendererd.entity.NPC.AbstractNPC;
import fr.game.rendererd.entity.PC.Player;
import fr.game.rendererd.event.GameEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameController extends AbstractController{

    //SINGLETON
    static GameController instance;
    private GameController(GamePanel panel){
        super(panel);
        this.gamePanel = panel;
    }
    private GameController(){
        super(null);
    }
    public static GameController createInstance(GamePanel panel) {
        if(instance == null) {
            instance = new GameController(panel);
        }else{
            instance.panel = panel;
            gamePanel = panel;
        }
        return instance;
    }
    public static GameController getInstance() {
        if(instance==null){
            instance = new GameController();
        }
        return instance;
    }

    /*le game controller ne devrait faire qu'echanger des messages avec le panel.
            Pour les tiles, c'est le tileManager qui fait le draw
            puis les AbstractRendered
            puis l'UI, puis les message
            chaque composant est chargé de son rendu avec sa method draw
            C'est lui qui fournit la liste des update et des draws
            Ils contient aussi les autres singleton pour
            la map (TileManager)
            les object (gameObjects
         */
    //pour les changement de controller et de key handler
    private Player player;
    private List<AbstractRendered> gameObjects = new ArrayList<>();
    private static GamePanel gamePanel;
    private Comparator coordinateComparator = new CoordinateComparator();


    public void addNewObject(AbstractRendered newObject){
        gameObjects.add(newObject);
    }
    public void removeObjectFromTheWorld(AbstractRendered abstractRendered){
        this.gameObjects.remove(abstractRendered);
    }
    public void setupGame(){
        TileManager.getInstance().loadWorld();
        int playerX = TileManager.getInstance().getWorldSelected().getPlayerStartingX();
        int playerY = TileManager.getInstance().getWorldSelected().getPlayerStartingY();
        if(player == null) {
            this.player = new Player(EntityEnum.BLUE_BOY, playerX, playerY);
        }else{
            player.setWorldX(playerX * AppVariables.tileSize);
            player.setWorldY(playerY * AppVariables.tileSize);
        }
        SoundController.getInstance().playMusic(SoundDescriptionEnum.BLUEBOYADVENTURE);
        AssetSetter.setup();
    }


    public List<AbstractRendered> getListToDisplay() {

        List<AbstractRendered> listToDisplay = new ArrayList<>();
        if (player != null) {
            listToDisplay.add(player);
            Camera.getInstance().centerCameraOnPlayer(player);
        }
        for (AbstractRendered objectToUpdate : gameObjects) {
            if(Camera.getInstance().isDisplayable(objectToUpdate)) {
                listToDisplay.add(objectToUpdate);
            }
        }
        listToDisplay.sort(coordinateComparator);
        return listToDisplay;
    }
    public List<AbstractRendered> getListToUpdate() {
        List<AbstractRendered> listToUpdate = new ArrayList<>();
        List<AbstractRendered> listToRemove = new ArrayList<>();
        if (player != null) {
            listToUpdate.add(player);
        }
        for (AbstractRendered objectInWorld : gameObjects) {

            if((objectInWorld  instanceof AbstractNPC)
                    && !((AbstractNPC)objectInWorld).isAlive() && ((AbstractNPC)objectInWorld).getAttributes(GameValueEnum.INVINCIBLE) == 0){
                listToRemove.add(objectInWorld);
            }else if((objectInWorld  instanceof GameEvent)
                    && (!(((GameEvent) objectInWorld).isReset()) && ((GameEvent) objectInWorld).getQuantity() <= 0)){
                listToRemove.add(objectInWorld);
            }else {
                listToUpdate.add(objectInWorld);
            }
        }
        if(listToRemove.size()>0){
            for(AbstractRendered objectToRemoce : listToRemove){
                removeObjectFromTheWorld(objectToRemoce);
            }
        }
        return listToUpdate;
    }

    //GETTER & SETTER
    public List<AbstractRendered> gameObjects() {
        return gameObjects;
    }
    public GamePanel getPanel() {
        return gamePanel;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    //COMPARATOR
    public static class CoordinateComparator implements Comparator<AbstractRendered> {

        @Override
        public int compare(AbstractRendered rendered1, AbstractRendered rendered2) {
            return Integer.compare(rendered1.getWorldY(), rendered2.getWorldY());
        }
    }

}
