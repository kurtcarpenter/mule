package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import team.screens.AScreen;
import team.MainApp;
import team.config.Player;

public class PlayerConfigController extends AScreen {

    @FXML
    private Text playerNumText;

    @FXML
	private ComboBox<String> raceBox;

	@FXML
	private ComboBox<String> colorBox;

	@FXML
	private TextField nameField;

    @FXML
    private Button nextButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameField.getText();
                String color = colorBox.getSelectionModel().getSelectedItem() == null ? "Red" :
                    colorBox.getSelectionModel().getSelectedItem().toString();
                String race = raceBox.getSelectionModel().getSelectedItem() == null ? "Human" :
                    raceBox.getSelectionModel().getSelectedItem().toString();
                parent.config.getPlayers().add(new Player(nameField.getText(),
                    Player.PlayerRace.valueOf(race.toUpperCase()),
                    Player.PlayerColor.valueOf(color.toUpperCase())));
                // System.out.println(parent.config.getPlayers().size());
                if (parent.config.getSettings().getNumPlayers() <= parent.config.getPlayers().size()) {
                    parent.displayScreen(MainApp.MAINMAP_SCREEN);
                } else {
                    nameField.clear();
                    raceBox.setValue(null);
                    colorBox.setValue(null);
                    parent.displayScreen(MainApp.PLAYER_CONFIG_SCREEN);
                }
                playerNumText.setText("Player Number: " + (parent.config.getPlayers().size() + 1));
            }
        });
    }


    /* Example method to navigate to a different screen
    @FXML
    private void goToMain(ActionEvent event){
        parent.setScreen(ScreensFramework.MAIN_SCREEN);
    }*/
}
