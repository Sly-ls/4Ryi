package fr.game.main.utils;

import fr.game.constants.InInventory;
import fr.game.constants.core.FontEnum;
import fr.game.constants.core.IconSizeEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.SkillnEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.rendered.entity.ClassCodex;
import fr.game.constants.rendered.entity.RaceCodex;
import fr.game.main.AppVariables;
import fr.game.mechanics.core.ScaledImage;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BufferedObjects {

    //SINGLETON
    static BufferedObjects instance;
    private BufferedObjects(){
        initFont();
        resetIconTotileSize();
    }
    public static BufferedObjects getInstance(){
        if(instance==null){
            instance = new BufferedObjects();
        }
        return instance;
    }

    //CLASS FIELDS
    Map<FontEnum, Font> bufferedFonts = new HashMap<>();
    Map<IconSizeEnum, Map<InInventory, ScaledImage>> bufferedIconsBySize;

    //Class Method
    public void resetIconTotileSize(){

        bufferedIconsBySize = new HashMap<>();
        addNewValueToBufferedIcon(Arrays.asList(GameObjectEnum.values()));
        addNewValueToBufferedIcon(Arrays.asList(GameValueEnum.values()));
        addNewValueToBufferedIcon(Arrays.asList(ClassCodex.values()));
        addNewValueToBufferedIcon(Arrays.asList(SkillnEnum.values()));
        addNewValueToBufferedIcon(Arrays.asList(RaceCodex.values()));
    }
    //CALLED ONLY IN CONSTRUCTOR
    private void initFont(){
        try {
            for (FontEnum font : FontEnum.values()) {
                InputStream is = Graphics2DUtils.class.getResourceAsStream(font.getPath());
                bufferedFonts.put(font, Font.createFont(Font.TRUETYPE_FONT, is));
                is.close();
            }
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //CALLED from public method
    private void addNewValueToBufferedIcon(List<InInventory> listOfValues){

        for(InInventory typeEnum : listOfValues){
            for(IconSizeEnum iconSize : IconSizeEnum.values()){
                addNewValueToBufferedIcon(iconSize, typeEnum);
            }
        }
    }
    private void addNewValueToBufferedIcon(IconSizeEnum size, InInventory object){
        int width = 0;
        int height = 0;
        int x = 0;
        int y = 0;
        switch (size){
            case XS:
                width = AppVariables.tileSize / 4;
                height = AppVariables.tileSize / 4;
                break;
            case SMALL:
                width = AppVariables.tileSize / 2;
                height = AppVariables.tileSize / 2;
                break;
            case MEDIUM:
                width = AppVariables.tileSize;
                height = AppVariables.tileSize;
                break;
            case BIG:
                width = AppVariables.tileSize * 2;
                height = AppVariables.tileSize * 2;
                break;
            case XL:
                width = AppVariables.tileSize * 4;
                height = AppVariables.tileSize * 4;
                break;
            default:
                break;
        }
        ScaledImage image = new ScaledImage(object.getImagesPath(), x, y, width, height);
        Map<InInventory, ScaledImage> sizedMap = bufferedIconsBySize.getOrDefault(size, new HashMap<>());;
        sizedMap.put(object, image);
        bufferedIconsBySize.put(size, sizedMap);
    }

    //SPECIAL GETTER AND SETTER
    public Font getFont(FontEnum fontType){
        Font fontToReturn = bufferedFonts.get(fontType);
        if(fontToReturn == null){
            fontToReturn = bufferedFonts.get(FontEnum.ARIAL);
        }
        return fontToReturn;
    }


    public ScaledImage getIcon(InInventory icon, IconSizeEnum iconSize){
        ScaledImage iconToReturn = null;
        Map<InInventory, ScaledImage> mapOfIcons = bufferedIconsBySize.get(iconSize);
        if(mapOfIcons != null){
            iconToReturn = mapOfIcons.get(icon);
        }
        return iconToReturn;
    }
    //GETTER and SETTER
    public Map<FontEnum, Font> getBufferedFonts() {
        return bufferedFonts;
    }
    public Map<IconSizeEnum, Map<InInventory, ScaledImage>> getBufferedIconsBySize() {
        return bufferedIconsBySize;
    }

}
