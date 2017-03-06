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
public class Peg {
    private  final char [] peg;
    private Location myLocation;
    
    public Peg(int rows){
        peg = new char[rows];
        for (int i = 0; i < peg.length; i++)
            peg[i] = 'n';
    }
    
    public void addBead(Color color){
        if(isFull())
            //throw exception
        ;
        
        for (int i = 0; i < peg.length; i++)
            if (peg[i]== 'n'){
                peg[i]= color.getColorChar();
                return;
            }
    }
    
    public void addLocation(int row, int column){
        myLocation = new Location (row,column);
    }
    
    public char getBeadColorLetter(int i){
        return (peg[i]);
    }
    public Location getLocation(){
        return myLocation; 
    }
    
    
    public String toString(){
        String beads ="";
        for (int i = 0; i < peg.length; i++){
            beads =  peg[i] + beads;
        }
        return beads;
    }
    
    public void clearPeg(){
        for (int i = 0; i < peg.length; i++)
            peg[i] = 'n';
    }
    
    private boolean isFull(){
        return peg.length != 'n';
    }
}
