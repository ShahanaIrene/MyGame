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
public class Location {
    private char myRow;
    private int myColumn;
    private static final char [] rowChar = {'a','b','c','d','e','f','g'};
    
    public Location (int row, int column){
       
        myRow = rowChar [row];
        myColumn = column;
    }
    
    public Location (String stringLocation){
        //throw exception for onvalid location
        myRow = stringLocation.charAt(0);
        myColumn = (Integer.parseInt(stringLocation.substring(1))-1);
    }
    
    public int getRowNumber(){
        for (int i = 0; i < rowChar.length; i++){
            if (myRow == rowChar[i])
                return i;
        }
        
        //throw exception?
        return -99;
    }
    
    public char getRowChar(){
        return myRow;
    }
    
    public int getColumn (){
        return myColumn;
    }
    
    @Override
    public String toString(){
        return String.format("%c%d", myRow, myColumn+1);
    }
    
    @Override
    public boolean equals(Object o){
        if (o instanceof Location)
            return this.equals((Location)o);
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.myRow;
        hash = 73 * hash + this.myColumn;
        return hash;
    }
    
    public boolean equals (Location that){
        return that != null
                && this.getColumn() == that.getColumn()
                && this.getRowNumber() == that.getRowNumber();
    }
    
}
