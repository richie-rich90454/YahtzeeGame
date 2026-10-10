import java.util.Scanner;

public class SimplifiedYahtzee {
    private int[] diceRollsCurrent;
    private int userScore;
    private int bestRound;
    public SimplifiedYahtzee(){
        userScore=0;
        diceRollsCurrent=new int[3];
        bestRound=1;
    }
    public int getUserScore(){
        return userScore;
    }
    public void setUserScore(int userScore){
        this.userScore=userScore;
    }
    public int getBestRound(){
        return bestRound;
    }
    public void rollAllDice(){
        diceRollsCurrent=new int[3];
        rollDice(1);
        rollDice(2);
        rollDice(3);
    }
    //starting from 1 and ending at 3
    public boolean rollDice(int diceIndex){
        diceIndex--;
        if (diceIndex>=0&&diceIndex<=2){
            diceRollsCurrent[diceIndex]=(int)(Math.random()*6)+1;
            return true;
        }
        else{
            return false;
        }
    }
    //starting from 1 and ending at three
    public boolean rollDice(String diceIndexString){
        int diceIndex=-1;
        //reference: https://stackoverflow.com/questions/35250163/what-happens-to-a-int-i-integer-parseintstring-when-there-are-no-integers-in
        try{
            diceIndex=Integer.parseInt(diceIndexString)-1;
        }
        catch(NumberFormatException exception){
            return false;
        }
        if (diceIndex>=0&&diceIndex<=2){
            diceRollsCurrent[diceIndex]=(int)(Math.random()*6)+1;
            return true;
        }
        else{
            return false;
        }
    }
    public String getAllDice(){
        return (diceRollsCurrent[0]+", "+diceRollsCurrent[1]+", "+diceRollsCurrent[2]);
    }
    public String addScoresForRound(){
        if (diceRollsCurrent[0]==diceRollsCurrent[1]&&diceRollsCurrent[1]==diceRollsCurrent[2]){
            setUserScore(userScore+diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2]+20);
            return "All rolls are the same value, so you earn a score of "+(diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2]+20)+" (the sum of all dice values and a 20 bonus added on top of it).";
        }
        else if (diceRollsCurrent[0]==diceRollsCurrent[1]||diceRollsCurrent[1]==diceRollsCurrent[2]||diceRollsCurrent[0]==diceRollsCurrent[2]){
            setUserScore(userScore+diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2]);
            return "Two rolls are the same value, so you earn a score of "+(diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2])+" (the sum of all dice values).";
        }
        else{
            setUserScore(userScore+Math.max(Math.max(diceRollsCurrent[0],diceRollsCurrent[1]), diceRollsCurrent[2]));
            return "No dices match, so you earn a score of "+(Math.max(Math.max(diceRollsCurrent[0],diceRollsCurrent[1]), diceRollsCurrent[2]))+" (the maximum value of the three dices).";
        }
    }
    public int getParticularDiceValue(int diceIndex){
        return diceRollsCurrent[diceIndex];
    }
    public static void printCharacterByCharacterWithNewLine(String characters){
        printCharacterByCharacter(characters);
        System.out.println();
    }
    //reference: https://stackoverflow.com/questions/33019295/want-to-print-text-char-by-char-for-my-textbased-game-but-it-prints-out-the-who
    public static void printCharacterByCharacter(String characters){
        for (char currentCharacter: characters.toCharArray()){
            System.out.print(currentCharacter);
            System.out.flush();
            try{
                Thread.sleep(10);
            }
            catch (InterruptedException exception){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    public void playRound(SimplifiedYahtzee userPlayer, Scanner input, int round){
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("This is round "+round +" of the simplified Yahtzee game.");
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("Your total score till this point is "+userPlayer.getUserScore());
        userPlayer.rollAllDice();
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The three dice roll results you have this round are "+userPlayer.getAllDice());
        SimplifiedYahtzee.printCharacterByCharacter("Do you want to reroll (enter yes or no; you have two chances of reroll if you enter yes)? ");
        String userRerollChoice=input.nextLine().toLowerCase().trim();
        while (!(userRerollChoice.equals("yes")||userRerollChoice.equals("no"))){
            SimplifiedYahtzee.printCharacterByCharacter("Please enter either yes or no: ");
            userRerollChoice=input.nextLine().toLowerCase().trim();
        }
        if (userRerollChoice.equals("yes")){
            for (int i=1;i<=2;i++){
                SimplifiedYahtzee.printCharacterByCharacter("Please enter the index of the dice that you want to reroll (between 1 and 3 inclusive): ");
                String userRerollIndex=input.nextLine().toLowerCase().trim();
                while (!userPlayer.rollDice(userRerollIndex)){
                    SimplifiedYahtzee.printCharacterByCharacter("Please enter a valid index between 1 and 3 inclusive: ");
                    userRerollIndex=input.nextLine().toLowerCase().trim();
                }
                SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The new rolled value is: "+userPlayer.getParticularDiceValue(Integer.parseInt(userRerollIndex)-1));//since it would definitely be an integer by this point that is valid there is no risk of runtime error here
                SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The three dice roll results you have this round are "+userPlayer.getAllDice());
                if (i==1){
                    SimplifiedYahtzee.printCharacterByCharacter("Do you still want a reroll (enter yes or no)? ");
                    userRerollChoice=input.nextLine().toLowerCase().trim();
                    while (!(userRerollChoice.equals("yes")||userRerollChoice.equals("no"))){
                        SimplifiedYahtzee.printCharacterByCharacter("Please enter either yes or no: ");
                        userRerollChoice=input.nextLine().toLowerCase().trim();
                    }
                    if (userRerollChoice.equals("no")){
                        break;
                    }
                }
            }
        }
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine(userPlayer.addScoresForRound()+"\n\n\n");
    }
}
