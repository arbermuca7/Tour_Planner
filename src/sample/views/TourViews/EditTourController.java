package sample.views.TourViews;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;
import sample.models.Tour;
import sample.viewModels.TourVM.EditTourViewModel;
import sample.views.MainWindowController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditTourController implements Initializable {

    public MainWindowController mainWindowController = new MainWindowController();

    public TextField NameTextField;
    public TextField StartingPointTextField;
    public TextField DestinationTextField;
    public TextField DistanceTextField;
    public TextArea DescriptionTextField;
    public Button EditBtn;

    int index = -1;
    String ident = mainWindowController.getIdentification();
    
    public JavaAppManager manager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-->EditTourController init");
        //set the value of the tour we want to edit
        setTourDataToEdit();
        //manager initialisation
        manager = JavaAppManagerFactory.GetManager();
        //validate the input fields
        validate_WordsTextFields(NameTextField);
        validate_WordsTextFields(StartingPointTextField);
        validate_WordsTextFields(DestinationTextField);
        validate_NumbersTextFields(DistanceTextField);
    }

    public void editTourD(ActionEvent actionEvent) throws SQLException {
        //String ident = mainWindowController.getIdentification();
        //get the index of the selected tour
        index = mainWindowController.TourListView.getSelectionModel().getSelectedIndex();
        //remove the selected Tour
        mainWindowController.TourListView.getItems().remove(index);
        //get the edited data
        String data = tourData();
        String[] content = data.split(",");
        String name = content[0];
        String start= content[1];
        String dest = content[2];
        String dist = content[3];
        String desc = content[4];
        double distance = 0;
        if (!dist.isEmpty())
            distance = Double.parseDouble(dist);
        //create the new updated tour
        Tour tour = new Tour(ident,name,desc,start,dest,distance);
        //edit Tour in DB
        manager.editData(tour,ident);
        //add the updated Tour to the ListView
        mainWindowController.TourListView.getItems().add(index,tour);
        //close the window
        Stage stage = (Stage) EditBtn.getScene().getWindow();
        stage.close();
    }
    public void setTourDataToEdit(){
        Tour tourG = null;
        NameTextField.setText(tourG.getT_Name());
        StartingPointTextField.setText(tourG.getStartPoint());
        DestinationTextField.setText(tourG.getDestination());
        DescriptionTextField.setText(tourG.getDescription());
        String distance = "";
        if (tourG.getT_Distance()!=0)
            distance = Double.toString(tourG.getT_Distance());
        DistanceTextField.setText(distance);
    }

    public String tourData(){
        String name = this.NameTextField.getText();
        String start = this.StartingPointTextField.getText();
        String dest = this.DescriptionTextField.getText();
        String dist = this.DistanceTextField.getText();
        String desc = this.DescriptionTextField.getText();

        return name+","+start+","+dest+","+dist+","+desc;
    }
    /**
     * a method to validate the input of
     * a text field where can be set only letters, space and number between 1 and 9
     * @param textField the field which will be validated
     * */
    public void validate_WordsTextFields(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z1-9 ]*")) ? change : null));

    }
    /**
     * a method to validate the numbers input of
     * a text field where we set maximal 2 digits after the "."
     * @param textField the field which will be validated
     * */
    public void validate_NumbersTextFields(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-9][0-9]*)?([.])?([1-9][0-9]{0,1})?")) ? change : null));
    }
}
