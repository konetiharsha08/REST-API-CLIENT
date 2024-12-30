import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class HttpJsonHandler {

    public static void main(String[] args) {
        // Example API URL (replace with any public API URL)
        String apiUrl = "https://jsonplaceholder.typicode.com/posts/1";
        fetchAndParseJson(apiUrl);
    }

    public static void fetchAndParseJson(String apiUrl) {
        try {
            // Open a connection to the URL
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse and display the JSON data
            parseJsonResponse(response.toString());

        } catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }

    public static void parseJsonResponse(String responseBody) {
        try {
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(responseBody);

            // Display the JSON data
            System.out.println("Parsed JSON Data:");
            for (String key : jsonObject.keySet()) {
                System.out.println(key + ": " + jsonObject.get(key));
            }

        } catch (Exception e) {
            System.out.println("Error parsing JSON data: " + e.getMessage());
        }
    }
}