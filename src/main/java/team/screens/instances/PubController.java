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
import javafx.scene.control.Label;

import team.screens.AScreen;
import team.MainApp;
import team.config.Player;

public class PubController extends AScreen {
  @FXML
  private Button gambleButton;

  @FXML
  private Button backButton;

  @FXML
  private Label timerLabel;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    backButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        parent.displayScreen(MainApp.TOWN_SCREEN);
      }
    });

    gambleButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        parent.game.getPubManager().gamble();
      }
    });
  }

  public void update() {
    timerLabel.textProperty().bind(parent.game.getTimerManager()
          .getTimerBinding());
  }
}