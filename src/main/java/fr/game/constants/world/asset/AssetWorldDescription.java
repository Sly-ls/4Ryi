package fr.game.constants.world.asset;

import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.world.WorldEnum;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AssetWorldDescription {

    ASSET_CUTE_MAP_BLUEBOY_TUTO(WorldEnum.CUTE_MAP_BLUEBOY_TUTO, new HashMap(){
        {
            put(GameObjectEnum.YELLOW_KEY, new ArrayList(){
                {add(new Pair<>(24,23));}
            });
            put(GameObjectEnum.DOOR, new ArrayList(){
                {add(new Pair<>(24,19));}
            });
            put(GameObjectEnum.RED_KEY, new ArrayList(){
                {add(new Pair<>(21,24));}
            });
            put(GameObjectEnum.RED_DOOR, new ArrayList(){
                {add(new Pair<>(21,19));}
            });
            put(GameObjectEnum.CHEST, new ArrayList(){
                {add(new Pair<>(27,25));}
            });
            put(GameObjectEnum.BOOT, new ArrayList(){
                {add(new Pair<>(26,23));}
            });
        }
    }),
    ASSET_TREASURE_HUNT(WorldEnum.TREASURE_HUNT, new HashMap(){
        {
            put(GameObjectEnum.YELLOW_KEY, new ArrayList(){
                {add(new Pair<>(24,23));}
            });
            put(GameObjectEnum.DOOR, new ArrayList(){
                {add(new Pair<>(24,19));}
            });
            put(GameObjectEnum.RED_KEY, new ArrayList(){
                {add(new Pair<>(21,24));}
            });
            put(GameObjectEnum.RED_DOOR, new ArrayList(){
                {add(new Pair<>(21,19));}
            });
            put(GameObjectEnum.CHEST, new ArrayList(){
                {add(new Pair<>(27,25));}
            });
            put(GameObjectEnum.BOOT, new ArrayList(){
                {add(new Pair<>(26,23));}
            });
        }
    }),

    ASSET_TEST_MAP(WorldEnum.TEST_MAP, new HashMap(){
        {
            put(GameObjectEnum.YELLOW_KEY, new ArrayList(){
                {add(new Pair<>(24,23));}
            });
            put(GameObjectEnum.DOOR, new ArrayList(){
                {add(new Pair<>(24,19));}
            });
            put(GameObjectEnum.RED_KEY, new ArrayList(){
                {add(new Pair<>(21,24));}
            });
            put(GameObjectEnum.RED_DOOR, new ArrayList(){
                {add(new Pair<>(21,19));}
            });
            put(GameObjectEnum.CHEST, new ArrayList(){
                {add(new Pair<>(27,25));}
            });
            put(GameObjectEnum.BOOT, new ArrayList(){
                {add(new Pair<>(26,23));}
            });
        }
    }),
    ASSET_THE_ISLAND(WorldEnum.THE_ISLAND, new HashMap(){
        {
            put(GameObjectEnum.YELLOW_KEY, new ArrayList(){
                {add(new Pair<>(44,93));}
                {add(new Pair<>(21,80));}
                {add(new Pair<>(29,46));}
                {add(new Pair<>(21,33));}
                {add(new Pair<>(1,36));}
            });
            put(GameObjectEnum.DOOR, new ArrayList(){
                {add(new Pair<>(5,84));}
                {add(new Pair<>(14,66));}
                {add(new Pair<>(39,54));}
                {add(new Pair<>(15,32));}
                {add(new Pair<>(19,17));}
            });
            put(GameObjectEnum.RED_KEY, new ArrayList(){
                {add(new Pair<>(4,79));}
                {add(new Pair<>(7,55));}
                {add(new Pair<>(38,7));}
                {add(new Pair<>(26,22));}
            });
            put(GameObjectEnum.RED_DOOR, new ArrayList(){
                {add(new Pair<>(44,85));}
                {add(new Pair<>(31,62));}
                {add(new Pair<>(35,45));}
                {add(new Pair<>(6,8));}
            });
            put(GameObjectEnum.CHEST, new ArrayList(){
                {add(new Pair<>(8,10));}
            });
            put(GameObjectEnum.BOOT, new ArrayList(){
                {add(new Pair<>(23,83));}
                {add(new Pair<>(35,18));}
            });
        }
    }),
    ASSET_SPIDERLAND(WorldEnum.SPIDERLAND, new HashMap(){
        {
            put(GameObjectEnum.YELLOW_KEY, new ArrayList(){
                {add(new Pair<>(44,93));}
                {add(new Pair<>(21,80));}
                {add(new Pair<>(29,46));}
                {add(new Pair<>(21,33));}
                {add(new Pair<>(1,36));}
            });
            put(GameObjectEnum.DOOR, new ArrayList(){
                {add(new Pair<>(5,84));}
                {add(new Pair<>(14,66));}
                {add(new Pair<>(39,54));}
                {add(new Pair<>(15,32));}
                {add(new Pair<>(19,17));}
            });
            put(GameObjectEnum.RED_KEY, new ArrayList(){
                {add(new Pair<>(4,79));}
                {add(new Pair<>(7,55));}
                {add(new Pair<>(38,7));}
                {add(new Pair<>(26,22));}
            });
            put(GameObjectEnum.RED_DOOR, new ArrayList(){
                {add(new Pair<>(44,85));}
                {add(new Pair<>(31,62));}
                {add(new Pair<>(35,45));}
                {add(new Pair<>(6,8));}
            });
            put(GameObjectEnum.CHEST, new ArrayList(){
                {add(new Pair<>(50,51));}
            });
            put(GameObjectEnum.BOOT, new ArrayList(){
                {add(new Pair<>(23,83));}
                {add(new Pair<>(35,18));}
            });
        }
    });

    WorldEnum worldEnum;
    Map<GameObjectEnum, List<Pair<Integer,Integer>>> assetSetter;

    AssetWorldDescription(WorldEnum worldEnum, Map<GameObjectEnum, List<Pair<Integer, Integer>>> assetSetter) {
        this.worldEnum = worldEnum;
        this.assetSetter = assetSetter;
    }

    public WorldEnum getWorldEnum() {
        return worldEnum;
    }

    public Map<GameObjectEnum, List<Pair<Integer, Integer>>> getAssetSetter() {
        return assetSetter;
    }


    public static AssetWorldDescription getDescriptorByWorld(WorldEnum worldSelectedCode) {
        for(AssetWorldDescription assetDesc : AssetWorldDescription.values()){
            if(assetDesc.getWorldEnum() == worldSelectedCode){
                return assetDesc;
            }
        }
        return null;
    }

}
