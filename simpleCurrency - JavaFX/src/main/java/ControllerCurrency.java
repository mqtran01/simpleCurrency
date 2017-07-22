import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCurrency implements Initializable {


    @FXML private TextField fromField;
    @FXML private TextField toField;
    @FXML private ComboBox<String> fromCurr;
    @FXML private ComboBox<String> toCurr;

    @FXML protected void updateRates() {

    }

    @FXML protected void quitApplication() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Only one model exists to attach to this controller
        final ModelCurrency model = new ModelCurrency();

        // TODO change to lambda
        // TextField listener
        ChangeListener<String> fieldChange = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Change detected");
                // Handles empty string
                if (newValue.equals("")) {
                    fromField.setText("");
                    toField.setText("");
                    return;
                }

                // Handles normal case
                StringProperty textProp = (StringProperty) observable;
                TextField changer = (TextField) textProp.getBean();
                if (changer.isFocused()) {
                    TextField follower;
                    boolean fromCheck;
                    if (changer.equals(fromField)) {
                        follower = toField;
                        fromCheck = true;
                    } else { // changer.equals(toField)
                        follower = fromField;
                        fromCheck = false;
                    }

                    String outputString;
                    try {
                        BigDecimal num = new BigDecimal(changer.getText());

                        if (fromCheck)
                            num = num.multiply(model.getExchangeRate()).setScale(4, BigDecimal.ROUND_HALF_UP);
                        else
                            num = num.divide(model.getExchangeRate(), BigDecimal.ROUND_HALF_UP).setScale(
                                    4, BigDecimal.ROUND_HALF_UP);

                        outputString = String.valueOf(num);

                    } catch (NumberFormatException e) { // Catches non numeric inputs
                        outputString = "Error";
                    }

                    follower.setText(outputString);
                }

            }
        };

        // TODO change to lambda
        // Combobox listener
        EventHandler<ActionEvent> changeCurr = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Changed!");
                updateCurrency(model);
            }
        };

        // Add in the listeners
        fromField.textProperty().addListener(fieldChange);
        toField.textProperty().addListener(fieldChange);
        fromCurr.setOnAction(changeCurr);
        toCurr.setOnAction(changeCurr);

        // Add ComboBox fields
        fromCurr.getItems().addAll(ModelCurrency.CURRENCIES);
        toCurr.getItems().addAll(ModelCurrency.CURRENCIES);
    }

    private void updateCurrency(ModelCurrency model) {
        model.changeExchangeRate(fromCurr.getValue(), toCurr.getValue());
    }
}
