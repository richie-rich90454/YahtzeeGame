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
        SimplifiedYahtzee userPlayer=new SimplifiedYahtzee();
        for (int round=1;round<=6;round++){
            for (int playerNumber=0;playerNumber<playerCount;playerNumber++){
                players[playerNumber].playRound(userPlayer, input, round, playerNumber);
            }
        }
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The final scores are: ");

        for (int )
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The game ended, and your final score is "+userPlayer.getUserScore()+".");
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("Your highest scoring round is round "+userPlayer.getBestRound()+" with a score of "+userPlayer.getBestRoundScore()+" (note: if two or more rounds had the same score, the latest round is printed here).");
    }
}