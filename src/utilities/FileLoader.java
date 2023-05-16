package utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class FileLoader {
    private static final String RESOURCE_PATH = System.getProperty("user.dir") + "\\resources\\";
    public static String localSession;

    private FileLoader() {} // Necessary

    /**
     * Read a file and return the content
     *
     * @param filepath : the path to the file to read
     * @return the content of the file
     * @throws IOException when cannot read line
     */
    public static List<String> readListFromFile(String filepath) throws IOException {
        InputStream inputStream = new FileInputStream(RESOURCE_PATH + filepath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return readBufferReader(bufferedReader);
    }

    /**
     * Read a url and return the content
     *
     * @param urlStr : the url to read
     * @return the content of the url
     * @throws IOException when cannot read the url or open the connexion
     */
    public static List<String> readListFromURL(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Cookie", "session=" + localSession);
        InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return readBufferReader(bufferedReader);
    }

    /**
     * Read a buffer reader and return it's content
     *
     * @param bufferedReader : the bufferReader to read
     * @return the content of the file
     * @throws IOException when cannot read line
     */
    public static List<String> readBufferReader(BufferedReader bufferedReader) throws IOException {
        List<String> stringList = new LinkedList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringList.add(line);
        }
        bufferedReader.close();
        return stringList;
    }
}
