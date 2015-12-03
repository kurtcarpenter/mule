package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;

import team.screens.AScreen;
import team.MainApp;
import team.config.Player;
import team.Game.GameState;
import team.map.GameMap;

public class MainMapController extends AScreen {
  @FXML
  private GridPane mapGrid;
  @FXML
  private Pane titlePane;
  @FXML
  private Button passButton;
  @FXML
  private Label turnLabel;
  @FXML
  private Label nameLabel;
  @FXML
  private Label moneyLabel;
  @FXML
  private Rectangle playerColor;
  @FXML
  private Label timerLabel;
  @FXML
  private Label scoreLabel;

  private String[][] mapLayout;

  private Image plain;
  private Image river;
  private Image town;
  private Image m1;
  private Image m2;
  private Image m3;
  private Image desert;
  private Image plainMule;
  private Image riverMule;
  private Image m1Mule;
  private Image m2Mule;
  private Image m3Mule;
  private Image desertMule;

  private boolean createdMap = false;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    passButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (parent.game.getMapManager().pass()
            == parent.game.getTurnManager().getPlayers().size()) {
          titlePane.getChildren().remove(passButton);
        }
        setPlayerStuff();
      }
    });
    plain = new Image("graphics/plain.png");
    river = new Image("graphics/river.png");
    town = new Image("graphics/town.png");
    m1 = new Image("graphics/m1.png");
    m2 = new Image("graphics/m2.png");
    m3 = new Image("graphics/m3.png");
    desert = new Image("graphics/desert.png");

    plainMule = new Image("graphics/plainMule.png");
    riverMule = new Image("graphics/riverMule.png");
    m1Mule = new Image("graphics/m1Mule.png");
    m2Mule = new Image("graphics/m2Mule.png");
    m3Mule = new Image("graphics/m3Mule.png");
    desertMule = new Image("graphics/desertMule.png");
  }

  public void update() {
    mapLayout = parent.game.getMapManager().getGameMap().getMapLayout();
    if (parent.game.getTurnManager().getCurrentTurn() == 1 && !createdMap)
      createMap();
    turnLabel.setText("Turn " + parent.game.getTurnManager().getCurrentTurn());
    nameLabel.setText(parent.game.getTurnManager()
        .getCurrentPlayer().getName());
    moneyLabel.setText("$" + parent.game.getTurnManager().getCurrentPlayer()
        .getMoney());
    playerColor.setFill(Color.valueOf(
        parent.game.getTurnManager().getCurrentPlayer().getColor().toString()));
    // Change init time based on player attributes
    if (parent.game.getTurnManager().getGameState() != GameState.LAND_SELECT
        && parent.game.getTimerManager().isStart()) {
      timerLabel.textProperty().bind(parent.game.getTimerManager()
          .startTimer());
    }
    scoreLabel.setText("Score: " + parent.game.getTurnManager()
        .getCurrentPlayer().getScore());
    timerLabel.textProperty().bind(parent.game.getTimerManager()
        .getTimerBinding());
  }

  /**
   * Get the hex representation of the current player's color.
   *
   * @return hex representation of color.
   */
  public String getNewButtonColor() {
    Color col = Color.valueOf(parent.game.getTurnManager()
        .getCurrentPlayer().getColor().toString());
    return String.format("#%02X%02X%02X", (int) (col.getRed() * 255),
        (int) (col.getGreen() * 255), (int) (col.getBlue() * 255));
  }

  /**
   * Set colors for all map buttons.
   *
   * @param map The game map
   */
  public void setMapButtons(GameMap map) {
    for (Node node : mapGrid.getChildren()) {
      if (node instanceof javafx.scene.Group) {
        continue;
      }
      int rowIndex = GridPane.getRowIndex(node);
      int colIndex = GridPane.getColumnIndex(node);
      Player player = map.getTile(rowIndex, colIndex).getOwner();
      if (player != null) {
        Color col = Color.valueOf(player.getColor().toString());
        String hex = String.format("#%02X%02X%02X", (int) (col.getRed() * 255),
            (int) (col.getGreen() * 255), (int) (col.getBlue() * 255 ));
        ((Button)node).setStyle("-fx-font: 14 arial; -fx-base: " + hex + ";");
      }
    }
  }

  /**
   * Sets the various labels corresponding to players and the current turn.
   */
  public void setPlayerStuff() {
    turnLabel.setText("Turn " + parent.game.getTurnManager().getCurrentTurn());
    nameLabel.setText(parent.game.getTurnManager()
        .getCurrentPlayer().getName());
    moneyLabel.setText("$" + parent.game.getTurnManager().getCurrentPlayer()
        .getMoney());
    playerColor.setFill(Color.valueOf(
        parent.game.getTurnManager().getCurrentPlayer().getColor().toString()));
    // Change init time based on player attributes
    if (parent.game.getTurnManager().getGameState() != GameState.LAND_SELECT
        && parent.game.getTimerManager().isStart()) {
      timerLabel.textProperty().bind(parent.game.getTimerManager()
          .startTimer());
    }
    scoreLabel.setText("Score: " + parent.game.getTurnManager()
        .getCurrentPlayer().getScore());
    timerLabel.textProperty().bind(parent.game.getTimerManager()
        .getTimerBinding());
  }

  /**
   * Creates the map.
   */
  public void createMap() {
    createdMap = true;
    for (int i = 0; i < mapLayout.length; i++) {
      for (int j = 0; j < mapLayout[i].length; j++) {
        final String layoutString = mapLayout[i][j];
        Image image = plain;
        switch (mapLayout[i][j]) {
          case "P":
            image = plain;
            break;
          case "R":
            image = river;
            break;
          case "Town":
            image = town;
            break;
          case "M1":
            image = m1;
            break;
          case "M2":
            image = m2;
            break;
          case "M3":
            image = m3;
            break;
          case "D":
            image = desert;
            break;
          default:
            System.out.println("Defaulted");
            break;
        }
        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setFitHeight(40);
        iv.setFitWidth(40);
        Button newButton = new Button("", iv);
        GridPane.setHalignment(newButton, HPos.CENTER);
        newButton.setAlignment(Pos.CENTER);
        newButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            if (layoutString.equals("Town")) {
              parent.displayScreen(MainApp.TOWN_SCREEN);
            } else {
              int rowIndex = GridPane.getRowIndex(newButton);
              int colIndex = GridPane.getColumnIndex(newButton);
              String hex = getNewButtonColor();
              boolean isValidTurn = parent.game.getMapManager().process(rowIndex, colIndex);
              if (isValidTurn) {
                newButton.setStyle("-fx-font: 14 arial; -fx-base: " + hex + ";");
                setPlayerStuff();
                if (parent.game.getTurnManager().getGameState() != GameState.LAND_SELECT) {
                  Image image = new Image("graphics/plainMule.png");
                  switch (mapLayout[rowIndex][colIndex]) {
                    case "P":
                      image = plainMule;
                      break;
                    case "R":
                      image = riverMule;
                      break;
                    case "M1":
                      image = m1Mule;
                      break;
                    case "M2":
                      image = m2Mule;
                      break;
                    case "M3":
                      image = m3Mule;
                      break;
                    case "D":
                      image = desertMule;
                      break;
                    default:
                      System.out.println("Defaulted");
                      break;
                  }
                  ImageView iv = new ImageView();
                  iv.setImage(image);
                  iv.setFitHeight(40);
                  iv.setFitWidth(40);
                  newButton.setGraphic(iv);
                }
              }
            }
          }
        });
        mapGrid.add(newButton, j, i);
      }
    }
  }
}
