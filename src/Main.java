import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        SimplifiedYahtzee userPlayer=new SimplifiedYahtzee();
        for (int round=1;round<=6;round++){
            SimplifiedYahtzee.printCharacterByCharacterWithNewLine("This is round "+round +" of the simplified Yahtzee game.");
            SimplifiedYahtzee.printCharacterByCharacterWithNewLine("Your total score till this point is "+userPlayer.getuserScore());
            userPlayer.rollAllDice();
            SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The three dice roll results you have this round are "+userPlayer.getAllDice());
            SimplifiedYahtzee.printCharacterByCharacter("Do you want to reroll (enter yes or no; you have two chances of reroll if you enter yes)? ");
            String userRerollChoice=input.nextLine().toLowerCase();
            while (!(userRerollChoice.equals("yes")||userRerollChoice.equals("no"))){
                SimplifiedYahtzee.printCharacterByCharacter("Please enter either yes or no: ");
                userRerollChoice=input.nextLine().toLowerCase();
            }
            if (userRerollChoice.equals("yes")){
                for (int i=1;i<=2;i++){
                    SimplifiedYahtzee.printCharacterByCharacter("Please enter the index of the dice that you want to reroll (between 1 and 3 inclusive): ");
                    int userRerollIndex=input.nextInt()-1;
                    while (!userPlayer.rollDice(userRerollIndex)){
                        SimplifiedYahtzee.printCharacterByCharacter("Please enter an index between 1 and 3 inclusive: ");
                        userRerollIndex=input.nextInt()-1;
                    }
                    SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The new rolled value is: "+userPlayer.getParticularDiceValue(userRerollIndex));
                    input.nextLine();
                    if (i==1){
                        SimplifiedYahtzee.printCharacterByCharacter("Do you still want a reroll (enter yes or no)? ");
                        userRerollChoice=input.nextLine().toLowerCase();
                        while (!(userRerollChoice.equals("yes")||userRerollChoice.equals("no"))){
                            SimplifiedYahtzee.printCharacterByCharacter("Please enter either yes or no: ");
                            userRerollChoice=input.nextLine().toLowerCase();
                        }
                        if (userRerollChoice.equals("no")){
                            break;
                        }
                    }
                }
            }
            SimplifiedYahtzee.printCharacterByCharacterWithNewLine(userPlayer.addScoresForRound()+"\n\n\n");
        }
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The game ended, and your final score is "+userPlayer.getuserScore());
    }
}