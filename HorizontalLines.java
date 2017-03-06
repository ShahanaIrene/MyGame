/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score4.game;

/**
 *
 * @author Shahana Alam
 * Tested
 */
public class HorizontalLines extends Lines {

    @Override
    public boolean isWhiteLine(Board board){
        String line = "",
                line2 =""; 
        int boardSize = board.getMyLength();
        
        for (int i=0; i< boardSize; i++)
            for(int j= 0; j<boardSize; j++){
                for(int k= 0 ; k< boardSize; k++){
                    line += board.getPeg(i, k).getBeadColorLetter(j);
                    line2 += board.getPeg(k, i).getBeadColorLetter(j);
                }
                areDeadLines(line, line2);
                
                if (line.equals("WWWW")||line2.equals("WWWW"))
                    return true;
                line = line2 = "";
            } 
        return false;
    }
    
    @Override
    public boolean isBlackLine(Board board){
        String line = "",
                line2 ="";
        int boardSize = board.getMyLength();
        
        for (int i=0; i< boardSize; i++)
            for(int j= 0; j<boardSize; j++){
                for(int k= 0 ; k< boardSize; k++){
                    line += board.getPeg(i, k).getBeadColorLetter(j);
                    line2 += board.getPeg(k, i).getBeadColorLetter(j);
                }
                
                areDeadLines(line, line2);
                
                if (line.equals("BBBB") ||line2.equals("BBBB"))
                    return true;
                
                line = line2 = "";
            } 
        return false;
    }
    
    public void areDeadLines(String line1, String line2){
        String [] lines = {line1, line2};
        for (String test : lines)
            if (isDeadLine(test))
                addDeadLines();
    }
}
