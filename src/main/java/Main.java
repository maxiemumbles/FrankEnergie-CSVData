import java.io.IOException;
import java.net.http.*;
import java.util.ArrayList;

public class Main {
    static String URL = "https://www.frankenergie.nl/graphql";
    static String CSV_FILE_NAME = "HourlyData.csv";

    static void main() {
        CSVWriter csvWriter = new CSVWriter();
        APIHandler apiHandler = new APIHandler();
        HttpResponse<String> rawResponse = apiHandler.getAPIResponse("2026-03-25"); // DATE FORMATTED IN "YYYY-MM-DD"

        GraphQLResponse response;
        try {
            assert rawResponse != null;
            response = apiHandler.readJsonIntoObject(rawResponse.body());
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
    }
}