/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score4.game;

/**
 *
 * @author Asif
 */
import java.util.Arrays;

public class Board {
    private final int myLength;
    private final Peg [][] myGrid;
    
    
    
    @SuppressWarnings("empty-statement")
    public Board (int length){
        myLength = length;
        myGrid = new Peg [myLength][myLength];
        
        for (int i = 0; i < myLength; i++){
            //Arrays.fill(myGrid[i], new Peg(myLength));
           for (int j = 0; j < myLength; j++)
               myGrid[i][j] = new Peg(myLength);
        }
        
        for (int i = 0; i < myLength; i++)
            for (int j = 0; j < myLength; j++)
               myGrid[i][j].addLocation(i, j); ;
    }
    
    public void addBead (Location location, Color color){
        for (int i = 0; i < myLength; i++)
            for (int j = 0; j < myLength; j++)
                if (myGrid[i][j].getLocation().equals(location)){
                    myGrid[i][j].addBead(color);
                    return;
                } 
        //Throw exception for incorrect location
    }
    
    public int getMyLength(){
        return myLength;
    }
    
    public Peg getPeg(Location location){
        return myGrid[location.getRowNumber()][location.getColumn()];
    }
    
    public Peg getPeg(int row, int column){
       return myGrid[row][column]; 
    }
    
    public void displayBoard(){
        for (int i = 0; i < myLength; i++){
            for (int j = 0; j < myLength; j++){
                System.out.print(myGrid[i][j].toString());
                System.out.print("  ");
            }
            System.out.printf("%n%n");
        }
        
        
        
    }
    
    
}
