package fr.game.mechanics.controller;

import fr.game.constants.world.TimeOfDayEnum;
import fr.game.main.AppConstants;
import fr.game.main.AppVariables;
import fr.game.constants.core.MessageTypeEnum;
import fr.game.constants.world.DayDescriptorEnum;
import fr.game.main.utils.NumberUtils;
import fr.game.mechanics.core.MessageBox;
import fr.game.rendererd.object.LightSource;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LightController {
    //SINGLETON
    static LightController instance;
    private LightController() {
        this.dayDescriptorEnum = DayDescriptorEnum.DAY_1;
        this.nbOfCounterInADay = AppVariables.FPS*AppConstants.SECOND_IN_MINUTES*dayDescriptorEnum.getDaylLengthInMinute();
        this.timeOfDusk = dayDescriptorEnum.getDuskHour() * (nbOfCounterInADay/24);
        this.timeOfDawn = dayDescriptorEnum.getDawnHour() * (nbOfCounterInADay/24);
        int timeToDawn = (nbOfCounterInADay/24) * dayDescriptorEnum.getDawnSpeed();
        int timeToDusk = (nbOfCounterInADay/24) * dayDescriptorEnum.getDuskSpeed();
        this.tickForDawn = timeToDawn/dayTransparencyMax;
        this.tickForDusk = timeToDusk/dayTransparencyMax;
        if(AppConstants.DEBUG && AppConstants.DEBUG_WITH_DAYLIGHT) {
            timeCounter = tickForDusk;
        }else{
            timeCounter = nbOfCounterInADay/2;
        }
    }
    public static LightController getInstance() {
        if(instance == null){
            instance = new LightController();
        }
        return instance;
    }

    //FIELDS
    DayDescriptorEnum dayDescriptorEnum;
    int sunset = 0;
    int timeCounter = 0;
    int dayCounter = 0;
    int dayTransparency = 0;
    int dayTransparencyMax = 235;
    int dayTransparencyMin = 0;
    int timeOfDusk = 0;
    int timeOfDawn = 0;
    int tickForDusk = 0;
    int tickForDawn = 0;
    int nbOfCounterInADay;
    TimeOfDayEnum timeOfDay;
    List<LightSource> lightSourceList = new ArrayList<>();
    Area dayArea = new Area();
    //GRAPHICS METHOD
    public void update(){
        timeCounter++;
        sunset++;
        if(timeCounter > timeOfDawn && timeCounter < timeOfDusk && dayTransparency > dayTransparencyMin){
            if(sunset >= tickForDawn){
                sunset=0;
                dayTransparency = NumberUtils.changeValue(-1,dayTransparency,dayTransparencyMax,NumberUtils.NumberLoopType.NO_LOOP);
                changeTimeOfTheDay(TimeOfDayEnum.DAWN);
            }else{
                sunset++;
            }
        }else if(((timeCounter < timeOfDawn && timeCounter < timeOfDusk)
                || (timeCounter > timeOfDawn && timeCounter > timeOfDusk))
                && dayTransparency < dayTransparencyMax){
            if(sunset >= tickForDusk){
                sunset=0;
                dayTransparency = NumberUtils.changeValue(1,dayTransparency,dayTransparencyMax,NumberUtils.NumberLoopType.NO_LOOP);
                changeTimeOfTheDay(TimeOfDayEnum.DUSK);
            }else{
                sunset++;
            }
        }
        if(dayTransparency == 0){
            changeTimeOfTheDay(TimeOfDayEnum.SUN);
        }else if (dayTransparency == dayTransparencyMax){
            changeTimeOfTheDay(TimeOfDayEnum.NIGTH);
        }
        if(timeCounter >= nbOfCounterInADay){
            timeCounter =0;
            dayCounter++;
            if(AppConstants.DEBUG){
                MessageBox.getInstance().sendMessage(MessageTypeEnum.MESSAGE_TO_PLAYER,"Day " + dayCounter, 0,0,true);
            }
        }
    }

    public void drawNaturalLight(Graphics2D graphics2D) {
        if (!AppConstants.DEBUG ||(AppConstants.DEBUG &&AppConstants.DEBUG_WITH_DAYLIGHT)) {
            dayArea = new Area(new Rectangle(0, 0, AppVariables.SCREEN_WIDTH, AppVariables.SCREEN_HEIGHT));
            drawLightSource(graphics2D);
            graphics2D.setColor(new Color(0, 0, 0, dayTransparency));
            graphics2D.fill(dayArea);
            graphics2D.draw(dayArea);
        }
    }
    public void drawLight(Graphics2D graphics2D) {
        drawNaturalLight(graphics2D);
        drawLightSource(graphics2D);

    }

    public void drawLightSource(Graphics2D graphics2D) {
        if(TimeOfDayEnum.SUN != this.timeOfDay && dayTransparency > 100){
            for(LightSource lightSource : lightSourceList) {
                int x = lightSource.getWorldX() - ((lightSource.getPower()* AppVariables.scale)/2);
                int y = lightSource.getWorldY() - ((lightSource.getPower()* AppVariables.scale)/2);
                x = Camera.getInstance().getScreenX(x)+AppVariables.tileSize/2;
                y = Camera.getInstance().getScreenY(y)+AppVariables.tileSize/2;

                Ellipse2D.Double ligth = new Ellipse2D.Double(x,y,
                        lightSource.getPower()* AppVariables.scale,
                        lightSource.getPower()* AppVariables.scale);
                dayArea.subtract(new Area(ligth));
                graphics2D.setColor(new Color(105, 52, 26, 100));
                graphics2D.fill(ligth);
                graphics2D.draw(ligth);
            }
        }
    }
    //LightSource method
    private void changeTimeOfTheDay(TimeOfDayEnum timeOfDay){
        if (this.timeOfDay != timeOfDay){
            this.timeOfDay = timeOfDay;
            if(AppConstants.DEBUG){
                System.out.println(new StringBuffer(this.getClass().getName().toUpperCase(Locale.ROOT))
                        .append(" time is ")
                        .append(this.timeOfDay.name())
                        .append("- transparency :")
                        .append(dayTransparency));
            }
        }
    }
    public void addLightSource(LightSource lightSource){
        if (lightSource != null) {
            this.lightSourceList.add(lightSource);
        }
    }
}