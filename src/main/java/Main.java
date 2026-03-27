import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static String url = "https://www.frankenergie.nl/graphql";
    static String CSV_FILE_NAME = "HourlyData.csv";

    static void main() {
        CSVWriter csvWriter = new CSVWriter();
        HttpResponse<String> rawResponse = getAPIResponse();

        GraphQLResponse response;
        try {
            assert rawResponse != null;
            response = readJsonIntoObject(rawResponse.body());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ArrayList<String[]> pricePoints = new ArrayList<>();
        response.getData().getMarketPrices().getElectricityPrices().forEach(pricePoint ->
                pricePoints.add(new String[] {pricePoint.getFrom(), String.valueOf(pricePoint.getAllInPrice()), pricePoint.getPerUnit()}));

        try {
            csvWriter.writeToCSV(pricePoints);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Access properties directly
        assert response != null;
        response.getData().getMarketPrices().getElectricityPrices().forEach(price -> {
            System.out.println("Price: " + price.getMarketPrice());
        });
    }

    public static GraphQLResponse readJsonIntoObject(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        // Return the class with all values!
        return objectMapper.readValue(jsonResponse, GraphQLResponse.class);
    }

    public static HttpResponse<String> getAPIResponse() {
        try {
            // Create HttpClient
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            // Create the GraphQL query body
            String jsonBody = "{"
                    + "\"query\": \"query MarketPrices($date: String!, $resolution: PriceResolution!) {\\n"
                    + "  marketPrices(date: $date, resolution: $resolution) {\\n"
                    + "    averageElectricityPrices {\\n"
                    + "      averageMarketPrice\\n"
                    + "      averageMarketPricePlus\\n"
                    + "      averageAllInPrice\\n"
                    + "      perUnit\\n"
                    + "      isWeighted\\n"
                    + "    }\\n"
                    + "    electricityPrices {\\n"
                    + "      from\\n"
                    + "      till\\n"
                    + "      marketPrice\\n"
                    + "      marketPricePlus\\n"
                    + "      allInPrice\\n"
                    + "      perUnit\\n"
                    + "      marketPricePlusComponents {\\n"
                    + "        name\\n"
                    + "        value\\n"
                    + "      }\\n"
                    + "      allInPriceComponents {\\n"
                    + "        name\\n"
                    + "        value\\n"
                    + "      }\\n"
                    + "    }\\n"
                    + "    gasPrices {\\n"
                    + "      from\\n"
                    + "      till\\n"
                    + "      marketPrice\\n"
                    + "      marketPricePlus\\n"
                    + "      allInPrice\\n"
                    + "      perUnit\\n"
                    + "      marketPricePlusComponents {\\n"
                    + "        name\\n"
                    + "        value\\n"
                    + "      }\\n"
                    + "      allInPriceComponents {\\n"
                    + "        name\\n"
                    + "        value\\n"
                    + "      }\\n"
                    + "    }\\n"
                    + "  }\\n"
                    + "}\\n\","
                    + "\"variables\": {"
                    + "\"date\": \"2026-03-25\","
                    + "\"resolution\": \"PT60M\""
                    + "},"
                    + "\"operationName\": \"MarketPrices\""
                    + "}";

            // Create HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                    .timeout(Duration.ofSeconds(30))
                    .build();

            // Send the request
            return client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            System.err.println("Error making request: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}