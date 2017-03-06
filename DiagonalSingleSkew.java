/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score4.game;

/**
 *
 * @author Shahana Alam
 * 
 */
//Tested
public class DiagonalSingleSkew extends Lines  {
    
    @Override
    public boolean isWhiteLine(Board board){
        String line = "",
                line2 = "",
                line3 = "",
                line4 = "";
        int boardSize = board.getMyLength();
        
        for (int i=0; i < boardSize; i++){
            for(int j= 0; j < boardSize; j++){
                line += board.getPeg(j, i).getBeadColorLetter(j);
                line2 += board.getPeg(3-j, i).getBeadColorLetter(j);
                line3 +=board.getPeg(i, j).getBeadColorLetter(3-j);
                line4 += board.getPeg(i,j).getBeadColorLetter(j);
            }
            
            areDeadLines(line, line2, line3, line4);
            
            if (line.equals("WWWW") || line2.equals("WWWW")||line3.equals("WWWW") || line4.equals("WWWW"))
                return true;
            
            line = line2 = line3 = line4 = "";
        }
        return false;
    }
    
    
    @Override
    public boolean isBlackLine(Board board){
        String line = "",
                line2 = "",
                line3 = "",
                line4 = "";
        int boardSize = board.getMyLength();
        
        for (int i=0; i < boardSize; i++){
            for(int j= 0; j < boardSize; j++){
                line += board.getPeg(j, i).getBeadColorLetter(j);
                line2 += board.getPeg(3-j, i).getBeadColorLetter(j);
                line3 +=board.getPeg(i, j).getBeadColorLetter(3-j);
                line4 += board.getPeg(i,j).getBeadColorLetter(j);
            }
            
            areDeadLines(line, line2, line3, line4);
            
            if (line.equals("BBBB") || line2.equals("BBBB")||line3.equals("BBBB") || line4.equals("BBBB"))
                return true;
            
            line = line2 = line3 = line4 = "";
        }
        return false;
    }
    
    public void areDeadLines(String line1, String line2, String line3, String line4){
        String [] lines = {line1, line2, line3, line4};
        for (String test : lines)
            if (isDeadLine(test))
                addDeadLines();
    }
}
