import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
public class MainPage extends Component implements ActionListener {
   private  String lat_="",lon_="";
    JButton nextPage,quit;
    JFrame frame=new JFrame();
    private Image backgroundImage;
    public void image()
    {
        try {
            backgroundImage = ImageIO.read(new File("cold-quotes-1575930075.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });
    }
    public MainPage() {
        // icon image
        ImageIcon img = new ImageIcon("cold-quotes-1575930075.jpg");
        frame.setIconImage(img.getImage());
        // panel
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(200,80));
        JPanel panel2=new JPanel();
        panel2.setLayout(new GridLayout(2,1));
        // buttons
        nextPage=new JButton("Enter");
        nextPage.setPreferredSize(new Dimension(100, 40));
        nextPage.addActionListener(this);
        panel2.add(nextPage);
        // quit button
        quit=new JButton("Quit");
        quit.setPreferredSize(new Dimension(200, 40));
        quit.addActionListener(this);
        panel2.add(quit);
        // frame
        frame.setTitle("Weather alert System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        image();
        panel.add(panel2,BorderLayout.WEST);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==quit)
        {
            int input = JOptionPane.showConfirmDialog(this,
                    "Are you sure to quit the program?", "Ask",
                    JOptionPane.YES_NO_OPTION);
            if(input==JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
        if(e.getSource()==nextPage)
        {
            // set values of the fields in the formData object
            Events_on_Fields gui=new Events_on_Fields();
            gui.againAutofill(lat_,lon_);
            gui.setSize(1000,700);
             // initialize UI components and set their values to formData
            gui.setLocationRelativeTo(null);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gui.setVisible(true);
            frame.dispose();
        }}
    public void autofill(double lat,double lon)
    {
        try {
            lat_=String.valueOf(lat);
            lon_=String.valueOf(lon);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this,"The latitude/longitude contains invalid value");
        }
        }



}
