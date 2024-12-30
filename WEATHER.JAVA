import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {

    // Replace with your API key from OpenWeatherMap
    private static final String API_KEY = "your_api_key";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public static void main(String[] args) {
        String city = "London"; // You can change this to any city
        fetchWeatherData(city);
    }

    public static void fetchWeatherData(String city) {
        try {
            // Construct the API URL
            String urlString = BASE_URL + city + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);

            // Open a connection to the URL
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

            // Parse and display the weather data
            parseAndDisplayWeatherData(response.toString());

        } catch (Exception e) {
            System.out.println("Error fetching weather data: " + e.getMessage());
        }
    }

    public static void parseAndDisplayWeatherData(String responseBody) {
        try {
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(responseBody);
            String cityName = jsonObject.getString("name");
            JSONObject main = jsonObject.getJSONObject("main");
            double temperature = main.getDouble("temp");
            double feelsLike = main.getDouble("feels_like");
            int humidity = main.getInt("humidity");
            
            JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
            String description = weather.getString("description");

            // Display the weather data
            System.out.println("Weather Data for " + cityName + ":");
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Feels Like: " + feelsLike + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Description: " + description);

        } catch (Exception e) {
            System.out.println("Error parsing weather data: " + e.getMessage());
        }
    }
}