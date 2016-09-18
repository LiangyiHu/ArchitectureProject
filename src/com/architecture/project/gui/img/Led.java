package com.architecture.project.gui.img;

import com.architecture.project.processer.registers.AbstractRegisters;
import com.architecture.project.processer.registers.Register;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author taoranxue on 9/17/16 8:04 PM.
 */
public class Led {
    private static final int LED_SIZE = 10;
    private static final int LED_BIT_NUM = 16;
    private static final String LED_OFF_URL = "/com/architecture/project/gui/img/led_off.png";
    private static final String LED_ON_URL = "/com/architecture/project/gui/img/led_on.png";

    public static Image parseImage(Register register) {
        String str = Integer.toBinaryString(register.getData());
        return parseImage(str);
    }

    public static Image parseImage(int binaryNumber) {
        String str = Integer.toBinaryString(binaryNumber);
        return parseImage(str);
    }

    public static Image parseImage(String binaryStr) {
        Image LedOnImg = getImageByURL(LED_ON_URL);
        Image LedOffImg = getImageByURL(LED_OFF_URL);

        int width = LED_BIT_NUM * LED_SIZE;
        Image combined = new BufferedImage(width, LED_SIZE, BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = combined.getGraphics();
        int i;
        int remainLength = LED_BIT_NUM - binaryStr.length();
        for (i = 0; i < remainLength; ++i) {
            graphics.drawImage(LedOffImg, i * LED_SIZE, 0, null);
        }
        for (; i < LED_BIT_NUM; ++i) {
            if (binaryStr.charAt(i - remainLength) == '1') {
                graphics.drawImage(LedOnImg, i * LED_SIZE, 0, null);
            } else if (binaryStr.charAt(i - remainLength) == '0') {
                graphics.drawImage(LedOffImg, i * LED_SIZE, 0, null);
            }
        }
        return combined;
    }

    private static Image getImageByURL(String str, int w, int h) {
        Image resizeImage = null;
        try {
            Image bImage = ImageIO.
                    read(Led.class.getResource(str));

            resizeImage = bImage.getScaledInstance(w, h, 4);
            if (resizeImage == null) {
                throw new Exception("Resize Error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resizeImage;
    }

    private static Image getImageByURL(String str) {
        return getImageByURL(str, LED_SIZE, LED_SIZE);
    }
}
