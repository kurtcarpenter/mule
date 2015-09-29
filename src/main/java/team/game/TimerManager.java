package team.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.beans.binding.StringBinding;

public class TimerManager {
    private Timeline timeline;

    public TimerManager() {
        timeline = new Timeline();
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

}
