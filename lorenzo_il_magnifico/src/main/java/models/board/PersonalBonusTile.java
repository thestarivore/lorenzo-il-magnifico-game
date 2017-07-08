package models.board;

import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class PersonalBonusTile implements Serializable {

    private Resources harvestPersonalResources;
    private Points harvestPersonalPoints;
    private Resources productionPersonalResources;
    private Points productionPersonalPoints;

    public PersonalBonusTile(){
        this.harvestPersonalPoints = new Points();
        this.harvestPersonalResources = new Resources();
        this.productionPersonalPoints = new Points();
        this.productionPersonalResources = new Resources();
    }
}
