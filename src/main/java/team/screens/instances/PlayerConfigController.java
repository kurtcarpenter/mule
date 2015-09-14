package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;

import team.screens.AScreen;

public class PlayerConfigController extends AScreen {

    @FXML
	// fx:id="configPlayersButton"
	private Button next;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configPlayersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.displayScreen(MainApp.BLANK_SCREEN);
            }
        };
    }


    /* Example method to navigate to a different screen
    @FXML
    private void goToMain(ActionEvent event){
        parent.setScreen(ScreensFramework.MAIN_SCREEN);
    }*/
}
