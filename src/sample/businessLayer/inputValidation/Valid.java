package sample.businessLayer.inputValidation;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Valid implements IValid{

    private static final Logger logger = LogManager.getLogger(Valid.class);

    /**
     * a method to validate the date input
     * in a format dd.mm.yyyy
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_date(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([0-9.]{0,10})?")) ? change : null));
        logger.info("date validated");
    }

    /**
     * a method to validate the time spend on route input
     * in a format hh:mm:ss
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_duration(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([0-9:]{0,8})?")) ? change : null));
        logger.info("duration validated");

    }

    /**
     * a method to validate the numbers input of
     * a text field  where we set maximal 2 digits after the "."
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_distance_fuel(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([0-9][0-9]*)?([.])?([0-9][0-9]?)?")) ? change : null));
        logger.info("distance and fuel costs validated");

    }

    /**
     * a method to validate the speed input of
     * a text field  where we set between 2 and 5 digits
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_speed(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([0-9]{0,5})")) ? change : null));
        logger.info("speed validated");

    }

    /**
     * a method to validate the rating input
     * between 1 and 5 with a certain regex
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_rate(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-5]?)")) ? change : null));
        logger.info("rating validated");
    }

    /**
     * a method to validate the true/false input with
     * the format of just being possible to write true or false
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_toll(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[tf]?")) ? change : null));
        logger.info("toll road and resting place validated");

    }

    /**
     * a method to validate the input of
     * a text field where can be set only letters, space and number between 1 and 9
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_WordsTextFields(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));
        logger.info("all fields containing only text validated");
    }

    /**
     * a method to validate the input of
     * a text field where can be set only letters, space and number between 1 and 9
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_specialTextFields(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z1-9 ]*")) ? change : null));
        logger.info("fields which contain letters and numbers validated");

    }
}
