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
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The final score and best single-round score for each player is: ");
        ArrayList<Integer> winners=new ArrayList<Integer>();
        int winnerScore=-1;
        boolean isTie=false;
        for (int i=1;i<=playerCount;i++){
            SimplifiedYahtzee.printCharacterByCharacterWithNewLine("Player "+i+": "+players[i].getUserScore()+" (best round is "+ players[i].getBestRound()+" with a score of "+players[i].getBestRoundScore()+"(note: if two or more rounds had the same score, the latest round is printed here)");
            if (players[i].getUserScore()>=winnerScore){
                if (!winners.isEmpty()){
                    isTie=true;
                }
                if (players[i].getUserScore()>winnerScore){
                    isTie=false;
                }
                winnerScore=players[i].getUserScore();
                if (isTie){
                    winners.add(i);
                }
                else{
                    winners.clear();
                    winners.add(i);
                }
            }
        }
        if (winners.size()>1){
            SimplifiedYahtzee.printCharacterByCharacter("The game is a tie between players ");
            for (int i=0;i<winners.size();i++){
                SimplifiedYahtzee.printCharacterByCharacter(winners.get(i)+"");
                if (i!=winners.size()-1){
                    SimplifiedYahtzee.printCharacterByCharacter(", ");
                }
            }
            SimplifiedYahtzee.printCharacterByCharacterWithNewLine(" all with a score of "+players[winners.get(0)].getUserScore());
        }
        else{
            SimplifiedYahtzee.printCharacterByCharacterWithNewLine("\nThe winner is player "+winners.get(0)+" with a score of "+players[winners.get(0)].getUserScore());
        }
    }
}