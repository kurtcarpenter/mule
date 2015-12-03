package team.screens.instances;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import team.screens.AScreen;
import team.MainApp;
import team.config.Player;
import team.game.containers.Resource;

public class StoreController extends AScreen {
  @FXML
  private Label foodStock;
  @FXML
  private Label foodPrice;
  @FXML
  private TextField foodQuantity;
  @FXML
  private Label foodInventory;
  @FXML
  private Label energyStock;
  @FXML
  private Label energyPrice;
  @FXML
  private TextField energyQuantity;
  @FXML
  private Label energyInventory;
  @FXML
  private Label smithoreStock;
  @FXML
  private Label smithorePrice;
  @FXML
  private TextField smithoreQuantity;
  @FXML
  private Label smithoreInventory;
  @FXML
  private Label crystiteStock;
  @FXML
  private Label crystitePrice;
  @FXML
  private TextField crystiteQuantity;
  @FXML
  private Label crystiteInventory;
  @FXML
  private Label muleStock;
  @FXML
  private Label mulePrice;
  @FXML
  private TextField muleQuantity;
  @FXML
  private Label muleInventory;
  @FXML
  private RadioButton noneButton;
  @FXML
  private RadioButton foodButton;
  @FXML
  private RadioButton energyButton;
  @FXML
  private RadioButton smithoreButton;
  @FXML
  private RadioButton crystiteButton;
  @FXML
  private RadioButton magicButton;
  @FXML
  private Button purchaseButton;
  @FXML
  private Button sellButton;
  @FXML
  private Button backButton;
  @FXML
  private Label moneyLabel;
  @FXML
  private Label totalLabel;

  private Resource muleType = Resource.FOOD;
  private int total = 0;
  private final int[] muleConfigPrices = {25, 50, 75, 100, 900};

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    foodQuantity.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(final ObservableValue<? extends String> observable,
          final String oldValue, final String newValue) {
        try {
          if (oldValue.equals("0")) {
            foodQuantity.setText(newValue.replaceAll("0",""));
          }
          if (newValue.equals("")) {
            foodQuantity.setText("0");
          }
          updateTotal();
        } catch (NumberFormatException nfe) {
          System.out.println(nfe.getMessage());
        }
      }
    });

    energyQuantity.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(final ObservableValue<? extends String> observable,
          final String oldValue, final String newValue) {
        try {
          if (oldValue.equals("0")) {
            energyQuantity.setText(newValue.replaceAll("0",""));
          }
          if (newValue.equals("")) {
            energyQuantity.setText("0");
          }
          updateTotal();
        } catch (NumberFormatException nfe) {
          System.out.println(nfe.getMessage());
        }
      }
    });

    smithoreQuantity.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(final ObservableValue<? extends String> observable,
          final String oldValue, final String newValue) {
        try {
          if (oldValue.equals("0")) {
            smithoreQuantity.setText(newValue.replaceAll("0",""));
          }
          if (newValue.equals("")) {
            smithoreQuantity.setText("0");
          }
          updateTotal();
        } catch (NumberFormatException nfe) {
          System.out.println(nfe.getMessage());
        }
      }
    });

    crystiteQuantity.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(final ObservableValue<? extends String> observable,
          final String oldValue, final String newValue) {
        try {
          if (oldValue.equals("0")) {
            crystiteQuantity.setText(newValue.replaceAll("0",""));
          }
          if (newValue.equals("")) {
            crystiteQuantity.setText("0");
          }
          updateTotal();
        } catch (NumberFormatException nfe) {
          System.out.println(nfe.getMessage());
        }
      }
    });

    noneButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        foodButton.setSelected(false);
        energyButton.setSelected(false);
        smithoreButton.setSelected(false);
        crystiteButton.setSelected(false);
        magicButton.setSelected(false);
        muleType = Resource.MULE;
        muleQuantity.setText("0");
        updateTotal();
      }
    });

    foodButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        noneButton.setSelected(false);
        energyButton.setSelected(false);
        smithoreButton.setSelected(false);
        crystiteButton.setSelected(false);
        magicButton.setSelected(false);
        muleType = Resource.FOOD;
        muleQuantity.setText("1");
        updateTotal();
      }
    });

    energyButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        noneButton.setSelected(false);
        foodButton.setSelected(false);
        smithoreButton.setSelected(false);
        crystiteButton.setSelected(false);
        magicButton.setSelected(false);
        muleType = Resource.ENERGY;
        muleQuantity.setText("1");
        updateTotal();
      }
    });

    smithoreButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        noneButton.setSelected(false);
        foodButton.setSelected(false);
        energyButton.setSelected(false);
        crystiteButton.setSelected(false);
        magicButton.setSelected(false);
        muleType = Resource.SMITHORE;
        muleQuantity.setText("1");
        updateTotal();
      }
    });

    crystiteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        noneButton.setSelected(false);
        foodButton.setSelected(false);
        energyButton.setSelected(false);
        smithoreButton.setSelected(false);
        magicButton.setSelected(false);
        muleType = Resource.CRYSTITE;
        muleQuantity.setText("1");
        updateTotal();
      }
    });

    magicButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        noneButton.setSelected(false);
        foodButton.setSelected(false);
        energyButton.setSelected(false);
        smithoreButton.setSelected(false);
        crystiteButton.setSelected(false);
        muleType = Resource.MAGIC;
        muleQuantity.setText("1");
        updateTotal();
      }
    });

    purchaseButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        try {
          parent.game.getStoreManager().buyResource(Resource.FOOD, Integer.parseInt(
              foodQuantity.getText()));

          parent.game.getStoreManager().buyResource(Resource.ENERGY, Integer.parseInt(
              energyQuantity.getText()));

          parent.game.getStoreManager().buyResource(Resource.SMITHORE, Integer.parseInt(
              smithoreQuantity.getText()));

          parent.game.getStoreManager().buyResource(Resource.CRYSTITE, Integer.parseInt(
              crystiteQuantity.getText()));
          
          if (muleType != Resource.MULE) {
            parent.game.getStoreManager().buyMule(Resource.MULE, muleType, Integer.parseInt(
                muleQuantity.getText()));
          }
        } catch (team.game.exceptions.StoreTransactionException e) {
          System.out.println(e.getMessage());
        } catch (team.game.exceptions.PlayerTransactionException e) {
          System.out.println(e.getMessage());
        }
        updateLabels();
      }
    });

    sellButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        try {
          parent.game.getStoreManager().sellResource(Resource.FOOD, Integer.parseInt(
              foodQuantity.getText()));

          parent.game.getStoreManager().sellResource(Resource.ENERGY, Integer.parseInt(
              energyQuantity.getText()));

          parent.game.getStoreManager().sellResource(Resource.SMITHORE, Integer.parseInt(
              smithoreQuantity.getText()));

          parent.game.getStoreManager().sellResource(Resource.CRYSTITE, Integer.parseInt(
              crystiteQuantity.getText()));

          if (muleType != Resource.MULE) {
            parent.game.getStoreManager().sellMule(muleType);
          }
        } catch (team.game.exceptions.PlayerTransactionException e) {
          System.out.println(e.getMessage());
        }
        updateLabels();
      }
    });

    backButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        parent.displayScreen(MainApp.TOWN_SCREEN);
      }
    });
  }

  private void updateTotal() {
    total = Integer.parseInt(foodPrice.getText().substring(1)) * Integer.parseInt(
        foodQuantity.getText()) + Integer.parseInt(energyPrice.getText().substring(1))
        * Integer.parseInt(energyQuantity.getText()) + Integer.parseInt(
        smithorePrice.getText().substring(1)) * Integer.parseInt(smithoreQuantity.getText())
        + Integer.parseInt(crystitePrice.getText().substring(1)) * Integer.parseInt(
        crystiteQuantity.getText());
    if (muleType != Resource.MULE) {
      total += (Integer.parseInt(mulePrice.getText().substring(1)) + muleConfigPrices[
          muleType.ordinal()]) * Integer.parseInt(muleQuantity.getText());
    }
    totalLabel.setText("$" + total);
  }

  public void update() {
    noneButton.setSelected(true);
    foodButton.setSelected(false);
    energyButton.setSelected(false);
    smithoreButton.setSelected(false);
    crystiteButton.setSelected(false);
    magicButton.setSelected(false);
    updateLabels();
  }

  /**
   * Called whenever the labels need to be updated.
   */
  public void updateLabels() {
    foodInventory.setText(""
        + parent.game.getTurnManager().getCurrentPlayer().getResourceQuantity(
        Resource.FOOD));
    foodStock.setText("" + parent.game.getStoreManager().getResourceStock(Resource.FOOD));

    energyInventory.setText(""
        + parent.game.getTurnManager().getCurrentPlayer().getResourceQuantity(
        Resource.ENERGY));
    energyStock.setText("" + parent.game.getStoreManager().getResourceStock(
        Resource.ENERGY));

    smithoreInventory.setText(""
        + parent.game.getTurnManager().getCurrentPlayer().getResourceQuantity(
        Resource.SMITHORE));
    smithoreStock.setText("" + parent.game.getStoreManager().getResourceStock(
        Resource.SMITHORE));

    crystiteInventory.setText(""
        + parent.game.getTurnManager().getCurrentPlayer().getResourceQuantity(
        Resource.CRYSTITE));
    crystiteStock.setText(""
        + parent.game.getStoreManager().getResourceStock(Resource.CRYSTITE));

    Resource muleType = parent.game.getTurnManager().getCurrentPlayer().getMule();
    if (muleType != null) {
      muleInventory.setText("" + parent.game.getTurnManager().getCurrentPlayer().getMule());
    } else {
      muleInventory.setText("None");
    }
    muleStock.setText("" + parent.game.getStoreManager().getResourceStock(Resource.MULE));
    moneyLabel.setText("$" + parent.game.getTurnManager().getCurrentPlayer().getMoney());
  }
}