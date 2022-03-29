package fr.game.main.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageUtils {


        public static BufferedImage createFlipped(BufferedImage image, boolean vertical)
        {
                AffineTransform at = new AffineTransform();
                if(vertical) {
                        at.concatenate(AffineTransform.getScaleInstance(1, -1));
                        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
                }else{
                        at.concatenate(AffineTransform.getScaleInstance(-1, 1));
                        at.concatenate(AffineTransform.getTranslateInstance(-image.getWidth(), 0));
                }
                return createTransformed(image, at);
        }

        public static BufferedImage createRotated(BufferedImage image, double facingAngle)
        {
                AffineTransform at = AffineTransform.getRotateInstance(
                        Math.toRadians(facingAngle), image.getWidth()/2, image.getHeight()/2.0);
                return createTransformed(image, at);
        }
        private static BufferedImage createTransformed(
                BufferedImage image, AffineTransform at)
        {
                BufferedImage newImage = new BufferedImage(
                        image.getWidth(), image.getHeight(),
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = newImage.createGraphics();
                g.transform(at);
                g.drawImage(image, 0, 0, null);
                g.dispose();
                return newImage;
        }

        }
