package controllers.game_course;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Period {
    private int numberOfPeriod;
    private Round round;

    public Period() {
        this.numberOfPeriod = 1;
        this.round = new Round();
    }

    public int getNumberOfPeriod() {
        return this.numberOfPeriod;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @Override
    public String toString(){
        return "" + this.numberOfPeriod;
    }
}
