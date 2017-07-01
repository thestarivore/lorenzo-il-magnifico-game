package models.data_persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;

import models.cards.*;

import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import models.cards.Deck;

/**
 * Created by cp18393 on 11/06/17.
 */
public class FileManagerImport implements Serializable{
    
    private LeaderCardDeck leaderCards;
    private DevelopmentCardDeck developmentCards;



    public static void main(String[] args) {
    //private ArrayList<DevelopmentCard> returnDeck(){
        ArrayList<DevelopmentCard> cards = new ArrayList<DevelopmentCard>();
        final Type dCardType = new TypeToken<ArrayList<DevelopmentCard>>(){}.getType();
        Gson gson = new Gson();
        String dCardFile = "/Users/cp18393/Desktop/DevCard.json";
        try (Reader reader = new FileReader(dCardFile)) {
            ArrayList<DevelopmentCard> list = gson.fromJson(reader, dCardType);
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
            cards=list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*for(int i = 0; i < cards.size(); i++) {
            System.out.print(cards.get(i));
        }*/
        //return cards;

    }


  /*  public LeaderCardDeck getLeaderCards() {
        String lCardFile = null;
        Gson gson = new Gson();
        try (Reader reader = new FileReader(lCardFile)) {
            LeaderCardDeck leaderCards = gson.fromJson(reader, new TypeToken<LeaderCardDeck>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderCards;

    public void callFileManager(){
        getLeaderCards();
    }
*/

}

