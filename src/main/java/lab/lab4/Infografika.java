package lab.lab4;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Infografika {

    String Title = "";
    String Web = "";
    String ImgWeb = "";
    String IconWeb = "";
    String Size = "";

    public Infografika(String text){

        Pattern patTitle = Pattern.compile("<title><!\\[CDATA\\[(.*)\\]\\]");
        Matcher matchTitle = patTitle.matcher(text);
        if(matchTitle.find()) this.Title = matchTitle.group(1);
        else this.Title = "Brak";

        Pattern patWeb = Pattern.compile("<link>(.*)</link>");
        Matcher matchWeb = patWeb.matcher(text);
        if(matchWeb.find()) this.Web = matchWeb.group(1);
        else this.Web = "Brak";

        Pattern patImgWeb = Pattern.compile("<media:content url=\"(.*)\" type");
        Matcher matchImgWeb = patImgWeb.matcher(text);
        if(matchImgWeb.find()) this.ImgWeb = matchImgWeb.group(1);
        else this.ImgWeb = "Brak";

        Pattern patIconWeb = Pattern.compile("<media:thumbnail url=\"(.*)\" />");
        Matcher matchIconWeb = patIconWeb.matcher(text);
        if(matchIconWeb.find()) this.IconWeb = matchIconWeb.group(1);
        else this.IconWeb = "Brak";

        Pattern patSizeX = Pattern.compile("width=\"(.*)\" height");
        Matcher matchSizeX = patSizeX.matcher(text);
        if(matchSizeX.find()) this.Size = matchSizeX.group(1);

        Pattern patSizeY = Pattern.compile("height=\"(.*)\">");
        Matcher matchSizeY = patSizeY.matcher(text);
        if(matchSizeY.find()) this.Size += "x"+matchSizeY.group(1);
        else this.Size = "Brak";
    }

}
