public class GamePlayer {
    private DiceRollsHandler diceRollsHandler;
    private int userScore;
    public GamePlayer(){
        userScore=0;
        diceRollsHandler=new DiceRollsHandler();
    }
    public int getuserScore(){
        return userScore;
    }
    public void setUserScore(int userScore){
        this.userScore=userScore;
    }
    public DiceRollsHandler getDiceRollsHandler() {
        return diceRollsHandler;
    }
    public void rollAllDice(){
        diceRollsHandler.clearDiceRolls();
        diceRollsHandler.rollAllDice();
    }
    public String getAllDice(){
        return (diceRollsHandler.getPerRoundRolls())[0]+", "+(diceRollsHandler.getPerRoundRolls())[1]+", "+(diceRollsHandler.getPerRoundRolls())[2];
    }
    public boolean rollDice(int diceIndex){
        return diceRollsHandler.rollDice(diceIndex);
    }
    public String addScoresForRound(){
        int[] diceRolls=diceRollsHandler.getPerRoundRolls();
        if (diceRolls[0]==diceRolls[1]&&diceRolls[1]==diceRolls[2]){
            setUserScore(userScore+diceRolls[0]+diceRolls[1]+diceRolls[2]+20);
            return "All rolls are the same value, so you earn a score of "+(diceRolls[0]+diceRolls[1]+diceRolls[2]+20)+" (the sum of all dice values and a 20 bonus added on top of it).";
        }
        else if (diceRolls[0]==diceRolls[1]||diceRolls[1]==diceRolls[2]||diceRolls[0]==diceRolls[2]){
            setUserScore(userScore+diceRolls[0]+diceRolls[1]+diceRolls[2]);
            return "Two rolls are the same value, so you earn a score of "+(diceRolls[0]+diceRolls[1]+diceRolls[2])+" (the sum of all dice values).";
        }
        else{
            setUserScore(userScore+Math.max(Math.max(diceRolls[0],diceRolls[1]), diceRolls[2]));
            return "No dices match, so you earn a score of "+(Math.max(Math.max(diceRolls[0],diceRolls[1]), diceRolls[2]))+" (the maximum value of the three dices).";
        }
    }
    public int getParticularDiceValue(int diceIndex){
        return diceRollsHandler.getParticularDiceValue(diceIndex);
    }
}
