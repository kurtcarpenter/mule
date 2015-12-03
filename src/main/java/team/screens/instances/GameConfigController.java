package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.scene.control.*;

import team.screens.AScreen;
import team.MainApp;
import team.config.GameSettings;
import team.Game;

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
  private Button loadGameButton;

  /**
   * Initialize method called to set the appropriate responses of the items on the screen.
   * 
   * @param url the URL
   * @param rb the ResourceBundle
   */
  @FXML
  public void initialize(URL url, ResourceBundle rb) {
    configPlayersButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        String difficulty = difficultyBox.getSelectionModel().getSelectedItem() == null
            ? "Beginner" : difficultyBox.getSelectionModel().getSelectedItem().toString();
        String map = mapBox.getSelectionModel().getSelectedItem() == null ? "Standard" :
            mapBox.getSelectionModel().getSelectedItem().toString();
        int numPlayers = playersBox.getSelectionModel().getSelectedItem() == null ? 2 :
            Integer.parseInt(playersBox.getSelectionModel().getSelectedItem().toString().substring(
            0,1));
        parent.config.setSettings(new GameSettings(GameSettings.Difficulty.valueOf(
            difficulty.toUpperCase()), GameSettings.Map.valueOf(map.toUpperCase()), numPlayers));
        parent.game.updateSettings();
        parent.displayScreen(MainApp.PLAYER_CONFIG_SCREEN);
      }
    });

    loadGameButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Game oldGame = parent.game.loadGame();
        if (oldGame != null) {
          parent.game = oldGame;
          System.out.println("Saved Game");
          parent.displayScreen(MainApp.MAINMAP_SCREEN);
        } else {
          System.out.println("Game could not be loaded!");
        }
      }
    });
  }
}