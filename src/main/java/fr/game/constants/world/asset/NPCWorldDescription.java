package fr.game.constants.world.asset;

import fr.game.constants.rendered.entity.EntityEnum;
import fr.game.constants.world.WorldEnum;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NPCWorldDescription {

    NPC_SHEET_CUTE_MAP_BLUEBOY_TUTO(WorldEnum.CUTE_MAP_BLUEBOY_TUTO, new HashMap(){
        {
            put(EntityEnum.OLD_MAN, new ArrayList(){
                {add(new Pair<>(26, 32));}
                {add(new Pair<>(26, 21));}
                {add(new Pair<>(28, 23));}
            });
        }
    }),
    NPC_SHEET_TREASURE_HUNT(WorldEnum.TREASURE_HUNT, new HashMap(){
        {
            put(EntityEnum.OLD_MAN, new ArrayList(){
                {add(new Pair<>(26, 32));}
                {add(new Pair<>(26, 21));}
                {add(new Pair<>(28, 23));}});
        }
    }),
    NPC_SHEET_TEST_MAP(WorldEnum.TEST_MAP, new HashMap(){
        {
            put(EntityEnum.OLD_MAN, new ArrayList(){
                {add(new Pair<>(28, 33));}});
            put(EntityEnum.GREEN_SLIME, new ArrayList(){
                {add(new Pair<>(36, 9));}
                {add(new Pair<>(37, 10));}
                {add(new Pair<>(38, 11));}
                {add(new Pair<>(39, 10));}});
            put(EntityEnum.CHIKEN, new ArrayList(){
                {add(new Pair<>(10, 9));}});
            put(EntityEnum.BLACK_DOG, new ArrayList(){
                {add(new Pair<>(10, 31));}});
            put(EntityEnum.LADY_MAGE, new ArrayList(){
                {add(new Pair<>(26, 28));}});
            put(EntityEnum.MAGIC_SWORD, new ArrayList(){
                {add(new Pair<>(27, 27));}});
            put(EntityEnum.SIREN_MAGE, new ArrayList(){
                {add(new Pair<>(35, 11));}});
        }
    }),
    NPC_SHEET_THE_ISLAND(WorldEnum.THE_ISLAND, new HashMap()),
    NPC_SHEET_SPIDERLAND(WorldEnum.SPIDERLAND, new HashMap());
    WorldEnum worldEnum;
    Map<EntityEnum, List<Pair<Integer,Integer>>> assetSetter;

    NPCWorldDescription(WorldEnum worldEnum, Map<EntityEnum, List<Pair<Integer, Integer>>> assetSetter) {
        this.worldEnum = worldEnum;
        this.assetSetter = assetSetter;
    }

    public WorldEnum getWorldEnum() {
        return worldEnum;
    }

    public Map<EntityEnum, List<Pair<Integer, Integer>>> getAssetSetter() {
        return assetSetter;
    }

    public static NPCWorldDescription getDescriptorByWorld(WorldEnum worldSelectedCode) {
        for(NPCWorldDescription assetDesc : NPCWorldDescription.values()){
            if(assetDesc.getWorldEnum() == worldSelectedCode){
                return assetDesc;
            }
        }
        return null;
    }
}
