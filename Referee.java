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
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class Referee {
    
    
    public static void main (String [] args){
        char humanColor;
        char aiColor;
        String currentMove;
        Player currentPlayer;
        PrintWriter consoleWriter;
        PrintWriter consoleWriter2;
        PrintWriter aiWriter;
        System.out.println("enter color");
        //JavaPipe myAiPlayer = new JavaPipe ("Game Responder");
        
        Scanner keyboardInput = new Scanner (System.in);
        
        //try{
            //myAiPlayer.start();
            
            humanColor = keyboardInput.next().charAt(0);
            consoleWriter = new PrintWriter (System.out);
            Scanner consoleReader = new Scanner (System.in);
            Player player1= new Player(humanColor, consoleReader, consoleWriter);
            
            System.out.println("enter color");
            humanColor = keyboardInput.next().charAt(0);
            consoleWriter2 = new PrintWriter (System.out);
            
            Player player2= new Player(humanColor, consoleReader, consoleWriter);
        
//            aiWriter = myAiPlayer.getOutputWriter();
//            Scanner aiReader = new Scanner(myAiPlayer.getInputReader());
//            aiColor = player1.getOppositePlayerColor().getColorChar();
//            Player player2 = new Player (aiColor, aiReader, aiWriter);
            
            Game myGame = new Game (4);
            
            consoleWriter2.flush();
            if (player1.getColor() == Color.WHITE){
                currentPlayer = player1;
                myGame.addPlayers(player1, player2);
            }
            else {
                currentPlayer = player2;
                myGame.addPlayers(player2, player1);
            }
        
            while ( !myGame.isFinished()){//|| !myGame.isDraw()){
                currentPlayer.getWriter().println("move;");
                currentPlayer.getWriter().flush();
                currentMove = currentPlayer.getReader().next();
                myGame.makeMove(currentMove, currentPlayer);
                currentPlayer = myGame.getOppositePlayer(currentPlayer); 
            }
        
       // }catch (IOException e){
        myGame.displayBoard(); 
        System.out.print("Winner:");
        System.out.println((myGame.getOppositePlayer(currentPlayer)).getColor());
    }
}
