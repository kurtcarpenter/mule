package team;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import team.screens.ScreenMaster;
import team.config.Configuration;
import team.config.GameSettings;

public class MainApp extends Application {

    public static final String GAME_CONFIG_SCREEN = "gameConfig";
    public static final String GAME_CONFIG_FXML = "fxml/gameConfig.fxml";
    public static final String PLAYER_CONFIG_SCREEN = "playerConfig";
    public static final String PLAYER_CONFIG_FXML = "fxml/playerConfig.fxml";
    public static final String BLANK_SCREEN = "blankScreen";
    public static final String BLANK_SCREEN_FXML = "fxml/blankScreen.fxml";

    public Configuration configuration;

    @Override
    public void start(Stage stage) throws Exception {
        configuration = new Configuration();

        ScreenMaster main = new ScreenMaster(configuration);
        main.loadScreen(GAME_CONFIG_SCREEN, GAME_CONFIG_FXML);
        main.loadScreen(PLAYER_CONFIG_SCREEN, PLAYER_CONFIG_FXML);
        main.loadScreen(BLANK_SCREEN, BLANK_SCREEN_FXML);
      	main.displayScreen(GAME_CONFIG_SCREEN);

        Group root = new Group();
        root.getChildren().addAll(main);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
