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
enum Color {
    BLACK ('B'),
    WHITE ('W');
    
    private char colorChar;
    
    Color (char color){
        this.colorChar = color;
    }
    
    public char getColorChar(){
        return colorChar;
    }
    
    public Color getOppositeColor(){
        if (this == Color.BLACK)
            return Color.WHITE;
        else
            return Color.BLACK;
        
    }
}
