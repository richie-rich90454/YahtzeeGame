import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        SimplifiedYahtzee userPlayer=new SimplifiedYahtzee();
        for (int round=1;round<=6;round++){
            userPlayer.playRound(userPlayer, input, round);
        }
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The game ended, and your final score is "+userPlayer.getUserScore());
    }
}