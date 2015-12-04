package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.PathTransition;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.animation.Timeline;
import javafx.util.Duration;

import team.screens.AScreen;
import team.Game;

public class GameOverController extends AScreen {

  @FXML
  private ImageView mule;

  @FXML
  private Button exitButton;

  /**
   * Initialize method called to set the appropriate responses of the items on the screen.
   * 
   * @param url the URL
   * @param rb the ResourceBundle
   */
  @FXML
  public void initialize(URL url, ResourceBundle rb) {
    exitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        System.exit(0);
      }
    });

    Path path = new Path();

    MoveTo move = new MoveTo();
    move.setX(75);
    move.setY(25);

    ArcTo arc1 = new ArcTo();
    arc1.setX(75);
    arc1.setY(75);
    arc1.setRadiusX(25);
    arc1.setRadiusY(25);

    ArcTo arc2 = new ArcTo();
    arc2.setX(75);
    arc2.setY(25);
    arc2.setRadiusX(25);
    arc2.setRadiusY(25);

    path.getElements().add(move);
    path.getElements().add(arc1);
    path.getElements().add(arc2);

    PathTransition pt = new PathTransition(Duration.millis(1000), path, mule);
    pt.setCycleCount(Timeline.INDEFINITE);
    pt.play();
  }
}