package models;

import models.board.Board;


/**
 * Created by Eduard Chirica on 5/20/17.
 *
 * This is the Model
 * from the MVC pattern(Model View Controller)
 *
 * The Model performs all the calculations needed and that is it.
 * It doesn't know the View exists
 */
public class GameFacadeModel {
    private Board board;


    public GameFacadeModel (int numberOfPlayer) {
        this.board = new Board(numberOfPlayer);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }



}
