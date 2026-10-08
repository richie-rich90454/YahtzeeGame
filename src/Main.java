import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        GamePlayer userPlayer=new GamePlayer();
        for (int round=1;round<=6;round++){
            System.out.println("This is round "+round +" of the simplified Yahtzee game.");
            System.out.println("Your total score till this point is "+userPlayer.getuserScore());
            userPlayer.rollAllDice();
            System.out.println("The three dice roll results you have this round are "+userPlayer.getAllDice());
            System.out.print("Do you want to reroll (enter yes or no; you have two chances of reroll if you enter yes)? ");
            String userRerollChoice=input.nextLine().toLowerCase();
            while (!(userRerollChoice.equals("yes")||userRerollChoice.equals("no"))){
                System.out.print("Please enter either yes or no: ");
            }
            if (userRerollChoice.equals("yes")){
                for (int i=1;i<=2;i++){
                    System.out.print("Please enter the index of the dice that you want to reroll (between 1 and 3 inclusive): ");
                    int userRerollIndex=input.nextInt()-1;
                    while (!userPlayer.rollDice(userRerollIndex)){
                        System.out.print("Please enter an index between 1 and 3 inclusive: ");
                        userRerollIndex=input.nextInt()-1;
                    }
                    System.out.println("The new rolled value is: "+userPlayer.getParticularDiceValue(userRerollIndex));
                    input.nextLine();
                    if (i==1){
                        System.out.print("Do you still want a reroll (enter yes or no)? ");
                        userRerollChoice=input.nextLine().toLowerCase();
                        while (!(userRerollChoice.equals("yes")||userRerollChoice.equals("no"))){
                            System.out.print("Please enter either yes or no: ");
                        }
                        if (userRerollChoice.equals("no")){
                            break;
                        }
                    }
                }
            }
            System.out.println(userPlayer.addScoresForRound()+"\n\n\n");
        }
        System.out.println("The game ended, and your final score is "+userPlayer.getuserScore());
    }
}