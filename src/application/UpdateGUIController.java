package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;


/**
 * 
 * Trieda UpdateGUIController je kontroler pre vyskakovacie okno 
 * aktualizácie záznamu, kde sa zadávajú potrebné údaje pre aktualizáciu.
 *
 */


public class UpdateGUIController extends AbstractController{
	@FXML
	Button UpdateSetId;
	@FXML
	TextField NameSetId;
	@FXML
	TextField SurnameSetId;
	@FXML
	Spinner<Integer> WeightSetId;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		UpdateController updateDB = new UpdateController();
		UpdateAllController updateAllDB = new UpdateAllController();
		
		UpdateSetId.setOnAction((event)->{
			
			if((!GUIController.name.equals("")) && (!GUIController.surname.equals("")))
			{
				
				if((!NameSetId.getText().equals("")) && (!SurnameSetId.getText().equals("")) && (WeightSetId.getValue() != null))
				{
					
					updateDB.Input(NameSetId.getText(), SurnameSetId.getText(), WeightSetId.getValue());
				
				}else{
				
					Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
					alert.setTitle("Error Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Incorrect value in some field(s).");

					alert.showAndWait();
				}
			}else {
				
				if((!NameSetId.getText().equals("")) && (!SurnameSetId.getText().equals("")) && (WeightSetId.getValue() != null))
				{
					
					updateAllDB.Input(NameSetId.getText(), SurnameSetId.getText(), WeightSetId.getValue());
					
				}else{
				
					Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
    				alert.setTitle("Error Dialog");
    				alert.setHeaderText(null);
    				alert.setContentText("Error: Not added");

    				alert.showAndWait();
    				
				}
				
			}
		});
	}
}
