package team;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import team.screens.ScreenMaster;
import team.config.Configuration;

public class MainApp extends Application {

    public static final String GAME_CONFIG_SCREEN = "gameConfig";
    public static final String GAME_CONFIG_FXML = "fxml/gameConfig.fxml";
    public static final String PLAYER_CONFIG_SCREEN = "playerConfig";
    public static final String PLAYER_CONFIG_FXML = "fxml/playerConfig.fxml";
    public static final String MAINMAP_SCREEN = "mainMapScreen";
    public static final String MAINMAP_SCREEN_FXML = "fxml/mainMapScreen.fxml";
    public static final String TOWN_SCREEN = "townScreen";
    public static final String TOWN_SCREEN_FXML = "fxml/townScreen.fxml";

    public Game game;

    @Override
    public void start(Stage stage) throws Exception {
        Configuration c = new Configuration();
        game = new Game(c);
        ScreenMaster main = new ScreenMaster(game);

        main.loadScreen(GAME_CONFIG_SCREEN, GAME_CONFIG_FXML);
        main.loadScreen(PLAYER_CONFIG_SCREEN, PLAYER_CONFIG_FXML);
        main.loadScreen(MAINMAP_SCREEN, MAINMAP_SCREEN_FXML);
        main.loadScreen(TOWN_SCREEN, TOWN_SCREEN_FXML);
      	game.passScreenMaster(main);
        main.displayScreen(GAME_CONFIG_SCREEN);

        Group root = new Group();
        root.getChildren().addAll(main);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
