import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class Weather_update extends Events_on_Fields  {
    private String main,description;
    public String getMain() {
        return main;
    }
    public void func() throws IOException, InterruptedException {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("H");
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM_dd_yyyy");
        LocalDate now = LocalDate.now();
        String date = dtf.format(now);
        String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        int findTime = 0;
        int count3=0;
        String dec;
        if (localTime.getHour() >= 12) {
            dec = "p.m";
        } else {
            dec = "a.m";
        }
        String time = dt.format(localTime);
        int time1 = Integer.parseInt(time);
        String lat = getLat().toString();
        String lon = getLon().toString();
        String email_ = getEmail();
        String jsonData;
        String[] names = new String[49];
       // String main, description;
        String temp;
        double temp1;
        int count = 0;
        int count_ = 0;
        int i = 0;
        String filename = date + "_latitude" + lat + "_longitude" + lon + ".txt";
        FileWriter myWriter = new FileWriter(filename);
        try {
            Map<String, String> weatherParams = new HashMap<>();
            weatherParams.put("appid", "efa01bb6297f16472bc40cd76ef1ea58");
            weatherParams.put("lat", lat);
            String latitude = weatherParams.get("lat");
            weatherParams.put("lon", lon);
            String longitude = weatherParams.get("lon");
            weatherParams.put("exclude", "current,minutely");
            String url = "https://api.openweathermap.org/data/2.5/onecall";
            String queryParams = String.join("&", weatherParams.keySet().stream().map(key -> key + "=" + weatherParams.get(key)).toArray(String[]::new));
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "?" + queryParams)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonData = response.body();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonData);
            JSONArray array = (JSONArray) obj.get("hourly");
            for (i = 0; i < 48; i++) {
                JSONObject firstHourlyData = (JSONObject) array.get(i);
                JSONArray weather = (JSONArray) firstHourlyData.get("weather");
                temp = (firstHourlyData.get("temp")).toString();
                temp1 = Double.parseDouble(temp);
                temp1 = temp1 - 273.15;
                BigDecimal bd = new BigDecimal(temp1).setScale(2, RoundingMode.HALF_UP);
                double newNum = bd.doubleValue();
                JSONObject weatherObj = (JSONObject) weather.get(0);
                int id = ((Long) weatherObj.get("id")).intValue();
                main = (weatherObj.get("main")).toString();
                description = (weatherObj.get("description")).toString();
                names[i] = (main + " (" + description + ") " + newNum + " \u2103");
                if (dec.equals("a.m")) {
                    if (count == 0) {
                        myWriter.write("On " + day[dayOfWeek] + "\n");
                        count++;
                    }
                    System.out.println("The time" + time1);
                    if (time1 >= 12 && time1 < 24) {
                        findTime = (time1 - 12) % 12;
                        if (findTime == 0) {
                            myWriter.write(names[i] + " at: " + 12 + " p.m.\n");
                        } else {
                            myWriter.write(names[i] + " at: " + findTime + " p.m.\n");
                        }
                    } else if (time1 >= 24 && time1 < 36) {
                        findTime = time1 % 12;
                        if (findTime == 0) {
                            if (count_ == 0) {
                                myWriter.write("On " + day[dayOfWeek + 1] + "\n");
                                count_++;
                            }
                            myWriter.write(names[i] + " at: " + 12 + " a.m.\n");
                        } else {
                            myWriter.write(names[i] + " at: " + findTime + " a.m.\n");
                        }
                    }

                    else if (time1 > 35 && time1 < 48) {
                        findTime = (time1 - 12) % 12;
                        if (findTime == 0) {
                            myWriter.write(names[i] + " at: " + 12 + " p.m.\n");
                        } else {
                            myWriter.write(names[i] + " at: " + findTime + " p.m.\n");
                        }
                    }
                    else {
                        findTime = time1 % 12;
                        if (findTime == 0) {
                            if (count3== 0) {
                                myWriter.write("On " + day[dayOfWeek + 2] + "\n");
                                count3++;
                            }
                            myWriter.write(names[i] + " at: " + 12 + " a.m.\n");
                        } else {
                            myWriter.write(names[i] + " at: " + findTime + " a.m.\n");
                        }
                    }
                }
                if (dec.equals("p.m")) {
                    System.out.println(time1);
                    System.out.println("hello enter");
                    if (count == 0) {
                        myWriter.write("         On " + day[dayOfWeek] + "\n");
                        count++;
                    }
                    if (time1 >= 12 && time1 < 24) {
                        System.out.println("hello love");
                        findTime = (time1 - 12) % 12;
                        if (findTime == 0) {
                            System.out.println("hello mana");
                            if (count_ == 0) {
                                myWriter.write("     On " + day[dayOfWeek + 1] + "\n");
                                count_++;
                            }
                            myWriter.write(names[i] + " at: " + 12 + " a.m.\n");

                        } else {
                            myWriter.write(names[i] + " at: " + findTime + " p.m.\n");
                        }}
                    else if(time1>59)
                    {
                        findTime = (time1 - 12) % 12;
                        if(findTime==0)
                        {
                        myWriter.write(names[i] + " at: " + 12 + " p.m.\n");}
                            else

                        {   myWriter.write(names[i] + " at: " + findTime + " p.m.\n");}
                    }
                    else if (time1 >= 48 && time1 < 59) {
                        findTime = (time1 - 12) % 12;
                        if (findTime == 0) {
                            if(count3==0)
                            {
                                myWriter.write("       On " + day[dayOfWeek + 2] + "\n");
                                count3++;
                            }
                            myWriter.write(names[i] + " at: " + 12 + " a.m.\n");
                        } else {
                            myWriter.write(names[i] + " at: " + findTime + " a.m.\n");
                        }

                    } else if (time1 > 35 && time1 < 48) {
                        findTime = (time1 - 12) % 12;
                        if (findTime == 0) {
                            myWriter.write(names[i] + " at: " + 12 + " p.m.\n");
                        } else {
                            myWriter.write(names[i] + " at: " + findTime + " p.m.\n");
                        }
                    } else {
                        findTime = time1 % 12;
                        if (findTime == 0) {
                            if (count_ == 0) {
                                myWriter.write("           On " + day[dayOfWeek + 1] + "\n");
                                count_++;
                            }

                            myWriter.write(names[i] + " at: " + 12 + " a.m.\n");
                        } else {
                            myWriter.write(names[i] + " at: " + findTime + " a.m.\n");
                        }
                    }
                }
                time1++;
            }
            myWriter.close();
        } catch (ConnectException | ParseException e) {
            JOptionPane.showMessageDialog(this, "The internet is not available.");
        }
        // File name
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bye");
        }
        String data = "";
        try (
                BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                data = data + line + "\n";
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Hy.");
        }
        Email email=new Email();
        email.function(data,email_);
    }
}


