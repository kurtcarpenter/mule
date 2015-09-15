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

public class PlayerConfigController extends AScreen {

    @FXML
    // fx:id="nextButton"
    private Button nextButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.displayScreen(MainApp.BLANK_SCREEN);
            }
        });
    }


    /* Example method to navigate to a different screen
    @FXML
    private void goToMain(ActionEvent event){
        parent.setScreen(ScreensFramework.MAIN_SCREEN);
    }*/
}
