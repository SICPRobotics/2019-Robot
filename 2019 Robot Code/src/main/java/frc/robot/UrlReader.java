package frc.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.*;

public class UrlReader
{
    private JSONObject data;
    private BufferedReader reader;

    public UrlReader() throws Exception
    {
        String urlString = "http://10.58.22.48:25565/data";

        URL url = new URL(urlString);

        reader = new BufferedReader(new InputStreamReader(url.openStream()));

       
        //reader.close();
    }

    public JSONObject getCurrentData() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length() > 0 && line.charAt(0) == '{') {
                data = new JSONObject(line.substring(0, line.length() - 32));
                break;
                //System.out.println(data.toString());
            }
        }
        return data;
    }
}