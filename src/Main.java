import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        SimplifiedYahtzee userPlayer=new SimplifiedYahtzee();
        for (int round=1;round<=6;round++){
            userPlayer.playRound(userPlayer, input, round);
        }
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The game ended, and your final score is "+userPlayer.getUserScore()+".");
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("Your highest scoring round is round "+userPlayer.getBestRound()+" with a score of "+userPlayer.getBestRoundScore()+" (note: if two or rounds had the same score, the latest round is printed here).");
    }
}