/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score4.game;

import java.io.PrintWriter;
import java.util.Scanner;
/**
 *
 * @author Asif
 */
public class Player {
    private Color myColor;
    private PrintWriter myWriter;
    private Scanner myReader;
    
    public Player (char color, Scanner scanner, PrintWriter printWriter){
        if (color == 'b')
            myColor = Color.BLACK;
        else
            myColor = Color.WHITE;
        
        myWriter = printWriter;
        myReader = scanner;
    }
    
    public Color getColor(){
        return myColor;
    }
    
    public Color getOppositePlayerColor(){
        return myColor.getOppositeColor();
    }
    
    public PrintWriter getWriter(){
        return myWriter;
    }
    
    public Scanner getReader(){
        return myReader;
    }
           
 }

