package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import team.screens.AScreen;
import team.MainApp;
import team.config.Player;
import team.game.containers.MuleType;

public class StoreController extends AScreen {

	@FXML
	private TextField foodQuantity;
	@FXML
	private TextField energyQuantity;
	@FXML
	private TextField smithoreQuantity;
	@FXML
	private TextField crystiteQuantity;
	@FXML
	private TextField muleQuantity;
	@FXML
	private RadioButton foodButton;
	@FXML
	private RadioButton energyButton;
	@FXML
	private RadioButton smithoreButton;
	@FXML
	private RadioButton crystiteButton;
	@FXML
	private Button purchaseButton;
	@FXML
	private Button sellButton;
	@FXML
	private Button backButton;
	private MuleType type = MuleType.FOOD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	foodButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
          	public void handle(ActionEvent event) {
           		energyButton.setSelected(false);
           		smithoreButton.setSelected(false);
           		crystiteButton.setSelected(false);
           		type = MuleType.FOOD;
          	}
      	});

      	energyButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
          	public void handle(ActionEvent event) {
           		foodButton.setSelected(false);
           		smithoreButton.setSelected(false);
           		crystiteButton.setSelected(false);
           		type = MuleType.ENERGY;
          	}
      	});

      	smithoreButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
          	public void handle(ActionEvent event) {
           		foodButton.setSelected(false);
           		energyButton.setSelected(false);
           		crystiteButton.setSelected(false);
           		type = MuleType.SMITHORE;
          	}
      	});

      	crystiteButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
          	public void handle(ActionEvent event) {
           		foodButton.setSelected(false);
           		energyButton.setSelected(false);
           		smithoreButton.setSelected(false);
           		type = MuleType.CRYSTITE;
          	}
      	});

    	backButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
          	public void handle(ActionEvent event) {
           		parent.displayScreen(MainApp.TOWN_SCREEN);
          	}
      	});
    }

    /* Example method to navigate to a different screen
    @FXML
    private void goToMain(ActionEvent event){
        parent.setScreen(ScreensFramework.MAIN_SCREEN);
    }*/
}
