package team.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import team.config.Player;
import team.screens.ScreenMaster;
import team.screens.instances.MainMapController;

public class TimerManager {
    private Timeline timeline;
    private TurnManager turnManager;
    private ScreenMaster screenMaster;
    IntegerProperty timeSeconds;

    public TimerManager(TurnManager turnManager) {
        this.turnManager = turnManager;
        timeline = new Timeline();
        timeline.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                resetTimer();
                //System.out.println("Timer Finished");
            }
        });
        timeSeconds = new SimpleIntegerProperty(50);
    }

    public StringBinding startTimer() {
        int food = turnManager.getCurrentPlayer().getFood();
        // Perform checks
        int turn = turnManager.getCurrentTurn();
        int startTime = 50;
        if (food == 0) {
            startTime = 5;
        } else {
            if ((turn <= 4 && food < 3))
                startTime = 30;
            else if ((turn <= 8 && food < 4))
                startTime = 30;
            else if ((turn <= 12 && food < 5))
                startTime = 30;
        }
        return startTimer(startTime);
    }

    private StringBinding startTimer(final int STARTTIME) {
        timeSeconds.set(STARTTIME);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        return timeSeconds.asString();
    }

    public void resetTimer() {
        // Next step
        turnManager.advanceStep();
        // Get MainMap view to change
        screenMaster.displayScreen("mainMapScreen");
    }

    public void stopTimer() {
        timeline.stop();
    }

    public int getTime() {
        return timeSeconds.getValue();
    }

    public void passScreenMaster(ScreenMaster screenMaster) {
        this.screenMaster = screenMaster;
    }

    public StringBinding getTimerBinding() {
      return timeSeconds.asString();
    }

}
