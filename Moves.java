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
public class Moves {
    private static int numberOfMoves;
    
    public static void addMove(){
        numberOfMoves++;
    }
    
    public static int getMovesMade(){
        return numberOfMoves;
    }
}
