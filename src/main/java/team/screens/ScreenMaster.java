package team.screens;

import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import team.config.Configuration;
import team.config.GameSettings;
import team.Game;
import team.screens.instances.MainMapController;

import java.util.HashMap;

public class ScreenMaster extends StackPane {

    private HashMap<String, Node> screens = new HashMap<String, Node>();
    public Game game;
    public Configuration config;
    private IScreen mainMapController;

    public ScreenMaster(Game game) {
        this.game = game;
        this.config = game.getConfiguration();
    }

    /**
     * Displays a given Screen, looked up by name (key)
     * @param name Name of screen (key)
     * @return Success
     */
    public boolean displayScreen(String name) {
        if (name.equals("mainMapScreen")) {
            ((MainMapController) mainMapController).setPlayerStuff();
        }
        if (screens.get(name) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, screens.get(name));
            } else {
                getChildren().add(screens.get(name));
            }
            return true;
        } else {
            System.out.println("Attempted to display unloaded screen");
            return false;
        }
    }

    /**
     * Adds a Screen and Node pair into our holding structure.
     * Invoked by this::loadScreen()
     * @param name Name of the screen (key)
     * @param screen Root node of the screen
     */
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    /**
     * Loads a screen from an FXML file.
     * @param name Name of the screen (key)
     * @resources File location of the FXML file
     * @return Success
     */
    public boolean loadScreen(String name, String resource) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(resource));
             Parent node = (Parent) loader.load();
             IScreen screenController = ((IScreen) loader.getController());
             if (name.equals("mainMapScreen")) {
               mainMapController = screenController;
             }
             screenController.setParent(this);
             addScreen(name, node);
             return true;
         } catch (Exception e) {
             System.out.println("Load Screen exception:");
             e.printStackTrace();
             return false;
         }
    }

    /**
     * Removes a screen from the data structure, making it unavailable.
     * It must be re-added to be used again.
     * @param name Name of the screen to remove (key)
     * @return Success
     */
    public boolean removeScreen(String name) {
        return screens.remove(name) != null;
    }
}
