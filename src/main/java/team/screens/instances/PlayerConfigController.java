package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;

import team.screens.AScreen;
import team.MainApp;
import team.config.Player;
import team.Game.GameState;

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

    private List<String> colorsUsed;

    private List<String> namesUsed;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colorsUsed = new ArrayList<>();
        namesUsed = new ArrayList<>();
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean nameExists = false;
                String name = nameField.getText();
                String color = colorBox.getSelectionModel().getSelectedItem() == null ? "Red" :
                    colorBox.getSelectionModel().getSelectedItem().toString();
                String race = raceBox.getSelectionModel().getSelectedItem() == null ? "Flapper" :
                    raceBox.getSelectionModel().getSelectedItem().toString();
                
                if (namesUsed.contains(name))
                    nameExists = true;

                if (!nameExists) {
                    namesUsed.add(name);
                    colorsUsed.add(color);
                    parent.config.getPlayers().add(new Player(nameField.getText(),
                        Player.PlayerRace.valueOf(race.toUpperCase()),
                        Player.PlayerColor.valueOf(color.toUpperCase())));
                    // System.out.println(parent.config.getPlayers().size());
                    if (parent.config.getSettings().getNumPlayers() <= parent.config.getPlayers().size()) {
                        parent.game.setCurrentState(GameState.LAND_SELECT);
                        parent.game.getTurnManager().setState(GameState.LAND_SELECT);
                        parent.displayScreen(MainApp.MAINMAP_SCREEN);
                    } else {
                        nameField.setText("Player " + (parent.config.getPlayers().size() + 1));
                        playerNumText.setText("Player " + (parent.config.getPlayers().size() + 1));
                        raceBox.setValue("Flapper");
                        ObservableList<String> colors = colorBox.getItems();
                        while (colorsUsed.contains(colors.get(0)))
                            colors.remove(0);
                        colorBox.setValue(colors.get(0));
                        parent.displayScreen(MainApp.PLAYER_CONFIG_SCREEN);
                    }
                } else {
                    nameField.setText("Name already used.");
                    nameField.requestFocus();
                }
            }
        });
    }


    /* Example method to navigate to a different screen
    @FXML
    private void goToMain(ActionEvent event){
        parent.setScreen(ScreensFramework.MAIN_SCREEN);
    }*/
}
