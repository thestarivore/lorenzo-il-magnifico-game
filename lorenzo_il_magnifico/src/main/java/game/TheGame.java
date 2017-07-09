package game;

import com.sun.org.apache.regexp.internal.RE;
import controllers.GameFacadeController;
import controllers.Player;

import controllers.RemotePlayer;
import models.GameFacadeModel;
import game.network.client.ClientInterface;
import game.network.server.ServerInterface;
import models.Points;
import models.Resources;
import models.board.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class TheGame implements Serializable {
    /**
     * List of the players in this game
     */
    private List<RemotePlayer> players;

    /**
     * The Facade Model of the MVC
     */
    transient private GameFacadeModel         theModel;

    /**
     * The Facade Controller of the MVC
     */
    transient private GameFacadeController    theController;

    /**
     * List of the available colors used for the players
     */
    private ArrayList<COLORS> colorAvailable;

    /**
     * Boolean variable indicating if game is started
     * or still has to begin.
     */
    private boolean gameStarted;

    /**
     * The maximum number of players allowed to this game
     */
    private int playersAllowed;

    //Constants
    public static final int MINIMUM_PLAYERS_NUMBER = 2;
    public static final int MAXIMUM_PLAYERS_NUMBER = 4;
    public static final int MAXIMUM_COLORS_NUMBER = 4;

    /**
     * Number of periods in a game
     */
    public static final int PERIODS_PER_GAME = 3;
    public static final int FIRST_PERIOD    = 0;
    public static final int SECOND_PERIOD   = 1;
    public static final int THIRD_PERIOD    = 2;
    public static final int END_OF_GAME     = 3;

    /**
     * Player Indexes
     */
    public static final int FIRST_PLAYER    = 0;
    public static final int SECOND_PLAYER   = 1;
    public static final int THIRD_PLAYER    = 2;
    public static final int FORTH_PLAYER    = 3;

    /**
     * Enum - Possible TheGame's colors constants.
     */
    public enum COLORS{
        RED("RED"),
        BLUE("BLUE"),
        YELLOW("YELLOW"),
        GREEN ("GREEN"),
        ;

        String color;

        /**
         * Basic constructor
         */
        COLORS(String color) {
            this.color = color;
        }

        /**
         * Get color
         * @return String of the color
         */
        public String getColor(){
            return color;
        }
    }


    /**
     * Basic TheGame Constructor
     */
    public TheGame() {
        this.players = new ArrayList<RemotePlayer>();

        //Initialize colors available for players
        colorAvailable = new ArrayList<COLORS>();
        colorAvailable.add(COLORS.RED);
        colorAvailable.add(COLORS.BLUE);
        colorAvailable.add(COLORS.YELLOW);
        colorAvailable.add(COLORS.GREEN);

        //Game is still in standby until the players arrive
        gameStarted = false;
        playersAllowed = MAXIMUM_PLAYERS_NUMBER;

        //Initialize the Model and the Controller
        theModel        = new GameFacadeModel(getPlayersAllowed());
        theController   = new GameFacadeController(theModel, this);

        //Start the Game Timeout for players
        startGameTimeOut();
    }

    /**
     * Adds a new player to the player list
     * @param player
     */
    public void addPlayer(RemotePlayer player) {
        this.players.add(player);
    }

    /**
     * @param index
     * @return player at index
     */
    public RemotePlayer getPlayer(int index) {
       return this.players.get(index);
    }

    /**
     * Iterate through players and find the player with the passed id.
     * If none has the required id, return null
     * @param id
     * @return Player with passed id
     */
    public Player getPlayerById(int id){
        //Iterate Players
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getID() == id)
                return players.get(i);
        }

        //If nothing found return null
        return null;
    }

    /**
     * Iterate through players and find the player's turn in the game
     * If no player was found, return "-1"
     * @param player player whose turn we must find out
     * @return integer of player's turn, "-1" if no player was found
     */
    public int getPlayerTurnNumber(Player player){
        //Iterate Players
        for (int index = 0; index < players.size(); index++) {
            if(players.get(index).equals(player))
                return index;
        }

        //If nothing found return null
        return -1;
    }

    /**
     * Get the player with the turn number selected.
     * @param turnNumber player's turn number
     * @return Player instance
     */
    public Player getPlayerByTurnNumber(int turnNumber){
        if(turnNumber <= getNumberOfPlayers() - 1)
            return players.get(turnNumber);
        else
            return null;
    }

    /**
     * @return integer of the current number of players in this game
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Gets the instance of the Controller
     * @return GameFacadeController class instance
     */
    public GameFacadeController getTheController() {
        return theController;
    }

    /**
     * Execute the Controller's Automata, to manage periods, actions
     * and apply all the game's rules
     */
    public void executeControllerAutoma() {
        theController.executeControllerAutoma();
    }

    /**
     * Gets the instance of the Model
     * @return GameFacadeModel class instance
     */
    public GameFacadeModel getTheModel() {
        return theModel;
    }


    /**
     * Finds out whether the game is already full
     * @return boolean "true" if the game is full, "false" otherwise
     */
    public boolean isGameFull(){
        int currentPlayers = getNumberOfPlayers();
        if(currentPlayers >= playersAllowed)
            return true;
        else
            return false;
    }

    /**
     * Remove this color, as it's occupied by a player
     * @param color
     */
    public void removeColor(COLORS color) {
        colorAvailable.remove(color);
    }

    /**
     * Get the String List of the available colors
     * @return
     */
    public ArrayList<String> getAvailableColorsStrings(){
        ArrayList<String> colors = new ArrayList<String>();

        //Fill the list
        for(int i = 0; i<colorAvailable.size(); i++)
            colors.add(colorAvailable.get(i).getColor());

        return colors;
    }

    /**
     * Get the String List of the available colors
     * @return
     */
    public ArrayList<COLORS> getAvailableColors() {
        return colorAvailable;
    }

    /**
     * @return "true" if game has started and "false" otherwise
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Set if game has started or not
     * @param gameStarted boolean variable indicating if game has started
     */
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }


    private void startGameTimeOut() {
        //Create new timer
        Timer timer = new Timer();

        // Schedule a timer that ticks every 100ms, it's used as time base
        // for the server's automata(final state machine).
        timer.schedule( new TimerTask() {
            //Get timeout from the configuration instance
            int timeout = getTheModel().getGameConfig().getTimeOutGame(); //in ms
            boolean startTimeout = false;

            //Run Function
            public void run() {
                //If game isn't started yet, do the timeout thing
                if(!isGameStarted()) {
                    //Wait for 2 or more players, before starting the controller
                    if (getNumberOfPlayers() >= 2) {
                        startTimeout = true;

                        //Stops if we've already reached the maximum number of players
                        if(getNumberOfPlayers() == MAXIMUM_PLAYERS_NUMBER){
                            startGameWithCurrentPlayers();
                        }
                    }

                    //If timeout control is enabled
                    if (startTimeout) {
                        if (timeout > 0) {
                            timeout -= 100;
                        }
                        //Timeout finished
                        else {
                            startGameWithCurrentPlayers();
                        }
                    }
                }
                else{
                    timer.cancel();
                }
            }
        }, 0, 100);
    }

    /**
     * Starts this game with the current number uf players connected
     */
    private void startGameWithCurrentPlayers(){

        //Set game as officially started
        setGameStarted(true);

        //Set the current maximum allowed number of players
        setPlayersAllowed(getNumberOfPlayers());

        //Create new Board with correct number of players
        if (getPlayersAllowed() < MAXIMUM_PLAYERS_NUMBER) {
            theModel.getBoard().updateNumberOfPlayers(getPlayersAllowed());
        }

        /*int turnNumber;
        for (int i = 0; i < getPlayersAllowed(); i++) {
            turnNumber = getPlayer(i).getTurnOrder();
            getPlayer(i).setRes(new Resources(turnNumber));
        }*/

        //Set the first player
        getTheController().setPlayerInTurn(getPlayer(0));
    }

    /**
     * Get the maximum number of players allowed.
     * This has a fixed value at first, but can decrease if timeout occurs
     * during the players connection
     */
    public int getPlayersAllowed() {
        return playersAllowed;
    }

    /**
     * Set the maximum number of players allowed in this game
     * @param playersAllowed integer number of players
     */
    public void setPlayersAllowed(int playersAllowed) {
        this.playersAllowed = playersAllowed;
    }
}


