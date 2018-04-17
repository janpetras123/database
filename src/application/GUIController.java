package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import application.SponzorInsertController;


/**
 * Trieda GUIController prepojuje GUI s logikou.
 * Sú tu namapované všetky funkèné èasti (fx:id), 
 * ako sú textové polia, tlaèidlá,tabu¾ky atï.
 * Volania z GUI sú zadefinované a volané ako anonymné volania eventov.
 * V týchto anonymných eventoch sú vykonané iba volania iných tried 
 * alebo vykonania výpisov v GUI rozhraní.
 */


public class GUIController extends AbstractController{
	public static int line, counterAll, i;
	public static String name, surname;
	int allview=1, sum;
	
	@FXML
	TextField NameSponzorId;
	@FXML
	DatePicker CreatSponzorId;
	@FXML
	TextField LinkSponzorId;
	@FXML
	Button AddSponzorId;
	@FXML
	Button DeleteSponzorId;
	
	@FXML
	TextField CountryMiestoPretekuId;
	@FXML
	TextField AddressMiestoPretekuId;
	@FXML
	Button AddMiestoPretekuId;
	@FXML
	Button DeleteMiestoPretekuId;
	
	@FXML
	TextField NamePretekarId;
	@FXML
	TextField SurnamePretekarId;
	@FXML
	DatePicker DateBirthPretekarId;
	@FXML
	Spinner<Integer> WeightPretekarId;
	@FXML
	TextField TeamPretekarId;
	@FXML
	TextField CountryPretekarId;
	@FXML
	TextField AddressPretekarId;
	@FXML
	Button UpdatePretekarId;
	@FXML
	Button AddPretekarId;
	
	@FXML
	private Label NamePrehladId;
	@FXML
	private Label SurnamePrehladId;
	@FXML
	private Label BirthdayPrehladId;
	@FXML
	private Label WeightPrehladId;
	@FXML
	private Label RacesNumberPrehladId;
	@FXML
	TextField RacerTeamPrehladId;
	@FXML
	TextField RacerNamePrehladId;
	@FXML
	TextField RacerSurnamePrehladId;
	@FXML
	Button FindPrehladId;
	
	@FXML
	TextField NameOverviewField;
	@FXML
	TextField SurnameOverviewField;
	@FXML
	Button FindOverviewId;
	@FXML
	Button UpdateOverviewId;
	@FXML
	Button DeleteOverviewId;
	@FXML
	TableColumn<Overview, Number> IdOverviewId;
	@FXML
	TableColumn<Overview, String> NameOverviewId;
	@FXML
	TableColumn<Overview, String> SurnameOverviewId;
	@FXML
	TableColumn<Overview, String> TeamOverviewId;
	@FXML
	TableColumn<Overview, String> CountryOverviewId;
	@FXML
	TableColumn<Overview, Number> RacesOverviewId;
	@FXML
	TableView<Overview> tableId;
	@FXML
	Button Previous;
	@FXML
	Button Next;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		SponzorInsertController sponzorInsDB = new SponzorInsertController();
		SponzorDeleteController sponzorDelDB = new SponzorDeleteController();
		MiestoPretekuInsertController miestoPretekuInsuDB = new MiestoPretekuInsertController();
		MiestoPretekuDeleteController miestoPretekuDelDB = new MiestoPretekuDeleteController();
		PretekarInsertController pretekDB = new PretekarInsertController();
		ViewPretekarController viewPretekarDB = new ViewPretekarController();
		OverviewController overviewDB = new OverviewController();
		OverviewAllController overviewAllDB = new OverviewAllController();
		OverviewDeleteController deleteDB = new OverviewDeleteController();
		OverviewDeleteAllController deleteAllDB = new OverviewDeleteAllController();
		

		
		overviewAllDB.Input(0, tableId); 
		allview = 1;
		
		NameOverviewId.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		SurnameOverviewId.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
		TeamOverviewId.setCellValueFactory(cellData -> cellData.getValue().teamProperty());
		CountryOverviewId.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
		RacesOverviewId.setCellValueFactory(cellData -> cellData.getValue().racesProperty());
		
		
		
		AddSponzorId.setOnAction((event)->{
			if((!NameSponzorId.getText().equals("")) && (CreatSponzorId.getValue() != null) && (!LinkSponzorId.getText().equals("")))
			{
				
				sponzorInsDB.Input(NameSponzorId.getText(), CreatSponzorId.getValue(), LinkSponzorId.getText());
				
			}else{
				
				Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Incorrect value in some field(s).");

				alert.showAndWait();
			}	
		});
		
		DeleteSponzorId.setOnAction((event)->{
			if((!NameSponzorId.getText().equals("")) && (CreatSponzorId.getValue() != null) && (!LinkSponzorId.getText().equals("")))
			{
				
				sponzorDelDB.Input(NameSponzorId.getText(), CreatSponzorId.getValue(), LinkSponzorId.getText());
				
			}else{
				
				Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Incorrect value in some field(s).");

				alert.showAndWait();
				
			}	
		});
		
		AddMiestoPretekuId.setOnAction((event)->{
			if((!CountryMiestoPretekuId.getText().equals("")) && (!AddressMiestoPretekuId.getText().equals("")))
			{
				
				miestoPretekuInsuDB.Input(CountryMiestoPretekuId.getText(), AddressMiestoPretekuId.getText());
				
			}else{
				
				Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Incorrect value in some field(s).");

				alert.showAndWait();
				
			}		
		});
		
		DeleteMiestoPretekuId.setOnAction((event)->{
			if((!CountryMiestoPretekuId.getText().equals("")) && (!AddressMiestoPretekuId.getText().equals("")))
			{
				
				miestoPretekuDelDB.Input(CountryMiestoPretekuId.getText(), AddressMiestoPretekuId.getText());
				
			}else{
				
				Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Incorrect value in some field(s).");

				alert.showAndWait();
				
			}		
		});
		
		AddPretekarId.setOnAction((event)->{
			if((!NamePretekarId.getText().equals("")) && (!SurnamePretekarId.getText().equals("")) && (DateBirthPretekarId.getValue() != null) && (WeightPretekarId.getValue() != null) && (!TeamPretekarId.getText().equals("")) && (!CountryPretekarId.getText().equals("")) && (!AddressPretekarId.getText().equals("")))
			{
				
				pretekDB.Input(NamePretekarId.getText(), SurnamePretekarId.getText(), DateBirthPretekarId.getValue(), WeightPretekarId.getValue(), TeamPretekarId.getText(), CountryPretekarId.getText(), AddressPretekarId.getText());
				
			}else{
				
				Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Incorrect value in some field(s).");

				alert.showAndWait();
				
			}
		});
				
		FindPrehladId.setOnAction((event)->{
			if((!RacerNamePrehladId.getText().equals("")) && (!RacerSurnamePrehladId.getText().equals("")) && (!RacerTeamPrehladId.getText().equals("")))
			{
				
				viewPretekarDB.Input(RacerNamePrehladId.getText(), RacerSurnamePrehladId.getText(), RacerTeamPrehladId.getText());
				
				NamePrehladId.setText(ViewPretekarController.nameLab);
				SurnamePrehladId.setText(ViewPretekarController.surnameLab);
				BirthdayPrehladId.setText(ViewPretekarController.birthdayLab);
				WeightPrehladId.setText(ViewPretekarController.weightLab);
				RacesNumberPrehladId.setText(ViewPretekarController.racesLab);
				
				
			}else{
				
				Alert alert = new Alert(AlertType.ERROR  /*ERROR*/);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Incorrect value in some field(s).");

				alert.showAndWait();
				
			}
		});
		
		FindOverviewId.setOnAction((event)->{
			i=0;
			if((!NameOverviewField.getText().equals("")) && (!SurnameOverviewField.getText().equals("")))
			{
				System.out.println("Tu nechcem byt");
				tableId.getItems().clear();
				allview = 0;
				name = NameOverviewField.getText();
				surname = SurnameOverviewField.getText();
				
				overviewDB.Input(NameOverviewField.getText(), SurnameOverviewField.getText(), 0, tableId);
				
			}else{
				
				tableId.getItems().clear();
				allview = 1;
				overviewAllDB.Input(0, tableId);
				
			}
			
			NameOverviewId.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
			SurnameOverviewId.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
			TeamOverviewId.setCellValueFactory(cellData -> cellData.getValue().teamProperty());
			CountryOverviewId.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
			RacesOverviewId.setCellValueFactory(cellData -> cellData.getValue().racesProperty());
		});
		
		Previous.setOnAction((event)->{
			tableId.getItems().clear();
			
			if(i > 0) {
				i--;
				if(allview == 1)
					overviewAllDB.Input(i, tableId);
				else
					overviewDB.Input(name, surname, i, tableId);
				
			}else {
				
				if(allview == 1)
					overviewAllDB.Input(i, tableId);
				else
					overviewDB.Input(name, surname, i, tableId);

			}
			
			NameOverviewId.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
			SurnameOverviewId.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
			TeamOverviewId.setCellValueFactory(cellData -> cellData.getValue().teamProperty());
			CountryOverviewId.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
			RacesOverviewId.setCellValueFactory(cellData -> cellData.getValue().racesProperty());
			
		});
		
		Next.setOnAction((event)->{
			sum = counterAll / 20;
			tableId.getItems().clear();
			
			if(i < sum) {
				i++;
				if(allview == 1)
					overviewAllDB.Input(i, tableId);
				else
					overviewDB.Input(name, surname, i, tableId);
				
			}else {
				
				if(allview == 1)
					overviewAllDB.Input(i, tableId);
				else
					overviewDB.Input(name, surname, i, tableId);
				
			}
			
			NameOverviewId.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
			SurnameOverviewId.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
			TeamOverviewId.setCellValueFactory(cellData -> cellData.getValue().teamProperty());
			CountryOverviewId.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
			RacesOverviewId.setCellValueFactory(cellData -> cellData.getValue().racesProperty());
			
		});
		
		UpdateOverviewId.setOnAction((event)-> {
			line = tableId.getSelectionModel().selectedIndexProperty().get();
			name = NameOverviewField.getText();
			surname = SurnameOverviewField.getText();
			
			if(line >= 0)
			{
				Stage popout;
				try {
					
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("Update.fxml"));
					AnchorPane updateRacer = loader.load();
					popout = new Stage();
					popout.setTitle("Update racer");
					popout.initModality(Modality.WINDOW_MODAL);
					popout.initOwner(Main.primaryStage);
					Scene scene = new Scene(updateRacer,600,400);
					popout.setScene(scene);
					scene.getStylesheets().add(getClass().getResource("File.css").toExternalForm());
					popout.showAndWait();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		DeleteOverviewId.setOnAction((event)-> {
			line = tableId.getSelectionModel().selectedIndexProperty().get();
			
			if(line >= 0)
			if((!NameOverviewField.getText().equals("")) && (!SurnameOverviewField.getText().equals("")))
			{
				deleteDB.Input();
	
			}else {
				
				deleteAllDB.Input();
			}
		});
	}
}
