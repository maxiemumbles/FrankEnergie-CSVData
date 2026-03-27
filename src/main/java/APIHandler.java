import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class APIHandler {
    public GraphQLResponse readJsonIntoObject(String jsonResponse) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        // Return the class with all values!
        return objectMapper.readValue(jsonResponse, GraphQLResponse.class);
    }

    public HttpResponse<String> getAPIResponse() {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

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

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Main.URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                    .timeout(Duration.ofSeconds(30))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            System.err.println("Error making request: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}