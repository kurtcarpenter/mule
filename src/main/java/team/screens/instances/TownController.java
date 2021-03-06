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

public class TownController extends AScreen {
  @FXML
  private Button landOfficeButton;

  @FXML
  private Button pubButton;

  @FXML
  private Button storeButton;

  @FXML
  private Button saveGameButton;

  @FXML
  private Button backButton;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    backButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        parent.displayScreen(MainApp.MAINMAP_SCREEN);
      }
    });

    pubButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        parent.displayScreen(MainApp.PUB_SCREEN);
      }
    });

    storeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        parent.displayScreen(MainApp.STORE_SCREEN);
      }
    });

    saveGameButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (parent.game.saveGame()) {
          System.out.println("Saved Game");
        } else {
          System.out.println("Could not save game!");
        }
      }
    });
  }
}