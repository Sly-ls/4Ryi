package fr.game.mechanics.core;

import fr.game.main.AppVariables;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScaledImage {

    public BufferedImage bufferedImage[];

    public ScaledImage(String path) {
        if(path != null && !path.equalsIgnoreCase("")) {
            try {
                this.bufferedImage = new BufferedImage[1];
                BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream(path));
                scaleImage( originalImage, 0, 0, 0, AppVariables.tileSize, AppVariables.tileSize);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
            }
        }
    }
    public ScaledImage(String[] path) {
        if(path != null && path.length>0){
            int index = 0;
            this.bufferedImage = new BufferedImage[path.length];
            while (index < path.length){
                try {
                    BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream(path[index]));
                    scaleImage( originalImage, index, 0, 0, AppVariables.tileSize, AppVariables.tileSize);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index++;
            }
        }
    }

    public ScaledImage(String[] path, int width, int height) {
        if(path != null && path.length>0){
            int index = 0;
            this.bufferedImage = new BufferedImage[path.length];
            while (index < path.length){
                try {
                    BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream(path[index]));
                    scaleImage( originalImage, index, 0, 0, width, height);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index++;
            }
        }
    }
    public ScaledImage(String[] path, int tileX, int tileY, int width, int height) {
        if(path != null && path.length>0){
            int index = 0;
            this.bufferedImage = new BufferedImage[path.length];
            while (index < path.length){
                try {
                    BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream(path[index]));
                    scaleImage( originalImage, index, tileX, tileY, width, height);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index++;
            }
        }
    }
    public ScaledImage(BufferedImage image) {
        if(image != null){
            int index = 0;
            this.bufferedImage = new BufferedImage[1];
            scaleImage( image, 0, 0, 0, AppVariables.tileSize, AppVariables.tileSize);
        }
    }

    private void scaleImage(BufferedImage originalImage, int index, int tileX, int tileY, int width, int height) {
        bufferedImage[index] = new BufferedImage(width,  height, originalImage.getType());
        Graphics2D graphics2D = bufferedImage[index].createGraphics();
        graphics2D.drawImage(originalImage, tileX, tileY, width,  height, null);
        graphics2D.dispose();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage[0];
    }

    public BufferedImage getBufferedImage(int index) {
        return bufferedImage[index];
    }
    public void setBufferedImage(BufferedImage bufferedImage, int index) {
        this.bufferedImage[index] = bufferedImage;
    }
}
