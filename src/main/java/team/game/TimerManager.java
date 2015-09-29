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

public class TimerManager {
    private Timeline timeline;
    private TurnManager turnManager;

    public TimerManager(TurnManager turnManager) {
        this.turnManager = turnManager;
        timeline = new Timeline();
        timeline.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                // Next step
                System.out.println("Finished");
            }
        });
    }

    public StringBinding startTimer(int STARTTIME) {
        IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
        timeSeconds.set(STARTTIME);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME+1),
                new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        return timeSeconds.asString();
    }

    public void stopTimer() {
        timeline.stop();
    }

}
