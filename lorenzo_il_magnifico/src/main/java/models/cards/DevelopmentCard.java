package models.cards;


import controllers.Player;
import controllers.effects.ImmediateEffect;
import controllers.game_course.Period;
import controllers.effects.PermanentEffect;
import models.Points;
import models.Resources;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class DevelopmentCard implements Card {
    private String name;
    private Period period;
    private Resources cost;
    private Points pointsReq;
    private Points pointsCost;
    private ImmediateEffect immediateEffect;
    private PermanentEffect permanentEffect;

    public DevelopmentCard() {
        this.name  = "prova";
    }


    public Period getPeriod() {
        return period;
    }

    public Resources getCost() {
        return cost;
    }

    public ImmediateEffect getImmediateEffect() {
        return immediateEffect;
    }

    public PermanentEffect getPermanentEffect() {
        return permanentEffect;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public void setCost(Resources cost) {
        this.cost = cost;
    }

    public Points getPointsReq() {
        return pointsReq;
    }

    public void setPointsCost(Points points) {
        this.pointsReq = points;
    }

    public void setImmediateEffect(ImmediateEffect immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public Points getPointsCost() {
        return pointsCost;
    }

    public void setPermanentEffect(PermanentEffect permanentEffect) {
        this.permanentEffect = permanentEffect;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeRes(Player player) {
        player.getRes().removeResources(cost);
    }

    public void removePoints(Player player) {
        player.getPoints().removePoints(pointsCost);
    }
    @Override
    public String toString(){
        return "Card Name: " + this.name + "\n"+ "Card Cost: " + this.cost+"\n"+"Immediate Effect: "+this.immediateEffect+ "\n"+ "Permanent effect: " + this.permanentEffect;
    }
}
