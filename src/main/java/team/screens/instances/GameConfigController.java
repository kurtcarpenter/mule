package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import team.screens.AScreen;
import team.MainApp;

public class GameConfigController extends AScreen {

	@FXML
	// fx:id="difficultyBox"
	private ComboBox<String> difficultyBox;

	@FXML
	// fx:id="mapBox"
	private ComboBox<String> mapBox;

	@FXML
	// fx:id="playersBox"
	private ComboBox<String> playersBox;

	@FXML
	// fx:id="configPlayersButton"
	private Button configPlayersButton;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        configPlayersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //String difficulty = difficultyBox.getSelectionModel().getSelectedItem().toString();
                //String map = mapBox.getSelectionModel().getSelectedItem().toString();
                int numPlayers = Integer.parseInt(playersBox.getSelectionModel().getSelectedItem().toString().substring(0,1));

                parent.displayScreen(MainApp.PLAYER_CONFIG_SCREEN);
            }
        });
    }
}