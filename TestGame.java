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
public class TestGame {
    
    public static void main(String [] args){
       Color color1 = Color.BLACK;
       Color color2 = Color.WHITE;
       Location myLocation = new Location("a3");
       System.out.println(myLocation);


Board myBoard = new Board(4);
myBoard.addBead(myLocation, color2);
myBoard.addBead(myLocation, color1);
myBoard.displayBoard();
    }
    
}
