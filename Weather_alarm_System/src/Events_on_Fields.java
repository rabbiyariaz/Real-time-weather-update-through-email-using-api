import org.w3c.dom.ranges.RangeException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import static java.lang.Double.parseDouble;
public class Events_on_Fields extends GUI implements ActionListener, KeyListener {
    // cannot assign again value to final variable
    // you can assign value only declaration time
    int var;
    private Double lat;private Double lon;private String email;
    Double latitude;
    Double longitude;
    private int variable=0;
    public Events_on_Fields() {
        getField1().addActionListener(this);
        getField2().addActionListener(this);
        getField3().addActionListener(this);
        getField3().addKeyListener(this);
        getField1().addKeyListener(this);
        getField2().addKeyListener(this);
        getButton().addActionListener(this);
        getBack_to_menu().addActionListener(this);
        getSend().addActionListener(this);}
    public Double validateTextField(String field, String text) {
        double number;
        try {
            number = parseDouble(field);
            if (text.equals("Latitude")) {
                              if (number > 90 || number < -90) {
                    throw new RangeException((short) 1, text + " should be between -90 and 90.");
                }
            } else if (text.equals("Longitude")) {
                if (number > 180 || number < -180) {
                    throw new RangeException((short) 1, text + " should be between -180 and 180.");
                }
            } else {
                return null; // invalid input
            }
            return number; // valid input
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid " + text, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            if(text.equals("Latitude"))
            {
                getField1().setText("");
            }
            else if(text.equals("Longitude"))
            {
                 getField2().setText("");
            }
            return null; // invalid input
        } catch (RangeException e) {
            String errorMessage = (text.equals("Longitude")) ? " b/w [-180,180]" : " b/w [-90,90]";
            JOptionPane.showMessageDialog(this, "Please enter " + text + errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
            if(text.equals("Latitude"))
            {
                getField1().setText("");
            }
            else if(text.equals("Longitude"))
            {
                 getField2().setText("");
            }
            return null; // invalid input
             }}
    public boolean validateEmail(String email_) {

        try {
            String last_ten_characters = email_.substring(email_.length() - 10);
            String last_seecs_char=email_.substring(email_.length()-13);
            System.out.println(last_seecs_char);
            String username = email_.substring(0, email_.length() - 10);
            if (username.length() == 0) {
                throw new IllegalArgumentException("Email address cannot be empty.");
            }
            if (username.contains(" ")) {
                throw new IllegalArgumentException("Email address cannot contain spaces.");
            }
            if (username.contains("!") || username.contains("#") || username.contains("$") || username.contains("%")
                    || username.contains("^") || username.contains("&") || username.contains("*") || username.contains("(")
                    || username.contains(")") || username.contains("-") || username.contains("+") || username.contains("=")
                    || username.contains("/") || username.contains("\\") || username.contains("|") || username.contains("{")
                    || username.contains("}") || username.contains("[") || username.contains("]") || username.contains(":")
                    || username.contains(";") || username.contains("\"") || username.contains("'") || username.contains("<")
                    || username.contains(">") || username.contains(",")  || username.contains("?")
                    || username.contains("`") || username.contains("~")||username.contains("@")) {
                throw new IllegalArgumentException("Email address contains prohibited characters.");}
            if (!last_ten_characters.equals("@gmail.com")&&!last_seecs_char.equals("@seecs.edu.pk")) {

                throw new IllegalArgumentException(last_ten_characters  + " should be equal to @gmail.com.");}
            else {
                variable=0;
                return true;
            }
        } catch (Exception e) {
            variable += 1;
             var = 6 - variable;
             if (variable == 1)
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid gmail", JOptionPane.ERROR_MESSAGE);
                getField3().setText("");
                return false;
            }
            if (variable == 2) {
                 JOptionPane.showMessageDialog(this, "Please make sure to enter a valid gmail address within five attempts.", "Invalid gmail", JOptionPane.ERROR_MESSAGE);
                getField3().setText("");
                return false;}
            if (variable > 2 && variable <= 6) {

                if (var != 0) {
                    JOptionPane.showMessageDialog(this, "You have only " + var + " attempts left, otherwise the system will sleep for half a minute.", "Invalid gmail", JOptionPane.ERROR_MESSAGE);
                    getField3().setText("");
                    return false;}
                getField3().setText("");
                getField1().setEnabled(false);
                getField2().setEnabled(false);
                getField3().setEnabled(false);
                try {
                    Thread.sleep(30000); // sleep for 30 seconds
                    variable = 0;
                } catch (InterruptedException ex) {
                    e.fillInStackTrace();
                }
                // enable all text fields
                getField1().setEnabled(true);
                getField2().setEnabled(true);
                getField3().setEnabled(true);
            }
        }
        if (var==0)
        {
            return false;
        }
        return true;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
         // text1
            if (event.getSource()==getBack_to_menu())
        {
            MainPage page=new MainPage();
           try{ page.autofill(lat,lon);}
           catch (Exception e)
           {
               System.out.println("The field contains wrong data");
           }
            page.setVisible(true);
            setVisible(false);
            dispose();
        }
        String text1 = getField1().getText();
        String text2 = getField2().getText();
        String text3 = getLabel1().getText();
        String text4 = getLabel2().getText();
        if (event.getSource() == getSend()) {

            if (getField3().getText().equals("") || getField2().getText().equals("") || getField1().getText().equals("")) {
                JOptionPane.showMessageDialog(this,"Please fill all the fields");
            } else {
                 latitude = validateTextField(getField1().getText(), "Latitude");
                 longitude = validateTextField(getField2().getText(), "Longitude");
                boolean validEmail = validateEmail(getField3().getText());
                if (latitude != null &&longitude != null &&validEmail) {
                         lat=latitude;
                         lon=longitude;
                         email=getField3().getText();
                    Weather_update update=new Weather_update();
                    try {
                        update.setLat(Double.parseDouble(String.valueOf(lat))); // Set the lat value
                        update.setLon(Double.parseDouble(String.valueOf(lon)));
                        update.setEmail(email);
                        update.func();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }}}}
            if (event.getSource()==getButton())
            {
                Desktop d = Desktop.getDesktop();
                try {
                    d.browse(new URI("https://www.latlong.net/"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                JOptionPane.showMessageDialog(this,"The internet is not available");
                } }

    }
    public Double getLat(){
        return lat;}
    public Double getLon() {
        return lon;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }
    public void setLon(Double lon) {this.lon = lon;}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            e.consume();}
     }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void againAutofill(String x,String y)
    {
        getField1().setText(x);
        getField2().setText(y);
    }
}

