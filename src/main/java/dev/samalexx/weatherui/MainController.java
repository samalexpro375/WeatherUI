//https://api.openweathermap.org/data/2.5/weather?q=+"cityF.getText()+"&appid=b74209d20a6a71b973b96158eba1102d&units=metric
package dev.samalexx.weatherui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;


public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text WindySpeedT;

    @FXML
    private TextField cityF;

    @FXML
    private Button getCity;

    @FXML
    private Text humidityT;

    @FXML
    private Text pressureT;

    @FXML
    private Text tempT;

    @FXML
    void initialize() {
        getCity.setOnAction(event ->
        {
            String getUserCity = cityF.getText().trim();
            if(!getUserCity.equals("")) {
                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=b74209d20a6a71b973b96158eba1102d&units=metric");
                System.out.println(output);

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    double pres = obj.getJSONObject("main").getDouble("pressure") * 0.750062;
                    double tempInfo = obj.getJSONObject("main").getDouble("temp");
                    tempInfo = Math.round(tempInfo);
                    pres = Math.round(pres);
                    tempT.setText("Температура: " + tempInfo + "°");
                    pressureT.setText("Давление: " + pres + "мм рт. ст.");
                    humidityT.setText("Влажность: " + obj.getJSONObject("main").getDouble("humidity") + "%");
                    WindySpeedT.setText("Скорость ветра: " + obj.getJSONObject("wind").getDouble("speed") + "м/c");

                }
            }
        });
    }

    private static String getUrlContent(String urlAdress)
    {
        StringBuffer content = new StringBuffer();

        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }

        catch (Exception e)
        {
            System.out.println("оШиБка: " + e.getMessage());
        }
        return content.toString();
    }

}
