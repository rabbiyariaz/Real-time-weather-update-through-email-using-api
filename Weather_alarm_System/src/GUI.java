// manipulate and use image in class
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class GUI extends JFrame{
    private TextField field1,field2,field3;
    private JLabel label1,label2,label3;
    private JButton button,back_to_menu,send;
    private Image backgroundImage; // background image
   // constructor
    public void image()
    {
        try {
            backgroundImage = ImageIO.read(new File("img.png"));
        } catch (IOException e) {
            e.printStackTrace();}
        setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                Graphics  gu= backgroundImage.getGraphics();
                gu.setFont(gu.getFont().deriveFont(23f));
                gu.drawString("Latitude", 330, 40);
                gu.drawString("Longitude", 330, 80);
                gu.drawString("Enter email", 330, 120);
                repaint();}
        });
    }
  public GUI()
    {
        ImageIcon img = new ImageIcon("img.png");
        setIconImage(img.getImage());
        setTitle("Weather_alarm_System");
        JPanel panel_center=new JPanel(new BorderLayout());
        JPanel panel=new JPanel(new GridLayout(6,1));
        Color customColor = new Color(0,30,102);
        panel_center.add(panel, BorderLayout.CENTER);
        // label1
        label1=new JLabel();label1.setText("Latitude");
        // fields
        field1=new TextField();field2=new TextField();
        // label first row,second column
        label2=new JLabel();label2.setText("Longitude");
        label2.setPreferredSize(new Dimension(100,30));
        label3=new JLabel();label3.setText("Enter your E-mail");
        label3.setPreferredSize(new Dimension(200,30));
        Font font=new Font("Arial", Font.PLAIN, 18);
        field3=new TextField();
        field2.setPreferredSize(new Dimension(100,30));
        button=new JButton("Find Long/Lat");
        button.setPreferredSize(new Dimension(200,30));
        back_to_menu=new JButton("Back to main");
        back_to_menu.setPreferredSize(new Dimension(200,40));
        send=new JButton("Send email");
        send.setPreferredSize(new Dimension(200,30));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        button.setFont(font);
        button.setBackground(customColor);
        button.setForeground(Color.WHITE);
        button.setFocusable(true);
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        send.setFont(font);
        Color customCol = new Color( 51, 102, 153);
        Color customColo = new Color(0, 47, 108);
        back_to_menu.setBackground(customColo);
        send.setBackground(customCol);send.setForeground(Color.WHITE);send.setFocusable(true);
        send.setBorder(new EmptyBorder(0, 0, 0, 0));
        back_to_menu.setFont(font);
        back_to_menu.setForeground(Color.WHITE);
        back_to_menu.setFocusable(true);
        back_to_menu.setBorder(new EmptyBorder(0, 0, 0, 0));
        field1.setFont(font);field2.setFont(font);field3.setFont(font);
        field1.setForeground(customCol);field2.setForeground(customCol);field3.setForeground(customCol);
        panel.add(field1,c);panel.add(field2,c);panel.add(field3,c);
        panel.add(send,c);panel.add(button,c);panel.add(back_to_menu,c);
        image();
        panel.setOpaque(false);
        // set fields
        setField1(field1);setField2(field2);setField3(field3);
        // Set panel as content pane and add to JFrame
        add(panel_center);
        repaint();
        // set background image
    }
    public void setField1(TextField field1) {
        this.field1 = field1;}
    public void setField2(TextField field2) {
        this.field2 = field2;
    }
    public TextField getField1() {
        return field1;}
    public TextField getField2() {
        return field2;
    }
    public JLabel getLabel1() {
        return label1;
    }
    public JLabel getLabel2() {
        return label2;
    }
    public JButton getButton() {
        return button;
    }
    public void setField3(TextField field3) {
        this.field3 = field3;
    }
    public TextField getField3() {
        return field3;
    }
    public JButton getSend() {
        return send;}
    public JButton getBack_to_menu() {
        return back_to_menu;
    }}
