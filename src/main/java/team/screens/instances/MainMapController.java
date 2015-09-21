package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import team.screens.AScreen;

public class MainMapController extends AScreen {

    @FXML
    private Button townButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //who knows?
    }

    /* Example method to navigate to a different screen
    @FXML
    private void goToMain(ActionEvent event){
        parent.setScreen(ScreensFramework.MAIN_SCREEN);
    }*/
}
