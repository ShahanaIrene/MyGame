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
public  abstract class Lines {
    private static int deadLines;
    public abstract boolean isWhiteLine(Board board);
    public abstract boolean isBlackLine(Board board);
    
    public void addDeadLines(){
        deadLines++;
    }
    
    public static int getDeadLines(){
        return deadLines;
    }
    
    public boolean isDeadLine(String line){
        return (line.contains("B") && (line.contains("W")));
    }
    //public abstract boolean isWhiteLine();
    //public abstract char winningLineColor();
}
