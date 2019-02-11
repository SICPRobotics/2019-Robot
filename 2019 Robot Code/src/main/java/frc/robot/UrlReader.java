package frc.robot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlReader {
    public UrlReader() throws Exception {
        String urlString = "http://10.58.22.48:25565/data";

        URL url = new URL(urlString);

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;
        while (true) {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        //reader.close();
    }
}