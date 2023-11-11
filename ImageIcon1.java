package DSA;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageIcon1 {

    private Image image;

    public ImageIcon1(String filePath) {
        try {
            File file = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            this.image = bufferedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // adjust width and height as per requirement
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }
}

