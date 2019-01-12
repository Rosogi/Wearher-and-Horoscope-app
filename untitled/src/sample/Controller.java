package sample;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Controller {
    public TextArea HoroscopeTextArea;
    public TextField NumTempTextField;
    public TextField WordTempTextField;
    public Button UpdateAllButton;
    @FXML
    public ToggleGroup RadioButtonGroup;
    public RadioButton Aries;
    public RadioButton Taurus;
    public RadioButton Gemini;
    public RadioButton Cancer;
    public RadioButton Leo;
    public RadioButton Virgo;
    public RadioButton Libra;
    public RadioButton Scorpio;
    public RadioButton Sagittarius;
    public RadioButton Capricorn;
    public RadioButton Aquarius;
    public RadioButton Pisces;



    public void UpdateInfoButton (ActionEvent actionEvent){
    NumTempTextField.setText(WeatherNumGet());
    WordTempTextField.setText(WeatherWordGet());
    HoroscopeTextArea.setText(HoroscopeGet(SelectGet()));
    }

    public String WeatherNumGet(){
        String NumTemp = null;
        try {
            Document WeatherURL =  Jsoup.connect("https://weather.rambler.ru/v-moskve/").get();
            Elements WeatherNum = WeatherURL.getElementsByClass("_1HBR");
            NumTemp = WeatherNum.text();
            return NumTemp;
        }
        catch (IOException e){e.printStackTrace();}
        return NumTemp;
    }

    public String WeatherWordGet(){
        String WordTemp = null;
        try {
            Document WeatherURL =  Jsoup.connect("https://weather.rambler.ru/v-moskve/").get();
            Elements WeatherWord = WeatherURL.getElementsByClass("Hixd");
            WordTemp = WeatherWord.text();
        }
        catch (IOException e){e.printStackTrace();}
        return WordTemp;
    }

    public String HoroscopeGet(String HoroChar) {

        String HoroArr[] = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces"};
        String ShowText = null;
        String FormatString = "";
        try {
            Document HoroscopeURL = Jsoup.connect("https://horoscopes.rambler.ru/" + HoroChar + "/").get();
            Elements HoroCharTeg = HoroscopeURL.getElementsByClass("_1dQ3");
            ShowText = "Горокоп : " + HoroCharTeg.text();
            String[] lines = Split(ShowText, 90, 1000);
            for (int i = 0; i < lines.length;i++){
                FormatString = FormatString + lines[i] + "\n";
            }
            ShowText = FormatString;
            return ShowText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ShowText;
    }
    //Ебаная магия Создатель:  Jose Tepedino
    public static String[] Split(String text, int chunkSize, int maxLength) {
        char[] data = text.toCharArray();
        int len = Math.min(data.length,maxLength);
        String[] result = new String[(len+chunkSize-1)/chunkSize];
        int linha = 0;
        for (int i=0; i < len; i+=chunkSize) {
            result[linha] = new String(data, i, Math.min(chunkSize,len-i));
            linha++;
        }
        return result;
    }

    public String SelectGet() {
        String HoroChar = null;
        if (Aries.isSelected()){
            HoroChar = "aries";
        }
        else if(Taurus.isSelected()){
            HoroChar = "taurus";
        }
        else if(Gemini.isSelected()){
            HoroChar = "gemini";
        }
        else if(Cancer.isSelected()){
            HoroChar = "cancer";
        }
        else if(Leo.isSelected()){
            HoroChar = "leo";
        }
        else if(Virgo.isSelected()){
            HoroChar = "virgo";
        }
        else if(Libra.isSelected()){
            HoroChar = "libra";
        }
        else if(Scorpio.isSelected()){
            HoroChar = "scorpio";
        }
        else if(Sagittarius.isSelected()){
            HoroChar = "sagittarius";
        }
        else if(Capricorn.isSelected()){
            HoroChar = "capricorn";
        }
        else if(Aquarius.isSelected()){
            HoroChar = "aquarius";
        }
        else if(Pisces.isSelected()){
            HoroChar = "pisces";
        }
        return HoroChar;
    }
}
