package controllers;

import game.TheGame;
import models.GameFacadeModel;
import models.cards.DevelopmentCard;
import models.cards.DevelopmentCardDeck;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class GameFacadeControllerTest {



    @Test
    public void managePlayerAction() throws Exception {
    }

    @Test
    public void fillTheTower() throws Exception {
    }

    @Test
    public void throwDice() throws Exception {
    }

  /*  @Test
    public void chooseAction() throws Exception {
        DevelopmentCardDeck developmentCardDeck = new DevelopmentCardDeck();
        DevelopmentCard card = new DevelopmentCard("MINT");
        developmentCardDeck.setCard(card);
        TheGame game  = new TheGame();
        Player player = new Player("test Player");
        GameFacadeModel gameModel = new GameFacadeModel(2);
        GameFacadeController gameController = new GameFacadeController(gameModel,game);
        assertTrue(gameController.chooseAction(player,"testMessage","tower"));
        }*/

    @Test
    public void performHarvestAction() throws Exception {
    }

}