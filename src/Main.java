import java.util.ArrayList;
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        SimplifiedYahtzee.printCharacterByCharacter("Welcome to the simplified Yahtzee game.\nPlease enter the number of players you intend to have (at least 1; values smaller than 1 and non-integer values would be cast to 1): ");
        String playerCountString=input.nextLine().trim();
        int playerCount=1;
        try{
            playerCount=Integer.parseInt(playerCountString);
            if (playerCount<1){
                playerCount=1;
            }
        }
        catch(NumberFormatException exception){

        }
        SimplifiedYahtzee[] players=new SimplifiedYahtzee[playerCount+1];
        for(int i=1;i<=playerCount;i++){
            players[i]=new SimplifiedYahtzee();
        }
        for (int round=1;round<=6;round++){
            for (int playerNumber=1;playerNumber<=playerCount;playerNumber++){
                players[playerNumber].playRound(players[playerNumber], input, round, playerNumber);
            }
        }
        SimplifiedYahtzee.endGame(playerCount, players);
    }
}