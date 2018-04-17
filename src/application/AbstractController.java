package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

/**
 * @controller AbstractController
 * Zadefinovanie abstraktneho controllera pre dedicnost
 * 
 */
public abstract class AbstractController implements Initializable{
	
	/**
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 * Vseobecna metoda, ktora sa nachadza v kazdom controlleri
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
}