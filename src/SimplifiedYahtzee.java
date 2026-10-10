import java.util.ArrayList;
import java.util.Scanner;

public class SimplifiedYahtzee {
    private int[] diceRollsCurrent;
    private int userScore;
    private int bestRound;
    private int bestRoundScore;
    public SimplifiedYahtzee(){
        userScore=0;
        diceRollsCurrent=new int[3];
        bestRound=1;
        bestRoundScore=-1;
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
    public int getBestRoundScore(){
        return bestRoundScore;
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
    //starting from 1 and ending at 3
    public boolean rollDice(String diceIndexString){
        int diceIndex=-1;
        //reference: https://stackoverflow.com/questions/35250163/what-happens-to-a-int-i-integer-parseintstring-when-there-are-no-integers-in
        try{
            diceIndex=Integer.parseInt(diceIndexString);
        }
        catch(NumberFormatException exception){
            return false;
        }
        return rollDice(diceIndex);
    }
    public String getAllDice(){
        return (diceRollsCurrent[0]+", "+diceRollsCurrent[1]+", "+diceRollsCurrent[2]);
    }
    public String addScoresForRound(int round){
        if (diceRollsCurrent[0]==diceRollsCurrent[1]&&diceRollsCurrent[1]==diceRollsCurrent[2]){
            int currentRoundScore=diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2]+20;
            setUserScore(userScore+diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2]+20);
            if (currentRoundScore>=bestRoundScore){
                bestRound=round;
                bestRoundScore=currentRoundScore;
            }
            return "All rolls are the same value, so you earn a score of "+(diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2]+20)+" (the sum of all dice values and a 20 bonus added on top of it).";
        }
        else if (diceRollsCurrent[0]==diceRollsCurrent[1]||diceRollsCurrent[1]==diceRollsCurrent[2]||diceRollsCurrent[0]==diceRollsCurrent[2]){
            int currentRoundScore=diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2];
            setUserScore(userScore+diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2]);
            if (currentRoundScore>=bestRoundScore){
                bestRound=round;
                bestRoundScore=currentRoundScore;
            }
            return "Two rolls are the same value, so you earn a score of "+(diceRollsCurrent[0]+diceRollsCurrent[1]+diceRollsCurrent[2])+" (the sum of all dice values).";
        }
        else{
            int currentRoundScore=Math.max(Math.max(diceRollsCurrent[0],diceRollsCurrent[1]), diceRollsCurrent[2]);
            setUserScore(userScore+Math.max(Math.max(diceRollsCurrent[0],diceRollsCurrent[1]), diceRollsCurrent[2]));
            if (currentRoundScore>=bestRoundScore){
                bestRound=round;
                bestRoundScore=currentRoundScore;
            }
            return "No dice match, so you earn a score of "+(Math.max(Math.max(diceRollsCurrent[0],diceRollsCurrent[1]), diceRollsCurrent[2]))+" (the maximum value of the three dice).";
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
    public void playRound(SimplifiedYahtzee userPlayer, Scanner input, int round, int playerNumber){
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("This is round "+round +" of the simplified Yahtzee game for player "+playerNumber+".");
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("Your total score till this point is "+userPlayer.getUserScore());
        userPlayer.rollAllDice();
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The three dice roll results you have this round are "+userPlayer.getAllDice());
        SimplifiedYahtzee.printCharacterByCharacter("Do you want to re-roll (enter yes or no; you have two chances of re-roll if you enter yes)? ");
        String userRerollChoice=input.nextLine().toLowerCase().trim();
        while (!(userRerollChoice.equals("yes")||userRerollChoice.equals("no"))){
            SimplifiedYahtzee.printCharacterByCharacter("Please enter either yes or no: ");
            userRerollChoice=input.nextLine().toLowerCase().trim();
        }
        if (userRerollChoice.equals("yes")){
            for (int i=1;i<=2;i++){
                SimplifiedYahtzee.printCharacterByCharacter("Please enter the index of the dice that you want to re-roll (between 1 and 3 inclusive): ");
                String userRerollIndex=input.nextLine().toLowerCase().trim();
                while (!userPlayer.rollDice(userRerollIndex)){
                    SimplifiedYahtzee.printCharacterByCharacter("Please enter a valid index between 1 and 3 inclusive: ");
                    userRerollIndex=input.nextLine().toLowerCase().trim();
                }
                SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The new rolled value is: "+userPlayer.getParticularDiceValue(Integer.parseInt(userRerollIndex)-1));//since it would definitely be an integer by this point that is valid there is no risk of runtime error here
                SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The three dice roll results you have this round are "+userPlayer.getAllDice());
                if (i==1){
                    SimplifiedYahtzee.printCharacterByCharacter("Do you still want a re-roll (enter yes or no)? ");
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
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine(userPlayer.addScoresForRound(round)+"\n\n\n");
    }
    public static void endGame(int playerCount, SimplifiedYahtzee[] players){
        SimplifiedYahtzee.printCharacterByCharacterWithNewLine("The final score and best single-round score for each player is: ");
        ArrayList<Integer> winners=new ArrayList<Integer>();
        int winnerScore=-1;
        boolean isTie=false;
        for (int i=1;i<=playerCount;i++){
            SimplifiedYahtzee.printCharacterByCharacterWithNewLine("Player "+i+": "+players[i].getUserScore()+" (best round is "+ players[i].getBestRound()+" with a score of "+players[i].getBestRoundScore()+" (note: if two or more rounds had the same score, the latest round is printed here)");
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
