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

import team.screens.AScreen;

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

    private String[][] mapLayout = { {"P","P","M1","P","R","P","M3","P","P"},
        {"P","M1","P","P","R","P","P","P","M3"}, {"M3","P","P","P","Town","P","P","P","M1"},
        {"P","M2","P","P","R","P","M2","P","P"}, {"P","P","M2","P","R","P","P","P","M2"} };

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
        createMap();
        //timerLabel.textProperty().bind(parent.game.getTimerManager().getTimerBinding());
    }

    public String getNewButtonColor() {
        Color c = Color.valueOf(parent.game.getTurnManager().getCurrentPlayer().getColor().toString());
        return String.format( "#%02X%02X%02X",
            (int)( c.getRed() * 255 ),
            (int)( c.getGreen() * 255 ),
            (int)( c.getBlue() * 255 ) );
    }

    public void setMapButtons(GameMap map) {
        for (Node node : mapGrid.getChildren()) {
            if (node instanceof javafx.scene.Group)
                continue;
            int i = GridPane.getRowIndex(node);
            int j = GridPane.getColumnIndex(node);
            Player p = map.getTile(i, j).getOwner();
            if (p != null) {
                Color c = Color.valueOf(p.getColor().toString());
                String hex = String.format( "#%02X%02X%02X",
                    (int)( c.getRed() * 255 ),
                    (int)( c.getGreen() * 255 ),
                    (int)( c.getBlue() * 255 ) );
                ((Button)node).setStyle("-fx-font: 14 arial; -fx-base: " + hex + ";");
            }
        }
    }

    public void setPlayerStuff() {
        turnLabel.setText("Turn " + parent.game.getTurnManager().getCurrentTurn());
        nameLabel.setText(parent.game.getTurnManager().getCurrentPlayer().getName());
        moneyLabel.setText("$" + parent.game.getTurnManager().getCurrentPlayer().getMoney());
        playerColor.setFill(Color.valueOf(parent.game.getTurnManager().getCurrentPlayer().getColor().toString()));
        // Change init time based on player attributes
        if (parent.game.getTurnManager().getGameState() != GameState.LAND_SELECT
                && parent.game.getTimerManager().isStart()) {
            timerLabel.textProperty().bind(parent.game.getTimerManager().startTimer());
        }
        scoreLabel.setText("Score " + parent.game.getTurnManager().getCurrentPlayer().getScore());
        timerLabel.textProperty().bind(parent.game.getTimerManager().getTimerBinding());
    }

    public void createMap() {
        for (int i = 0; i < mapLayout.length; i++) {
            for (int j = 0; j < mapLayout[i].length; j++) {
                final String layoutString = mapLayout[i][j];
                Image image = new Image("graphics/test.png");
                switch(mapLayout[i][j]) {
                    case "P":
                        image = new Image("graphics/plain.png");
                        break;
                    case "R":
                        image = new Image("graphics/river.png");
                        break;
                    case "Town":
                        image = new Image("graphics/town.png");
                        break;
                    case "M1":
                        image = new Image("graphics/m1.png");
                        break;
                    case "M2":
                        image = new Image("graphics/m2.png");
                        break;
                    case "M3":
                        image = new Image("graphics/m3.png");
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
                        if (layoutString.equals("Town"))
                            parent.displayScreen(MainApp.TOWN_SCREEN);
                        else {
                            int x = GridPane.getRowIndex(newButton);
                            int y = GridPane.getColumnIndex(newButton);
                            //System.out.println("x: " + x + " y: " + j);
                            String hex = getNewButtonColor();
                            boolean isValidTurn = parent.game.getMapManager().process(x, y);
                            if (isValidTurn) {
                                newButton.setStyle("-fx-font: 14 arial; -fx-base: " + hex + ";");
                                setPlayerStuff();
                            }
                        }
                    }
                });
                mapGrid.add(newButton, j, i);
            }
        }
    }
}
