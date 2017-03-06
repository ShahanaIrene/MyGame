package score4.game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Dumb responder for score4 game
//Author:Shahana alam

import java.io.PrintWriter;
import java.util.Scanner;

public class gameResponder {
    public static void main(String[] args){
        
        String command;
        PrintWriter responseWriter = new PrintWriter (System.out);
        Scanner commandReader = new Scanner (System.in);
        
        while(commandReader.hasNext()){
            command = commandReader.next();
            
            //Command to end program
            if (command.equals("exit"))
                break;
            
            switch (command){
                case "restart;":
                case "quit;":
                case "play":   //Fall through as response same for all
                case "move;":    
                case "note:" : responseWriter.println("ok.");
                    break;
                default: responseWriter.println("Bad command.");
            }
            commandReader.nextLine();// Moves to next line if more words such as Note: 'color' plays 'location'
            responseWriter.flush();
        }
    }
    
}
