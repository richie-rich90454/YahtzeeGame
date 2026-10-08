public class DiceRollsHandler {
    private int[] perRoundRolls;
    public DiceRollsHandler(){
        perRoundRolls=new int[3];
    }
    public void rollAllDice(){
        perRoundRolls[0]=(int)(Math.random()*6)+1;
        perRoundRolls[1]=(int)(Math.random()*6)+1;
        perRoundRolls[2]=(int)(Math.random()*6)+1;
    }
    public int[] getPerRoundRolls(){
        return perRoundRolls;
    }
    public boolean rollDice(int diceIndex){
        if (diceIndex>=0&&diceIndex<=2){
            perRoundRolls[diceIndex]=(int)(Math.random()*6)+1;
            return true;
        }
        else{
            return false;
        }
    }
    public void clearDiceRolls(){
        perRoundRolls=new int[3];
    }
    public int getParticularDiceValue(int diceIndex){
        return perRoundRolls[diceIndex];
    }
}