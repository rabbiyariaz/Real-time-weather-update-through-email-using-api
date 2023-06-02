import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class FileForImage extends JComponent{
    public static void image()
    {
    Image backgroundImage = null;
        JFrame frame = new JFrame();
        try {
            backgroundImage = ImageIO.read(new File("img.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image finalBackgroundImage = backgroundImage;
        frame.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });}
}
