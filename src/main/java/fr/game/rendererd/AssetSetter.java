package fr.game.rendererd;

import fr.game.main.AppVariables;
import fr.game.constants.game.LightSourceEnum;
import fr.game.constants.rendered.GameEventEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.rendered.entity.EntityEnum;
import fr.game.constants.world.WorldEnum;
import fr.game.constants.world.asset.AssetWorldDescription;
import fr.game.constants.world.asset.EventWorldDescription;
import fr.game.constants.world.asset.NPCWorldDescription;
import fr.game.mechanics.controller.LightController;
import fr.game.panel.game.GameController;
import fr.game.rendererd.entity.NPC.AbstractNPC;
import fr.game.rendererd.entity.NPC.Monster;
import fr.game.rendererd.entity.NPC.Neutral;
import fr.game.rendererd.event.GameEvent;
import fr.game.rendererd.object.GameObject;
import fr.game.rendererd.object.LightSource;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class AssetSetter {

    public AssetSetter() {
    }

    private static void setObject(AssetWorldDescription assetDesc){

        for(Map.Entry<GameObjectEnum, List<Pair<Integer, Integer>>> descriptionEntry : assetDesc.getAssetSetter().entrySet()) {
            for(Pair<Integer,Integer> coordinate : descriptionEntry.getValue()){
                GameController.getInstance().addNewObject(
                        new GameObject(
                                descriptionEntry.getKey(), coordinate.getKey(), coordinate.getValue()));
            }
        }

    }
    private static void setEvent(EventWorldDescription eventDesc){

        for(Map.Entry<GameEventEnum, List<Pair<Integer, Integer>>> descriptionEntry : eventDesc.getAssetSetter().entrySet()) {
            for(Pair<Integer,Integer> coordinate : descriptionEntry.getValue()){
                GameController.getInstance().addNewObject(
                        new GameEvent(
                                descriptionEntry.getKey(), coordinate.getKey(), coordinate.getValue()));
            }
        }
    }
    private static void setNPC(NPCWorldDescription npcDesc){
        for(Map.Entry<EntityEnum, List<Pair<Integer, Integer>>> descriptionEntry : npcDesc.getAssetSetter().entrySet()) {
            for(Pair<Integer,Integer> coordinate : descriptionEntry.getValue()){
                AbstractNPC npcToAdd = null;
                switch (descriptionEntry.getKey()){
                    case GREEN_SLIME:
                        npcToAdd = new Monster(descriptionEntry.getKey(), coordinate.getKey(), coordinate.getValue());
                        break;
                    default:
                        break;
                }
                if(npcToAdd == null){
                    npcToAdd = new Neutral( descriptionEntry.getKey(), coordinate.getKey(), coordinate.getValue());
                }
                GameController.getInstance().addNewObject(npcToAdd);
            }
        }
    }

    public static void setup() {
        WorldEnum worldSelected = TileManager.getInstance().getWorldSelected();
        if(worldSelected != null){
            if (NPCWorldDescription.getDescriptorByWorld(worldSelected) != null) {
                setNPC(NPCWorldDescription.getDescriptorByWorld(worldSelected));
            }
            if (NPCWorldDescription.getDescriptorByWorld(worldSelected) != null) {

                setObject(AssetWorldDescription.getDescriptorByWorld(worldSelected));
            }
            if (NPCWorldDescription.getDescriptorByWorld(worldSelected) != null) {

                setEvent(EventWorldDescription.getDescriptorByWorld(worldSelected));
            }
            setLightSource(AssetWorldDescription.getDescriptorByWorld(worldSelected));
        }
    }

    private static void setLightSource(AssetWorldDescription descriptorByWorld) {
        LightSource lightSource1 = new LightSource(LightSourceEnum.GROUND_TORCH,
                (GameController.getInstance().getPlayer().getWorldX()/ AppVariables.tileSize)-1,
                (GameController.getInstance().getPlayer().getWorldY()/ AppVariables.tileSize));
        LightSource lightSource2 = new LightSource(LightSourceEnum.GROUND_TORCH,
                46,9);
        LightSource lightSource3 = new LightSource(LightSourceEnum.GROUND_TORCH,
                38,10);
        LightSource lightSource4 = new LightSource(LightSourceEnum.GROUND_TORCH,
                36,18);

        GameController.getInstance().addNewObject(lightSource4);
        LightController.getInstance().addLightSource(lightSource4);
        GameController.getInstance().addNewObject(lightSource3);
        LightController.getInstance().addLightSource(lightSource3);
        GameController.getInstance().addNewObject(lightSource2);
        LightController.getInstance().addLightSource(lightSource2);
        GameController.getInstance().addNewObject(lightSource1);
        LightController.getInstance().addLightSource(lightSource1);
    }
}
