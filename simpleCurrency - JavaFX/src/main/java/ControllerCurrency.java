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

//    private ModelCurrency model;


    @FXML protected void convertAction() {
        toCurr.getItems();
    }

    @FXML protected void quitApplication() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ModelCurrency model = new ModelCurrency();

        ChangeListener<String> fieldChange = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Change detected");
                StringProperty textProp = (StringProperty) observable;
                TextField txtFld = (TextField) textProp.getBean();
                // TODO handle empty string and invalid input
                if (txtFld.equals(fromField)) {
                    // TODO add functionality change
                    if (fromField.isFocused()) {
                        BigDecimal num = new BigDecimal(fromField.getText());
//                        num = num.add(BigDecimal.ONE).setScale(2, BigDecimal.ROUND_HALF_UP);
                        num = num.multiply(model.getExchangeRate()).setScale(4, BigDecimal.ROUND_HALF_UP);;
                        toField.setText(String.valueOf(num));
                    }
                } else {
                    // TODO add functionality change
                    if (toField.isFocused()) {
                        BigDecimal num = new BigDecimal(toField.getText());
//                        num = num.add(BigDecimal.ONE);
                        num = num.divide(model.getExchangeRate(), BigDecimal.ROUND_HALF_UP).setScale(4, BigDecimal.ROUND_HALF_UP);
                        fromField.setText(String.valueOf(num));
                    }
                }
            }
        };

        EventHandler<ActionEvent> changeCurr = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Changed!");
                updateCurrency(model);
            }
        };

        fromField.textProperty().addListener(fieldChange);
        toField.textProperty().addListener(fieldChange);
        fromCurr.setOnAction(changeCurr);
        toCurr.setOnAction(changeCurr);



        fromCurr.getItems().addAll(ModelCurrency.CURRENCIES);
        toCurr.getItems().addAll(ModelCurrency.CURRENCIES);
    }

    private void updateCurrency(ModelCurrency model) {
        model.changeExchangeRate(fromCurr.getValue(), toCurr.getValue());
    }
}
