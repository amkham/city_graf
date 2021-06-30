package com.limmath;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private BufferedImage bufferedImage;


    private void setImage(BufferedImage bufferedImage)
    {
        this.bufferedImage = bufferedImage;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(bufferedImage, 0, 0 , null);
    }

    public static void loadAndShowImage(ImagePanel imagePanel)
    {
        try
        {
            BufferedImage image = ImageIO.read(new File("./files/output/ex4.1.png"));
            BufferedImage resized = resize(image, 400, 400 );
            imagePanel.setImage(resized);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }


    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }


}
