import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Michael on 19/07/2017.
 */
public class ModelCurrency {

    public static final ArrayList<String> CURRENCIES;

    static {
        CURRENCIES = new ArrayList<>();
        CURRENCIES.add("AUD");
        CURRENCIES.add("USD");
        CURRENCIES.add("NZD");
    }

    private HashMap<String, Double> exchangeRates;
    private BigDecimal currExch = new BigDecimal(1);

    public ModelCurrency() {
        this.exchangeRates = new HashMap<>();
        this.exchangeRates.put("USD", 1.0);
        this.exchangeRates.put("AUD", 1.26);
        this.exchangeRates.put("NZD", 500.0);
    }

    public void changeExchangeRate(String from, String to) {
        if (CURRENCIES.contains(from) && CURRENCIES.contains(to)) {
            currExch = new BigDecimal(this.exchangeRates.get(to)).divide(
                    new BigDecimal(this.exchangeRates.get(from)), BigDecimal.ROUND_HALF_UP);
        } else {
            currExch = new BigDecimal(1);
        }
    }

    public BigDecimal getExchangeRate() {
        return currExch;
    }

    public void loadCurrencies() {

    }

    private void saveCurrencies() {

    }

    public void enquireCurrentRates() {
//            CurrencyLayerConnector.enquireAPI();
        /* pseudo-code (or Python)

        Call CurrencyLayer
        If HashMap:
            replace old HashMap
            replace text file data
        else:
            Bad


         */
    }
}
