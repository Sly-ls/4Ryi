package fr.game.main;

import fr.game.constants.panel.PanelEnum;

import javax.swing.*;

public class Main {

    public static void main (String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(AppConstants.GAME_TITLE);
        ApplicationManager gameManager = new ApplicationManager(window, PanelEnum.TITLE_SCREEN);
//FIXME can't start directly on game panel for debug with test MAP
        //FIXME also menu wolrd selection seems to doesn't work before fisrt win
        //to create the tiles for spiderland
//        TileManager.getInstance().cleanTileSet();
//        TileManager.getInstance().cutTileSetToTile();

        //to create the PC and NPC
//        TileManager.getInstance().cutSpriteSheetToSprite();
//        TileManager.getInstance().printEnumFromFile();
    }



}
