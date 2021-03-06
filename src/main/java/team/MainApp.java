package team;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import team.screens.ScreenMaster;
import team.config.Configuration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class MainApp extends Application {
  public static final String GAME_CONFIG_SCREEN = "gameConfig";
  public static final String GAME_CONFIG_FXML = "fxml/gameConfig.fxml";
  public static final String PLAYER_CONFIG_SCREEN = "playerConfig";
  public static final String PLAYER_CONFIG_FXML = "fxml/playerConfig.fxml";
  public static final String MAINMAP_SCREEN = "mainMapScreen";
  public static final String MAINMAP_SCREEN_FXML = "fxml/mainMap.fxml";
  public static final String TOWN_SCREEN = "townScreen";
  public static final String TOWN_SCREEN_FXML = "fxml/town.fxml";
  public static final String PUB_SCREEN = "pub";
  public static final String PUB_SCREEN_FXML = "fxml/pub.fxml";
  public static final String STORE_SCREEN = "store";
  public static final String STORE_SCREEN_FXML = "fxml/store.fxml";
  public Game game;

  @Override
  public void start(Stage stage) throws Exception {
    Configuration config = new Configuration();
    game = new Game(config);
    ScreenMaster main = new ScreenMaster(game);
    game.passScreenMaster(main);

    main.loadScreen(GAME_CONFIG_SCREEN, GAME_CONFIG_FXML);
    main.loadScreen(PLAYER_CONFIG_SCREEN, PLAYER_CONFIG_FXML);
    main.loadScreen(MAINMAP_SCREEN, MAINMAP_SCREEN_FXML);
    main.loadScreen(TOWN_SCREEN, TOWN_SCREEN_FXML);
    main.loadScreen(STORE_SCREEN, STORE_SCREEN_FXML);
    main.loadScreen(PUB_SCREEN, PUB_SCREEN_FXML);
    main.displayScreen(GAME_CONFIG_SCREEN);

    final URL resource = getClass().getClassLoader().getResource("music/themeSong.mp3");
    final Media media = new Media(resource.toString());
    final MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

    Group root = new Group();
    root.getChildren().addAll(main);
    Scene scene = new Scene(root); // 850, 400
    stage.setScene(scene);
    stage.show();
  }
}