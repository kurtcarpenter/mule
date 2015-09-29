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
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import team.screens.AScreen;
import team.MainApp;
import team.config.Player;

import team.screens.AScreen;

public class MainMapController extends AScreen {

    @FXML
    private GridPane mapGrid;
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

    private String[][] mapLayout = { {"P","P","M1","P","R","P","M3","P","P"},
        {"P","M1","P","P","R","P","P","P","M3"}, {"M3","P","P","P","Town","P","P","P","M1"},
        {"P","M2","P","P","R","P","M2","P","P"}, {"P","P","M2","P","R","P","P","P","M2"} };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        passButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parent.game.getMapManager().pass();
                setPlayerStuff();
            }
        });
        createMap();
    }

    public String getNewButtonColor() {
        Color c = Color.valueOf(parent.game.getTurnManager().getCurrentPlayer().getColor().toString());
        return String.format( "#%02X%02X%02X",
            (int)( c.getRed() * 255 ),
            (int)( c.getGreen() * 255 ),
            (int)( c.getBlue() * 255 ) );
    }

    public void setPlayerStuff() {
        turnLabel.setText("Turn " + parent.game.getTurnManager().getCurrentTurn());
        nameLabel.setText("Player " + parent.game.getTurnManager().getCurrentPlayer().getName());
        moneyLabel.setText("$" + parent.game.getTurnManager().getCurrentPlayer().getMoney());
        playerColor.setFill(Color.valueOf(parent.game.getTurnManager().getCurrentPlayer().getColor().toString()));
        // Change init time based on player attributes
        timerLabel.textProperty().bind(parent.game.getTimerManager().startTimer(50));
    }

    public void createMap() {
        for (int i = 0; i < mapLayout.length; i++) {
            for (int j = 0; j < mapLayout[i].length; j++) {
                //System.out.println("i: " + i + " j: " + j);
                // switch(mapLayout[i][j]) {
                //     case "P":
                //         //Make Plain
                //         mapGrid.add(new Button("P"), j, i);
                //         break;
                //     case "R":
                //         //Make River
                //         mapGrid.add(new Button("R"), j, i);
                //         break;
                //     case "T":
                //         mapGrid.add(townButton, j, i);
                //         break;
                //     case "M1":
                //         //Make Mountain1
                //         mapGrid.add(new Button("M1"), j, i);
                //         break;
                //     case "M2":
                //         //Make Mountain2
                //         mapGrid.add(new Button("M2"), j, i);
                //         break;
                //     case "M3":
                //         //Make Mountain3
                //         mapGrid.add(new Button("M3"), j, i);
                //         break;
                // }
                final String layoutString = mapLayout[i][j];
                Button newButton = new Button(mapLayout[i][j]);
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
