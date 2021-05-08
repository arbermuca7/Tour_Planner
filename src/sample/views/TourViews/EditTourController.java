package sample.views.TourViews;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import sample.businessLayer.inputValidation.IValid;
import sample.businessLayer.inputValidation.Valid;
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
    public IValid validate = new Valid();

    public TextField NameTextField;
    public TextField StartingPointTextField;
    public TextField DestinationTextField;
    public TextField DistanceTextField;
    public TextArea DescriptionTextField;
    public Button EditBtn;
    
    public JavaAppManager manager;
    String id = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-->EditTourController init");
        //set the value of the tour we want to edit
        //manager initialisation
        manager = JavaAppManagerFactory.GetManager();
        //validate the input fields
        validate.validate_specialTextFields(NameTextField);
        validate.validate_specialTextFields(StartingPointTextField);
        validate.validate_specialTextFields(DestinationTextField);
        validate.validate_distance_fuel(DistanceTextField);
    }
    // here is edited everything which a tour contains
    public void editTourD(ActionEvent actionEvent) throws SQLException {
        System.out.println("+++++++++\nID: "+id+"\n+++++++");
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
        Tour tour = new Tour(id,name,desc,start,dest,distance);
        //edit Tour in DB
        manager.editData(tour,id);
        //add the updated Tour to the ListView
        mainWindowController.getTourListItems().clear();
        manager.GetData(mainWindowController.getTourListItems());
        //close the window
        Stage stage = (Stage) EditBtn.getScene().getWindow();
        stage.close();
    }
    //set the data to be changed in the edit window
    /**
     * @param editInfo String with all the informations of the tour
     * like identification, name, start, destination, description
     * and set these data in the edit window
     * */
    public void initEdit(String editInfo){
        String[] content = editInfo.split(",");
        id = content[0];
        NameTextField.setText(content[1]);
        StartingPointTextField.setText(content[2]);
        DestinationTextField.setText(content[3]);
        DescriptionTextField.setText(content[4]);
        DistanceTextField.setText(content[5]);
        System.out.println(id+","
                +content[1]+","+content[2]+","
                +content[3]+","+content[4]+","+content[5]);
    }
    /**
     * takes the input from the text fields of the edit window
     * @return String with all the values to be added in DB
     * */
    public String tourData(){
        String name = this.NameTextField.getText();
        String start = this.StartingPointTextField.getText();
        String dest = this.DestinationTextField.getText();
        String dist = this.DistanceTextField.getText();
        String desc = this.DescriptionTextField.getText();

        return name+","+start+","+dest+","+dist+","+desc;
    }
}
