/*import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Time extends Weather_update {
    public String dataReturn(int dayOfWeek, String filename, String names,int count_,int count,int time1,FileWriter myWriter,String email_){
        try {

            // File name
            if (dec.equals("a.m")) {
                    if (count == 0) {
                        myWriter.write("On " + day[dayOfWeek] + "\n");
                        count++;
                    }
                    if (time1 >= 12 && time1 < 24) {
                        findTime = (time1 - 12) % 12;
                        if (findTime == 0) {
                            myWriter.write(names + " at: " + 12 + " p.m.\n");
                        } else {
                            myWriter.write(names+ " at: " + findTime + " p.m.\n");
                        }
                    } else {
                        findTime = time1 % 12;
                        if (findTime == 0) {
                            if (count_ == 0) {
                                System.out.println("I am inside count"+count);
                                myWriter.write("On " + day[dayOfWeek + 1] + "\n");
                                count_++;
                            }
                            myWriter.write(names + " at: " + 12 + " a.m.\n");
                        } else {
                            myWriter.write(names + " at: " + findTime + " a.m.\n");
                        }
                    }
                }

                if (dec.equals("p.m")) {
                    System.out.println(time1);
                    if (count == 0) {
                        System.out.println("hello enter");
                        myWriter.append("On ").append(day[dayOfWeek]).append("\n");
                    }
                    if (time1 >= 12 && time1 < 24) {
                       // System.out.println("hello love");
                        findTime = (time1 - 12) % 12;
                        if (findTime == 0) {
                            count_++;
                            System.out.println(" first the count is"+count_);
                            //System.out.println("hello mana");
                            if (count_ == 0){
                                System.out.println("Hello enter--------");
                                myWriter.append("On ").append(day[dayOfWeek + 1]).append("\n");
                            }

                            myWriter.append(names).append(" at: ").append(String.valueOf(12)).append(" a.m.\n");
                        } else {
                             myWriter.append(names).append(" at: ").append(String.valueOf(findTime)).append(" p.m.\n");
                        }
                    } else if (time1 >= 48 && time1 < 59) {
                        myWriter.append(names).append(" at: ").append(String.valueOf(findTime)).append(" p.m.\n");

                    } else if (time1 > 35 && time1 < 48) {
                        findTime = (time1 - 12) % 12;
                        if (findTime == 0) {
                            myWriter.append(names).append(" at: ").append(String.valueOf(12)).append(" p.m.\n");
                        } else {
                            myWriter.append(names).append(" at: ").append(String.valueOf(findTime)).append(" p.m.\n");
                        }
                    } else {
                        findTime = time1 % 12;
                        if (findTime == 0) {
                            count_++;
                            if (count_ == 0) {
                                myWriter.append("On ").append(day[dayOfWeek + 1]).append("\n");}
                            myWriter.append(names).append(" at: ").append(String.valueOf(12)).append(" a.m.\n");
                        } else {
                            myWriter.append(names).append(" at: ").append(String.valueOf(findTime)).append(" a.m.\n");
                        }
                    }
                }

            myWriter.close();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this,"Bye");
        }
    String data = "";
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            data = data + line + "\n";
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Hy.");
    }
       return data;
      }

}

*/
