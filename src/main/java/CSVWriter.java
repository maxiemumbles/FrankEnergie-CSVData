import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVWriter {
    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void writeToCSV(List<String[]> data) throws IOException {
        File csvFile = new File(Main.CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvFile)) {
            data.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    private String escapeSpecialCharacters(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        String escapedData = data.replaceAll("\\R", " ");
        if (escapedData.contains(",") || escapedData.contains("\"") || escapedData.contains("'")) {
            escapedData = escapedData.replace("\"", "\"\"");
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }
}
