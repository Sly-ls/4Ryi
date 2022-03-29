package fr.game.main.utils;

import javafx.util.Pair;

import java.util.Set;

public class NumberUtils {

    public static int changeValue(int change, int originalValue, int maxValue, NumberLoopType loop){
        return changeValue(change,originalValue,0,maxValue,loop);
    }


    public static int changeValue(int change, int originalValue, int minValue, int maxValue, NumberLoopType loop) {
        change+=originalValue;
        if (change > maxValue){
            if(NumberLoopType.LOOP_STOP == loop)  {
                change = minValue;
            }else if(NumberLoopType.LOOP_PLUS == loop) {
                change =change-maxValue;
            }else  if(NumberLoopType.NO_LOOP == loop) {
                change = maxValue;
            }
        }else if(change < 0){
            if(NumberLoopType.LOOP_STOP == loop) {
                change = maxValue;
            }else if(NumberLoopType.LOOP_PLUS == loop) {
                change =minValue-change;
            }else  if(NumberLoopType.NO_LOOP == loop) {
                change = minValue;
            }
        }
        return change;
    }

    public static int getCoordinateOLD(Set<Pair<Integer, Integer>> listOfCoordinate, int criteria, boolean getX, boolean max, boolean nextValid) {
        boolean firstCoordinate = true;
        boolean foundOne = false;
        int toReturn =0;
        for(Pair<Integer,Integer> coordinateToVerify : listOfCoordinate){
            boolean criteriaVerified;
            if(getX){
                criteriaVerified = coordinateToVerify.getValue() == criteria;
            }else {
                criteriaVerified = coordinateToVerify.getKey() == criteria;
            }
            if(criteriaVerified){
                foundOne = true;
                if(getX){
                    if(max){
                        toReturn = Math.max(coordinateToVerify.getKey(), toReturn);
                    }else{
                        if(firstCoordinate || toReturn > coordinateToVerify.getKey()){
                            if(firstCoordinate) {
                                firstCoordinate = false;
                            }
                            toReturn = coordinateToVerify.getKey();
                        }
                    }
                }else{
                    if(max){
                        toReturn = Math.max(coordinateToVerify.getValue(), toReturn);
                    }else{
                        if(firstCoordinate || toReturn > coordinateToVerify.getValue()){
                            if(firstCoordinate) {
                                firstCoordinate = false;
                            }
                            toReturn = coordinateToVerify.getValue();
                        }
                    }
                }
            }
        }
        if(nextValid && foundOne){
            toReturn++;
        }
        return toReturn;
    }
    public enum NumberLoopType{
        LOOP_STOP,
        //FIXME LOOP_PLUS is not working properly,
        LOOP_PLUS,
        NO_LOOP;
    }
}
