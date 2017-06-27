package models.board;

import java.awt.*;
import java.util.Random;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Dice {
    private Color color;
    private int number;

    /**
     * Enum - Possible Dice's colors constants.
     * Actually the neuter dice does't exist, but there is one family
     * member who is neuter and has no color.
     */
    public enum COLORS{
        NEUTER("NEUTER"),
        WHITE("WHITE"),
        BLACK("BLACK"),
        ORANGE ("ORANGE"),
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
     * Basic Dice Constructor
     */
    public Dice() {
        this.color = null;
        this.number = 0;
    }

    public int rollDice(int min , int max){
        Random rand= new Random();
        this.number=rand.nextInt((max-min)+1)+min;
        return this.number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(int i) {
        switch(i) {
            case 0:
                this.color = Color.BLACK;
                break;
            case 1:
                this.color = Color.ORANGE;
                break;
            default:
                this.color = Color.WHITE;
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}