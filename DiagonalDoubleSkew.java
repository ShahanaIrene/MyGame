/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score4.game;

/**
 *
 * @author Shahana Ala
 * 
 */
//Tested
public class DiagonalDoubleSkew extends Lines{

    @Override
    public boolean isWhiteLine(Board board){
        String line = "",
                line2 ="",
                line3="",
                line4="";
        
        for(int i= 0; i<board.getMyLength(); i++){
            line += board.getPeg(i, i).getBeadColorLetter(3 - i);
            line2 += board.getPeg(i, i).getBeadColorLetter(i);
            line3 += board.getPeg(i, 3-i).getBeadColorLetter(i);
            line4 += board.getPeg(i, 3-i).getBeadColorLetter(3-i);
        }
        areDeadLines(line, line2, line3, line4);
        
        return (line.equals("WWWW") || line2.equals("WWWW")||line3.equals("WWWW") || line4.equals("WWWW"));
    }
    
    @Override
    public boolean isBlackLine(Board board){
        String line = "",
                line2 ="",
                line3="",
                line4="";
        for(int i= 0; i<board.getMyLength(); i++){
            line += board.getPeg(i, i).getBeadColorLetter(3 - i);
            line2 += board.getPeg(i, i).getBeadColorLetter(i);
            line3 += board.getPeg(i, 3-i).getBeadColorLetter(i);
            line4 += board.getPeg(i, 3-i).getBeadColorLetter(3-i);
        } 
        areDeadLines(line, line2, line3, line4);
        return (line.equals("BBBB") || line2.equals("BBBB")||line3.equals("BBBB") || line4.equals("BBBB"));
    }
    
    public void areDeadLines(String line1, String line2, String line3, String line4){
        String [] lines = {line1, line2, line3, line4};
        for (String test : lines)
            if (isDeadLine(test))
                addDeadLines();
    }
}
