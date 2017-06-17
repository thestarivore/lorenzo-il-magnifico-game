package game;


import controllers.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Paolo on 08/05/17.
 */
public class Lobby {
    private static ArrayList<TheGame> games;
    private String[] colorAvailable = {"RED", "BLUE", "YELLOW", "GREEN"};

    //Constants
    public static final int MAXIMUM_GAMES_NUMBER = 10;

    /**
     * Basic Constructor
     */
    public Lobby() {
        int numberOfPlayer = 4; //must be asked

        //Rooms Creation
        //Every Room is a TheGame instance
        //once a room works we can implement the multi-room system
        games = new ArrayList<TheGame>();
        games.add(new TheGame());

        TheGame firstGame = games.get(0);


    }


    /**
     * A new Client was accepted,
     * Manage this client as a new Player and add it to the current
     * game, or create a new game if there is no place in the current one.
     * @param player Player istance of the player to add
     */
    public void newPlayerArrived(Player player){
        //Get last game in the list, because the others should be full
        TheGame lastGame = games.get(games.size()-1);

        //If game is not full add the new player to this game, else create a new game
        if (lastGame.isGameFull() == false){
            lastGame.addPlayer(player);
        }
        else{
            games.add(new TheGame());
        }

    }


    public void removeColor(String color) {
        for (int i=0; i < 4; i++)
            if (color.equalsIgnoreCase(colorAvailable[i]))
                colorAvailable[i]= "-";
    }

    public String getColorAvailable(int i) {
        return colorAvailable[i];
    }



}
