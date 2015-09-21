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

import team.screens.AScreen;

public class MainMapController extends AScreen {

    @FXML
    private Button townButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      townButton.setOnAction(new EventHandler<ActionEvent>() {
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
