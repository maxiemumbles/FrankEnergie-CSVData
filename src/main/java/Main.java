import java.io.IOException;
import java.net.http.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;

public class Main {
    static String URL = "https://www.frankenergie.nl/graphql";
    static String CSV_FILE_NAME = "HourlyData.csv";

    static void main() {
        CSVWriter csvWriter = new CSVWriter();
        APIHandler apiHandler = new APIHandler();

        ArrayList<String[]> pricePoints = new ArrayList<>();

        LocalDate dateNow = LocalDate.now();
        for (LocalDate incrementingDate = dateNow.minusYears(1); incrementingDate.isBefore(dateNow); incrementingDate = incrementingDate.plusDays(1)) {
            System.out.println(incrementingDate);
            HttpResponse<String> rawResponse = apiHandler.getAPIResponse(incrementingDate.toString()); // DATE FORMATTED IN "YYYY-MM-DD"

            GraphQLResponse response;
            try {
                assert rawResponse != null;
                response = apiHandler.readJsonIntoObject(rawResponse.body());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            response.getData().getMarketPrices().getElectricityPrices().forEach(pricePoint ->
                    pricePoints.add(new String[] {pricePoint.getFrom(), String.valueOf(pricePoint.getAllInPrice()), pricePoint.getPerUnit()}));
        }

        try {
            csvWriter.writeToCSV(pricePoints);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}