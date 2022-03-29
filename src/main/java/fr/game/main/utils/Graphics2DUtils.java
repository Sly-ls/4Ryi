package fr.game.main.utils;

import fr.game.constants.InInventory;
import fr.game.constants.core.IconSizeEnum;
import fr.game.constants.game.GameValueEnum;
import fr.game.constants.game.SkillnEnum;
import fr.game.constants.rendered.GameObjectEnum;
import fr.game.constants.rendered.entity.ClassCodex;
import fr.game.constants.rendered.entity.RaceCodex;
import fr.game.main.AppVariables;
import fr.game.constants.core.FontEnum;
import fr.game.mechanics.core.ScaledImage;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphics2DUtils {


    public static void setUIFont(Graphics2D graphics2D, FontEnum font, int size, Color color){
        setFont(graphics2D, font);
        setFontColor(graphics2D, color);
        setSize(graphics2D, size);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
    public static void setFont(Graphics2D graphics2D,FontEnum fontType){
        Font fontToset = BufferedObjects.getInstance().getFont(fontType);
        graphics2D.setFont(fontToset);
    }
    public static void setFontColor(Graphics2D graphics2D,Color color){
        if(color == null) {
            graphics2D.setColor(Color.WHITE);
        }else{
            graphics2D.setColor(color);
        }
    }
    public static void setSize(Graphics2D graphics2D, int size){
        if(size < 0){
            size = 100;
            setFontColor(graphics2D, Color.RED);
        }
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, size));
    }

    protected static int getXForCenteredText(Graphics2D graphics2D, String text){
        int textLenght = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = AppVariables.SCREEN_WIDTH /2 - textLenght/2;
        return  x ;
    }
    protected static int getYForCenteredText(Graphics2D graphics2D, String text){
        int textHeight = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getHeight();
        int y = AppVariables.SCREEN_HEIGHT /2 - textHeight/2;
        return  y ;
    }
}
