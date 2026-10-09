public class SimplifiedYahtzee {
    private int[] diceRollsCurrent;
    private int userScore;
    public SimplifiedYahtzee(){
        userScore=0;
        diceRollsCurrent=new int[3];
    }
    public int getuserScore(){
        return userScore;
    }
    public void setUserScore(int userScore){
        this.userScore=userScore;
    }
    public void rollAllDice(){
        diceRollsCurrent=new int[3];
        rollDice("0");
        rollDice("1");
        rollDice("2");
    }
    public boolean rollDice(String diceIndexString){
        int diceIndex=-1;
        //reference: https://stackoverflow.com/questions/35250163/what-happens-to-a-int-i-integer-parseintstring-when-there-are-no-integers-in
        try{
            diceIndex=Integer.parseInt(diceIndexString);
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
        System.out.print('\n');
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
}
