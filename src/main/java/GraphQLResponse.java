import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Root response object from Frank Energie GraphQL API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphQLResponse {
    @JsonProperty("data")
    private Data data;

    @JsonProperty("errors")
    private List<GraphQLError> errors;

    // Constructors
    public GraphQLResponse() {}

    public GraphQLResponse(Data data) {
        this.data = data;
    }

    // Getters and Setters
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<GraphQLError> getErrors() {
        return errors;
    }

    public void setErrors(List<GraphQLError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "MarketPricesResponse{" +
                "data=" + data +
                ", errors=" + errors +
                '}';
    }
}

/**
 * Data wrapper containing market prices
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Data {

    @JsonProperty("marketPrices")
    private MarketPrices marketPrices;

    // Constructors
    public Data() {}

    public Data(MarketPrices marketPrices) {
        this.marketPrices = marketPrices;
    }

    // Getters and Setters
    public MarketPrices getMarketPrices() {
        return marketPrices;
    }

    public void setMarketPrices(MarketPrices marketPrices) {
        this.marketPrices = marketPrices;
    }

    @Override
    public String toString() {
        return "Data{" +
                "marketPrices=" + marketPrices +
                '}';
    }
}

/**
 * Market prices container
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class MarketPrices {

    @JsonProperty("averageElectricityPrices")
    private AveragePrice averageElectricityPrices;

    @JsonProperty("electricityPrices")
    private List<PricePoint> electricityPrices;

    @JsonProperty("gasPrices")
    private List<PricePoint> gasPrices;

    // Constructors
    public MarketPrices() {}

    // Getters and Setters
    public AveragePrice getAverageElectricityPrices() {
        return averageElectricityPrices;
    }

    public void setAverageElectricityPrices(AveragePrice averageElectricityPrices) {
        this.averageElectricityPrices = averageElectricityPrices;
    }

    public List<PricePoint> getElectricityPrices() {
        return electricityPrices;
    }

    public void setElectricityPrices(List<PricePoint> electricityPrices) {
        this.electricityPrices = electricityPrices;
    }

    public List<PricePoint> getGasPrices() {
        return gasPrices;
    }

    public void setGasPrices(List<PricePoint> gasPrices) {
        this.gasPrices = gasPrices;
    }

    @Override
    public String toString() {
        return "MarketPrices{" +
                "averageElectricityPrices=" + averageElectricityPrices +
                ", electricityPrices count=" + (electricityPrices != null ? electricityPrices.size() : 0) +
                ", gasPrices count=" + (gasPrices != null ? gasPrices.size() : 0) +
                '}';
    }
}

/**
 * Average price information for a given period
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class AveragePrice {

    @JsonProperty("averageMarketPrice")
    private Double averageMarketPrice;

    @JsonProperty("averageMarketPricePlus")
    private Double averageMarketPricePlus;

    @JsonProperty("averageAllInPrice")
    private Double averageAllInPrice;

    @JsonProperty("perUnit")
    private String perUnit;

    @JsonProperty("isWeighted")
    private Boolean isWeighted;

    // Constructors
    public AveragePrice() {}

    // Getters and Setters
    public Double getAverageMarketPrice() {
        return averageMarketPrice;
    }

    public void setAverageMarketPrice(Double averageMarketPrice) {
        this.averageMarketPrice = averageMarketPrice;
    }

    public Double getAverageMarketPricePlus() {
        return averageMarketPricePlus;
    }

    public void setAverageMarketPricePlus(Double averageMarketPricePlus) {
        this.averageMarketPricePlus = averageMarketPricePlus;
    }

    public Double getAverageAllInPrice() {
        return averageAllInPrice;
    }

    public void setAverageAllInPrice(Double averageAllInPrice) {
        this.averageAllInPrice = averageAllInPrice;
    }

    public String getPerUnit() {
        return perUnit;
    }

    public void setPerUnit(String perUnit) {
        this.perUnit = perUnit;
    }

    public Boolean getIsWeighted() {
        return isWeighted;
    }

    public void setIsWeighted(Boolean isWeighted) {
        this.isWeighted = isWeighted;
    }

    @Override
    public String toString() {
        return "AveragePrice{" +
                "averageMarketPrice=" + averageMarketPrice +
                ", averageMarketPricePlus=" + averageMarketPricePlus +
                ", averageAllInPrice=" + averageAllInPrice +
                ", perUnit='" + perUnit + '\'' +
                ", isWeighted=" + isWeighted +
                '}';
    }
}

/**
 * Individual price point for a specific time slot
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class PricePoint {

    @JsonProperty("from")
    private String from;

    @JsonProperty("till")
    private String till;

    @JsonProperty("marketPrice")
    private Double marketPrice;

    @JsonProperty("marketPricePlus")
    private Double marketPricePlus;

    @JsonProperty("allInPrice")
    private Double allInPrice;

    @JsonProperty("perUnit")
    private String perUnit;

    @JsonProperty("marketPricePlusComponents")
    private List<PriceComponent> marketPricePlusComponents;

    @JsonProperty("allInPriceComponents")
    private List<PriceComponent> allInPriceComponents;

    // Constructors
    public PricePoint() {}

    // Getters and Setters
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTill() {
        return till;
    }

    public void setTill(String till) {
        this.till = till;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getMarketPricePlus() {
        return marketPricePlus;
    }

    public void setMarketPricePlus(Double marketPricePlus) {
        this.marketPricePlus = marketPricePlus;
    }

    public Double getAllInPrice() {
        return allInPrice;
    }

    public void setAllInPrice(Double allInPrice) {
        this.allInPrice = allInPrice;
    }

    public String getPerUnit() {
        return perUnit;
    }

    public void setPerUnit(String perUnit) {
        this.perUnit = perUnit;
    }

    public List<PriceComponent> getMarketPricePlusComponents() {
        return marketPricePlusComponents;
    }

    public void setMarketPricePlusComponents(List<PriceComponent> marketPricePlusComponents) {
        this.marketPricePlusComponents = marketPricePlusComponents;
    }

    public List<PriceComponent> getAllInPriceComponents() {
        return allInPriceComponents;
    }

    public void setAllInPriceComponents(List<PriceComponent> allInPriceComponents) {
        this.allInPriceComponents = allInPriceComponents;
    }

    @Override
    public String toString() {
        return "PricePoint{" +
                "from='" + from + '\'' +
                ", till='" + till + '\'' +
                ", marketPrice=" + marketPrice +
                ", marketPricePlus=" + marketPricePlus +
                ", allInPrice=" + allInPrice +
                ", perUnit='" + perUnit + '\'' +
                '}';
    }
}

/**
 * Price component breakdown (taxes, surcharges, etc.)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class PriceComponent {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private Double value;

    // Constructors
    public PriceComponent() {}

    public PriceComponent(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PriceComponent{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

/**
 * GraphQL error response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class GraphQLError {

    @JsonProperty("message")
    private String message;

    @JsonProperty("locations")
    private List<ErrorLocation> locations;

    // Constructors
    public GraphQLError() {}

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<ErrorLocation> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "GraphQLError{" +
                "message='" + message + '\'' +
                ", locations=" + locations +
                '}';
    }
}

/**
 * Error location in GraphQL query
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorLocation {

    @JsonProperty("line")
    private Integer line;

    @JsonProperty("column")
    private Integer column;

    // Constructors
    public ErrorLocation() {}

    // Getters and Setters
    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "ErrorLocation{" +
                "line=" + line +
                ", column=" + column +
                '}';
    }
}